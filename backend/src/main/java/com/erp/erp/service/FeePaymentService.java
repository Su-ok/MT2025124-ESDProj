package com.erp.erp.service;

import com.erp.erp.dto.FeePaymentDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FeePaymentService {
    List<FeePaymentDto> getAllPayments();
    FeePaymentDto getPaymentById(Long id);
    List<FeePaymentDto> getPaymentsByStudent(Long studentId);
    List<FeePaymentDto> getPaymentsByFee(Long feeId);
    BigDecimal getTotalPaidAmount(Long studentId, Long feeId);
    List<FeePaymentDto> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    FeePaymentDto recordPayment(FeePaymentDto paymentDto);
    FeePaymentDto updatePayment(Long id, FeePaymentDto paymentDto);
    void deletePayment(Long id);
    FeePaymentDto verifyPayment(Long paymentId, Long verifiedById, String remarks);
}
