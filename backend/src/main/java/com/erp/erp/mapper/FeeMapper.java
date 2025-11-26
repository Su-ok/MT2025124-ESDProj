package com.erp.erp.mapper;

import com.erp.erp.dto.FeeDto;
import com.erp.erp.entity.Fee;
import com.erp.erp.repository.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeeMapper implements BaseMapper<Fee, FeeDto> {

    @Autowired
    protected DepartmentRepository departmentRepository;

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    public abstract FeeDto toDto(Fee entity);

    @Mapping(target = "department", expression = "java(getDepartment(dto.getDepartmentId()))")
    public abstract Fee toEntity(FeeDto dto);

    @Mapping(target = "department", expression = "java(getDepartment(dto.getDepartmentId()))")
    public abstract void updateEntity(FeeDto dto, @MappingTarget Fee entity);

    protected com.erp.erp.entity.Department getDepartment(Long departmentId) {
        if (departmentId == null) {
            return null;
        }
        return departmentRepository.findById(departmentId).orElse(null);
    }
}
