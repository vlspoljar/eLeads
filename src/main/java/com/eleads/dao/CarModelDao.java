package com.eleads.dao;

import com.eleads.dto.CarModelDto;
import com.eleads.model.CarModel;

/**
 * Created by Vlatko
 */
public class CarModelDao extends GenericDao<Object, CarModelDto, Integer> {

    @Override
    protected Object formEntity(CarModelDto dto) {
        CarModel entity = null;
        if (dto != null) {
            entity = new CarModel(dto.getId(), dto.getName());
        }
        return entity;
    }

    @Override
    protected CarModelDto formDTO(Object o) {
        CarModelDto dto = null;
        if (o != null) {
            if (o instanceof CarModel) {
                CarModel entity = (CarModel) o;
                dto = new CarModelDto(entity.getId(), entity.getName());
            } else {
                Object[] entityArray = (Object[]) o;
                dto = new CarModelDto((Integer) entityArray[0], (String) entityArray[1]);
            }
        }
        return dto;
    }

    @Override
    protected Class<?> entityClass() {
        return CarModel.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return CarModelDto.class;
    }

    @Override
    public String getBasicSql() {
        String sql = "select carModel.id, carModel.name from car_model carModel where 1 = 1 ";
        return sql;
    }

    @Override
    public String getDefaultOrderBySql() {
        return " order by carModel.id ";
    }

}
