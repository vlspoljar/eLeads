package com.eleads.dao;

import com.eleads.dto.CarBrandDto;
import com.eleads.model.CarBrand;

/**
 * Created by Vlatko
 */
public class CarBrandDao extends GenericDao<Object, CarBrandDto, Integer> {

    @Override
    protected Object formEntity(CarBrandDto dto) {
        CarBrand entity = null;
        if (dto != null) {
            entity = new CarBrand(dto.getId(), dto.getName());
        }
        return entity;
    }

    @Override
    protected CarBrandDto formDTO(Object o) {
        CarBrandDto dto = null;
        if (o != null) {
            if (o instanceof CarBrand) {
                CarBrand entity = (CarBrand) o;
                dto = new CarBrandDto(entity.getId(), entity.getName());
            } else {
                Object[] entityArray = (Object[]) o;
                dto = new CarBrandDto((Integer) entityArray[0], (String) entityArray[1]);
            }
        }
        return dto;
    }

    @Override
    protected Class<?> entityClass() {
        return CarBrand.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return CarBrandDto.class;
    }

    @Override
    public String getBasicSql() {
        String sql = "select carBrand.id, carBrand.name from car_brand carBrand where 1 = 1 ";
        return sql;
    }

    @Override
    public String getDefaultOrderBySql() {
        return " order by carBrand.id ";
    }

}
