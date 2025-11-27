package com.erp.erp.mapper;

import com.erp.erp.dto.FeePaymentDto;
import com.erp.erp.entity.*;
import com.erp.erp.exception.ResourceNotFoundException;
import com.erp.erp.repository.EmployeeRepository;
import com.erp.erp.repository.FeeRepository;
import com.erp.erp.repository.StudentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeePaymentMapper implements BaseMapper<FeePayment, FeePaymentDto> {

    @Autowired
    protected StudentRepository studentRepository;
    
    @Autowired
    protected FeeRepository feeRepository;
    
    @Autowired
    protected EmployeeRepository employeeRepository;

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "studentIdNumber", source = "student.studentId")
    @Mapping(target = "feeId", source = "fee.id")
    @Mapping(target = "feeName", source = "fee.name")
    @Mapping(target = "receivedById", source = "receivedBy.id")
    @Mapping(target = "receivedByName", source = "receivedBy.name")
    @Mapping(target = "verifiedById", source = "verifiedBy.id")
    @Mapping(target = "verifiedByName", source = "verifiedBy.name")
    public abstract FeePaymentDto toDto(FeePayment entity);

    @Mapping(target = "student", expression = "java(getStudent(dto.getStudentId()))")
    @Mapping(target = "fee", expression = "java(getFee(dto.getFeeId()))")
    @Mapping(target = "receivedBy", expression = "java(getEmployee(dto.getReceivedById()))")
    @Mapping(target = "verifiedBy", expression = "java(getEmployee(dto.getVerifiedById()))")
    public abstract FeePayment toEntity(FeePaymentDto dto);

    @Mapping(target = "student", expression = "java(getStudent(dto.getStudentId()))")
    @Mapping(target = "fee", expression = "java(getFee(dto.getFeeId()))")
    @Mapping(target = "receivedBy", expression = "java(getEmployee(dto.getReceivedById()))")
    @Mapping(target = "verifiedBy", expression = "java(getEmployee(dto.getVerifiedById()))")
    public abstract void updateEntity(FeePaymentDto dto, @MappingTarget FeePayment entity);

    protected Student getStudent(Long studentId) {
        if (studentId == null) {
            return null;
        }
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "id", studentId));
    }

    protected Fee getFee(Long feeId) {
        if (feeId == null) {
            return null;
        }
        return feeRepository.findById(feeId)
                .orElseThrow(()-> new ResourceNotFoundException("Fee", "id", feeId));
    }

    protected Employee getEmployee(Long employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("ReceivedBy", "id", employeeId));
    }
}
