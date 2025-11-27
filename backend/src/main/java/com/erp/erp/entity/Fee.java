package com.erp.erp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fees")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "semester")
    private Integer semester;
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    @OneToMany(mappedBy = "fee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeePayment> feePayments = new ArrayList<>();
    
    public void addFeePayment(FeePayment feePayment) {
        feePayments.add(feePayment);
        feePayment.setFee(this);
    }
    
    public void removeFeePayment(FeePayment feePayment) {
        feePayments.remove(feePayment);
        feePayment.setFee(null);
    }
}
