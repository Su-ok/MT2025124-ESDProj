package com.erp.erp.service.impl;

import com.erp.erp.dto.FeeDto;
import com.erp.erp.entity.Fee;
import com.erp.erp.exception.ResourceNotFoundException;
import com.erp.erp.mapper.FeeMapper;
import com.erp.erp.repository.FeeRepository;
import com.erp.erp.service.FeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;
    private final FeeMapper feeMapper;

    @Override
    public List<FeeDto> getAllFees() {
        return feeRepository.findAll().stream()
                .map(feeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeeDto getFeeById(Long id) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found with id: " + id));
        return feeMapper.toDto(fee);
    }

    @Override
    public List<FeeDto> getFeesByDepartment(Long departmentId) {
        return feeRepository.findByDepartmentId(departmentId).stream()
                .map(feeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeeDto> getFeesByDepartmentAndAcademicYearAndSemester(
            Long departmentId, String academicYear, Integer semester) {
        return feeRepository.findByDepartmentIdAndAcademicYearAndSemester(departmentId, academicYear, semester)
                .stream()
                .map(feeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeeDto createFee(FeeDto feeDto) {
        Fee fee = feeMapper.toEntity(feeDto);
        Fee savedFee = feeRepository.save(fee);
        return feeMapper.toDto(savedFee);
    }

    @Override
    @Transactional
    public FeeDto updateFee(Long id, FeeDto feeDto) {
        Fee existingFee = feeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found with id: " + id));
        
        feeMapper.updateEntity(feeDto, existingFee);
        Fee updatedFee = feeRepository.save(existingFee);
        return feeMapper.toDto(updatedFee);
    }

    @Override
    @Transactional
    public void deleteFee(Long id) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found with id: " + id));
        feeRepository.delete(fee);
    }

    @Override
    @Transactional
    public FeeDto toggleFeeStatus(Long id, boolean isActive) {
        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found with id: " + id));
        fee.setActive(isActive);
        Fee updatedFee = feeRepository.save(fee);
        return feeMapper.toDto(updatedFee);
    }
}
