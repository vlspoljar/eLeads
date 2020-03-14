package com.eleads.dao;

import com.eleads.dto.EngineTypeDto;
import com.eleads.model.EngineType;

/**
 * Created by Vlatko
 */
public class EngineTypeDao extends GenericDao<Object, EngineTypeDto, Integer> {

    @Override
    protected EngineType formEntity(EngineTypeDto dto) {
        EngineType entity = null;
        if (dto != null) {
            entity = new EngineType(dto.getId(), dto.getName());
        }
        return entity;
    }

    @Override
    protected EngineTypeDto formDTO(Object o) {
        EngineTypeDto dto = null;
        if (o != null) {
            if (o instanceof EngineType) {
                EngineType entity = (EngineType) o;
                dto = new EngineTypeDto(entity.getId(), entity.getName());
            } else {
                Object[] entityArray = (Object[]) o;
                dto = new EngineTypeDto((Integer) entityArray[0], (String) entityArray[1]);
            }
        }
        return dto;
    }

    @Override
    protected Class<?> entityClass() {
        return EngineType.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return EngineTypeDto.class;
    }

    @Override
    public String getBasicSql() {
        String sql = "select engineType.id, engineType.name from engine_type engineType where 1 = 1 ";
        return sql;
    }

    @Override
    public String getDefaultOrderBySql() {
        return " order by engineType.id ";
    }

}
