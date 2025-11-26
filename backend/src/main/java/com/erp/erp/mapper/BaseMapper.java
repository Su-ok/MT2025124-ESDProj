package com.erp.erp.mapper;

public interface BaseMapper<Entity, DTO> {
    DTO toDto(Entity entity);
    Entity toEntity(DTO dto);
    void updateEntity(DTO dto, Entity entity);
}
