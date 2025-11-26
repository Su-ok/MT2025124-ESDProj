package com.erp.erp.mapper;

import com.erp.erp.dto.FeeDto;
import com.erp.erp.entity.Department;
import com.erp.erp.entity.Fee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T01:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Ubuntu)"
)
@Component
public class FeeMapperImpl extends FeeMapper {

    @Override
    public FeeDto toDto(Fee entity) {
        if ( entity == null ) {
            return null;
        }

        FeeDto feeDto = new FeeDto();

        feeDto.setDepartmentId( entityDepartmentId( entity ) );
        feeDto.setDepartmentName( entityDepartmentName( entity ) );
        feeDto.setId( entity.getId() );
        feeDto.setName( entity.getName() );
        feeDto.setDescription( entity.getDescription() );
        feeDto.setAmount( entity.getAmount() );
        feeDto.setActive( entity.isActive() );
        feeDto.setAcademicYear( entity.getAcademicYear() );
        feeDto.setSemester( entity.getSemester() );
        feeDto.setDueDate( entity.getDueDate() );

        return feeDto;
    }

    @Override
    public Fee toEntity(FeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Fee fee = new Fee();

        fee.setId( dto.getId() );
        fee.setName( dto.getName() );
        fee.setDescription( dto.getDescription() );
        fee.setAmount( dto.getAmount() );
        fee.setActive( dto.isActive() );
        fee.setAcademicYear( dto.getAcademicYear() );
        fee.setSemester( dto.getSemester() );
        fee.setDueDate( dto.getDueDate() );

        fee.setDepartment( getDepartment(dto.getDepartmentId()) );

        return fee;
    }

    @Override
    public void updateEntity(FeeDto dto, Fee entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
        entity.setAmount( dto.getAmount() );
        entity.setActive( dto.isActive() );
        entity.setAcademicYear( dto.getAcademicYear() );
        entity.setSemester( dto.getSemester() );
        entity.setDueDate( dto.getDueDate() );

        entity.setDepartment( getDepartment(dto.getDepartmentId()) );
    }

    private Long entityDepartmentId(Fee fee) {
        if ( fee == null ) {
            return null;
        }
        Department department = fee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityDepartmentName(Fee fee) {
        if ( fee == null ) {
            return null;
        }
        Department department = fee.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
