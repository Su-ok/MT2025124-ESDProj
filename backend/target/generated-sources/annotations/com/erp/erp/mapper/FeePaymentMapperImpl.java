package com.erp.erp.mapper;

import com.erp.erp.dto.FeePaymentDto;
import com.erp.erp.entity.Employee;
import com.erp.erp.entity.Fee;
import com.erp.erp.entity.FeePayment;
import com.erp.erp.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T22:26:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Ubuntu)"
)
@Component
public class FeePaymentMapperImpl extends FeePaymentMapper {

    @Override
    public FeePaymentDto toDto(FeePayment entity) {
        if ( entity == null ) {
            return null;
        }

        FeePaymentDto feePaymentDto = new FeePaymentDto();

        feePaymentDto.setStudentId( entityStudentId( entity ) );
        feePaymentDto.setStudentName( entityStudentName( entity ) );
        feePaymentDto.setStudentIdNumber( entityStudentStudentId( entity ) );
        feePaymentDto.setFeeId( entityFeeId( entity ) );
        feePaymentDto.setFeeName( entityFeeName( entity ) );
        feePaymentDto.setReceivedById( entityReceivedById( entity ) );
        feePaymentDto.setReceivedByName( entityReceivedByName( entity ) );
        feePaymentDto.setVerifiedById( entityVerifiedById( entity ) );
        feePaymentDto.setVerifiedByName( entityVerifiedByName( entity ) );
        feePaymentDto.setId( entity.getId() );
        feePaymentDto.setAmount( entity.getAmount() );
        feePaymentDto.setPaymentDate( entity.getPaymentDate() );
        feePaymentDto.setPaymentMethod( entity.getPaymentMethod() );
        feePaymentDto.setTransactionReference( entity.getTransactionReference() );
        feePaymentDto.setRemarks( entity.getRemarks() );
        feePaymentDto.setVerified( entity.isVerified() );
        feePaymentDto.setVerificationDate( entity.getVerificationDate() );

        return feePaymentDto;
    }

    @Override
    public FeePayment toEntity(FeePaymentDto dto) {
        if ( dto == null ) {
            return null;
        }

        FeePayment feePayment = new FeePayment();

        feePayment.setId( dto.getId() );
        feePayment.setAmount( dto.getAmount() );
        feePayment.setPaymentDate( dto.getPaymentDate() );
        feePayment.setPaymentMethod( dto.getPaymentMethod() );
        feePayment.setTransactionReference( dto.getTransactionReference() );
        feePayment.setRemarks( dto.getRemarks() );
        feePayment.setVerified( dto.isVerified() );
        feePayment.setVerificationDate( dto.getVerificationDate() );

        feePayment.setStudent( getStudent(dto.getStudentId()) );
        feePayment.setFee( getFee(dto.getFeeId()) );
        feePayment.setReceivedBy( getEmployee(dto.getReceivedById()) );
        feePayment.setVerifiedBy( getEmployee(dto.getVerifiedById()) );

        return feePayment;
    }

    @Override
    public void updateEntity(FeePaymentDto dto, FeePayment entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setAmount( dto.getAmount() );
        entity.setPaymentDate( dto.getPaymentDate() );
        entity.setPaymentMethod( dto.getPaymentMethod() );
        entity.setTransactionReference( dto.getTransactionReference() );
        entity.setRemarks( dto.getRemarks() );
        entity.setVerified( dto.isVerified() );
        entity.setVerificationDate( dto.getVerificationDate() );

        entity.setStudent( getStudent(dto.getStudentId()) );
        entity.setFee( getFee(dto.getFeeId()) );
        entity.setReceivedBy( getEmployee(dto.getReceivedById()) );
        entity.setVerifiedBy( getEmployee(dto.getVerifiedById()) );
    }

    private Long entityStudentId(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Student student = feePayment.getStudent();
        if ( student == null ) {
            return null;
        }
        Long id = student.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityStudentName(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Student student = feePayment.getStudent();
        if ( student == null ) {
            return null;
        }
        String name = student.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityStudentStudentId(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Student student = feePayment.getStudent();
        if ( student == null ) {
            return null;
        }
        String studentId = student.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        return studentId;
    }

    private Long entityFeeId(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Fee fee = feePayment.getFee();
        if ( fee == null ) {
            return null;
        }
        Long id = fee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityFeeName(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Fee fee = feePayment.getFee();
        if ( fee == null ) {
            return null;
        }
        String name = fee.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long entityReceivedById(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Employee receivedBy = feePayment.getReceivedBy();
        if ( receivedBy == null ) {
            return null;
        }
        Long id = receivedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityReceivedByName(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Employee receivedBy = feePayment.getReceivedBy();
        if ( receivedBy == null ) {
            return null;
        }
        String name = receivedBy.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long entityVerifiedById(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Employee verifiedBy = feePayment.getVerifiedBy();
        if ( verifiedBy == null ) {
            return null;
        }
        Long id = verifiedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityVerifiedByName(FeePayment feePayment) {
        if ( feePayment == null ) {
            return null;
        }
        Employee verifiedBy = feePayment.getVerifiedBy();
        if ( verifiedBy == null ) {
            return null;
        }
        String name = verifiedBy.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
