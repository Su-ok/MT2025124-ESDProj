package com.erp.erp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FeePaymentDto extends BaseDto {
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    private String studentName;
    private String studentIdNumber;
    
    @NotNull(message = "Fee ID is required")
    private Long feeId;
    
    private String feeName;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentDate;
    
    private String paymentMethod;
    private String transactionReference;
    private String remarks;
    
    private Long receivedById;
    private String receivedByName;
    
    private boolean isVerified = false;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verificationDate;
    
    private Long verifiedById;
    private String verifiedByName;
}
