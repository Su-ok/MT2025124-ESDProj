package com.erp.erp.repository;

import com.erp.erp.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
    List<FeePayment> findByStudentId(Long studentId);
    List<FeePayment> findByFeeId(Long feeId);
    
    @Query("SELECT fp FROM FeePayment fp WHERE fp.student.id = :studentId AND fp.fee.id = :feeId")
    List<FeePayment> findByStudentAndFee(
        @Param("studentId") Long studentId,
        @Param("feeId") Long feeId
    );
    
    @Query("SELECT COALESCE(SUM(fp.amount), 0) FROM FeePayment fp WHERE fp.student.id = :studentId AND fp.fee.id = :feeId")
    BigDecimal getTotalPaidAmountForFee(
        @Param("studentId") Long studentId,
        @Param("feeId") Long feeId
    );
    
    @Query("SELECT fp FROM FeePayment fp WHERE fp.paymentDate BETWEEN :startDate AND :endDate")
    List<FeePayment> findPaymentsBetweenDates(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
