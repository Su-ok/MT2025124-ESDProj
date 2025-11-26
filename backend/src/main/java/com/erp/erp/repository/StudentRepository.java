package com.erp.erp.repository;

import com.erp.erp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByStudentId(String studentId);
    List<Student> findByDepartmentId(Long departmentId);
    
    @Query("SELECT s FROM Student s WHERE s.department.id = :departmentId AND s.academicYear = :academicYear")
    List<Student> findByDepartmentAndAcademicYear(
        @Param("departmentId") Long departmentId, 
        @Param("academicYear") String academicYear
    );
}
