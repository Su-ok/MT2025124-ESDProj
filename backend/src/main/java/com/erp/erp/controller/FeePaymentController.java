package com.erp.erp.controller;

import com.erp.erp.dto.FeePaymentDto;
import com.erp.erp.service.FeePaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/fee-payments")
public class FeePaymentController extends BaseController {

    private final FeePaymentService feePaymentService;

    @Autowired
    public FeePaymentController(FeePaymentService feePaymentService) {
        this.feePaymentService = feePaymentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeePaymentDto>> getAllPayments() {
        return ResponseEntity.ok(feePaymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeePaymentDto> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(feePaymentService.getPaymentById(id));
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeePaymentDto>> getPaymentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(feePaymentService.getPaymentsByStudent(studentId));
    }

    @GetMapping("/fee/{feeId}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeePaymentDto>> getPaymentsByFee(@PathVariable Long feeId) {
        return ResponseEntity.ok(feePaymentService.getPaymentsByFee(feeId));
    }

    @GetMapping("/student/{studentId}/fee/{feeId}/total")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<BigDecimal> getTotalPaidAmount(
            @PathVariable Long studentId, @PathVariable Long feeId) {
        return ResponseEntity.ok(feePaymentService.getTotalPaidAmount(studentId, feeId));
    }

    @GetMapping("/between-dates")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<List<FeePaymentDto>> getPaymentsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(feePaymentService.getPaymentsBetweenDates(startDate, endDate));
    }

    @PostMapping
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeePaymentDto> recordPayment(@Valid @RequestBody FeePaymentDto paymentDto) {
        return ResponseEntity.ok(feePaymentService.recordPayment(paymentDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeePaymentDto> updatePayment(
            @PathVariable Long id, @Valid @RequestBody FeePaymentDto paymentDto) {
        return ResponseEntity.ok(feePaymentService.updatePayment(id, paymentDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        feePaymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/verify")
    @PreAuthorize("hasRole('ACCOUNTS')")
    public ResponseEntity<FeePaymentDto> verifyPayment(
            @PathVariable Long id,
            @RequestParam Long verifiedById,
            @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(feePaymentService.verifyPayment(id, verifiedById, remarks));
    }
}
