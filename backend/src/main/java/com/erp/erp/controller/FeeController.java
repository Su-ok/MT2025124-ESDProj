package com.erp.erp.controller;

import com.erp.erp.dto.FeeDto;
import com.erp.erp.service.FeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController extends BaseController {

    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeeDto>> getAllFees() {
        return ResponseEntity.ok(feeService.getAllFees());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeeDto> getFeeById(@PathVariable Long id) {
        return ResponseEntity.ok(feeService.getFeeById(id));
    }

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeeDto>> getFeesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(feeService.getFeesByDepartment(departmentId));
    }

    @GetMapping("/department/{departmentId}/academic-year/{academicYear}/semester/{semester}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeeDto>> getFeesByDepartmentAndAcademicYearAndSemester(
            @PathVariable Long departmentId,
            @PathVariable String academicYear,
            @PathVariable Integer semester) {
        return ResponseEntity.ok(
                feeService.getFeesByDepartmentAndAcademicYearAndSemester(departmentId, academicYear, semester));
    }

    @PostMapping
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeeDto> createFee(@Valid @RequestBody FeeDto feeDto) {
        return ResponseEntity.ok(feeService.createFee(feeDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeeDto> updateFee(
            @PathVariable Long id, @Valid @RequestBody FeeDto feeDto) {
        return ResponseEntity.ok(feeService.updateFee(id, feeDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<Void> deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeeDto> toggleFeeStatus(
            @PathVariable Long id, @RequestParam boolean isActive) {
        return ResponseEntity.ok(feeService.toggleFeeStatus(id, isActive));
    }
}
