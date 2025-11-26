package com.erp.erp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FeeDto extends BaseDto {
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    private boolean isActive = true;
    
    private Long departmentId;
    private String departmentName;
    
    @NotBlank(message = "Academic year is required")
    private String academicYear;
    
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester cannot exceed 12")
    private Integer semester;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
