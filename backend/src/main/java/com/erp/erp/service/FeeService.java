package com.erp.erp.service;

import com.erp.erp.dto.FeeDto;
import java.util.List;

public interface FeeService {
    List<FeeDto> getAllFees();
    FeeDto getFeeById(Long id);
    List<FeeDto> getFeesByDepartment(Long departmentId);
    List<FeeDto> getFeesByDepartmentAndAcademicYearAndSemester(
        Long departmentId, String academicYear, Integer semester);
    FeeDto createFee(FeeDto feeDto);
    FeeDto updateFee(Long id, FeeDto feeDto);
    void deleteFee(Long id);
    FeeDto toggleFeeStatus(Long id, boolean isActive);
}
