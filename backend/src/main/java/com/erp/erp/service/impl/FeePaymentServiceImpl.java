package com.erp.erp.service.impl;

import com.erp.erp.dto.FeePaymentDto;
import com.erp.erp.entity.Employee;
import com.erp.erp.entity.FeePayment;
import com.erp.erp.exception.ResourceNotFoundException;
import com.erp.erp.mapper.FeePaymentMapper;
import com.erp.erp.repository.EmployeeRepository;
import com.erp.erp.repository.FeePaymentRepository;
import com.erp.erp.service.FeePaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeePaymentServiceImpl implements FeePaymentService {

    private final FeePaymentRepository feePaymentRepository;
    private final FeePaymentMapper feePaymentMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<FeePaymentDto> getAllPayments() {
        return feePaymentRepository.findAll().stream()
                .map(feePaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeePaymentDto getPaymentById(Long id) {
        FeePayment payment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return feePaymentMapper.toDto(payment);
    }

    @Override
    public List<FeePaymentDto> getPaymentsByStudent(Long studentId) {
        return feePaymentRepository.findByStudentId(studentId).stream()
                .map(feePaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeePaymentDto> getPaymentsByFee(Long feeId) {
        return feePaymentRepository.findByFeeId(feeId).stream()
                .map(feePaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalPaidAmount(Long studentId, Long feeId) {
        return feePaymentRepository.getTotalPaidAmountForFee(studentId, feeId);
    }

    @Override
    public List<FeePaymentDto> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return feePaymentRepository.findPaymentsBetweenDates(startDate, endDate).stream()
                .map(feePaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeePaymentDto recordPayment(FeePaymentDto paymentDto) {
        // Set received by to the current authenticated employee
        // In a real app, you'd get this from the security context
        // For now, we'll use the first admin user or the provided receivedById
        if (paymentDto.getReceivedById() == null) {
            List<Employee> admins = employeeRepository.findByIsAdminTrue();
            if (!admins.isEmpty()) {
                paymentDto.setReceivedById(admins.get(0).getId());
            }
        }
        
        // Set payment date to now if not provided
        if (paymentDto.getPaymentDate() == null) {
            paymentDto.setPaymentDate(LocalDateTime.now());
        }
        
        FeePayment payment = feePaymentMapper.toEntity(paymentDto);
        FeePayment savedPayment = feePaymentRepository.save(payment);
        return feePaymentMapper.toDto(savedPayment);
    }

    @Override
    @Transactional
    public FeePaymentDto updatePayment(Long id, FeePaymentDto paymentDto) {
        FeePayment existingPayment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        
        // Don't allow updating verified payments
        if (existingPayment.isVerified()) {
            throw new IllegalStateException("Cannot update a verified payment");
        }
        
        feePaymentMapper.updateEntity(paymentDto, existingPayment);
        FeePayment updatedPayment = feePaymentRepository.save(existingPayment);
        return feePaymentMapper.toDto(updatedPayment);
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        FeePayment payment = feePaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        
        // Don't allow deleting verified payments
        if (payment.isVerified()) {
            throw new IllegalStateException("Cannot delete a verified payment");
        }
        
        feePaymentRepository.delete(payment);
    }

    @Override
    @Transactional
    public FeePaymentDto verifyPayment(Long paymentId, Long verifiedById, String remarks) {
        FeePayment payment = feePaymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));
        
        if (payment.isVerified()) {
            throw new IllegalStateException("Payment is already verified");
        }
        
        Employee verifiedBy = employeeRepository.findById(verifiedById)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + verifiedById));
        
        payment.setVerified(true);
        payment.setVerificationDate(LocalDateTime.now());
        payment.setVerifiedBy(verifiedBy);
        payment.setRemarks(remarks);
        
        FeePayment verifiedPayment = feePaymentRepository.save(payment);
        return feePaymentMapper.toDto(verifiedPayment);
    }
}
