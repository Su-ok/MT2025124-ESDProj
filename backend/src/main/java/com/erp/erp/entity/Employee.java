package com.erp.erp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {
    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;
    
    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "salary")
    private Double salary;
    
    @Column(name = "is_admin", columnDefinition = "boolean default false")
    private boolean isAdmin = false;
}
