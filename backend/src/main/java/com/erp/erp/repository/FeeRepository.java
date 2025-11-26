package com.erp.erp.repository;

import com.erp.erp.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByDepartmentId(Long departmentId);
    List<Fee> findByDepartmentIdAndAcademicYearAndSemester(Long departmentId, String academicYear, Integer semester);
    
    @Query("SELECT f FROM Fee f WHERE f.department.id = :departmentId " +
           "AND f.academicYear = :academicYear " +
           "AND (f.semester = :semester OR f.semester IS NULL) " +
           "AND f.isActive = true")
    List<Fee> findApplicableFees(
        @Param("departmentId") Long departmentId,
        @Param("academicYear") String academicYear,
        @Param("semester") Integer semester
    );
}
