package com.erp.erp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    
    @Column(name = "student_id", unique = true, nullable = false)
    private String studentId;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "admission_date")
    private LocalDate admissionDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "current_semester")
    private Integer currentSemester;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeePayment> feePayments = new ArrayList<>();
    
    public void addFeePayment(FeePayment feePayment) {
        feePayments.add(feePayment);
        feePayment.setStudent(this);
    }
    
    public void removeFeePayment(FeePayment feePayment) {
        feePayments.remove(feePayment);
        feePayment.setStudent(null);
    }
}
