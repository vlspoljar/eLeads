package com.eleads.dao;

import com.eleads.dto.*;
import com.eleads.model.*;
import com.eleads.util.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
public class LeadDao extends GenericDao<Object, LeadDto, Integer> {

    @Inject CarDao carDao;

    @Override
    protected Object formEntity(LeadDto dto) {
        Lead entity = null;
        if (dto != null) {
            entity = new Lead();
            entity.setId(dto.getId());
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setCity(dto.getCity());
            List<Car> cars = new ArrayList<>();
            if (dto.getCars() != null && !dto.getCars().isEmpty()) {
                Car car;
                for (CarDto carDto : dto.getCars()) {
                    if (carDto != null) {
                        car = new Car();
                        car.setId(carDto.getId());
                        car.setNumDoors(carDto.getNumDoors());
                        car.setColor(carDto.getColor());
                        if (carDto.getEngineType() != null) car.setEngineTypeId(new EngineType(carDto.getEngineType().getId(), carDto.getEngineType().getName()));
                        if (carDto.getCarBrand() != null) car.setCarBrandId(new CarBrand(carDto.getCarBrand().getId(), carDto.getCarBrand().getName()));
                        if (carDto.getCarModel() != null) car.setCarModelId(new CarModel(carDto.getCarModel().getId(), carDto.getCarModel().getName()));
                        cars.add(car);
                    }
                }
            }
            entity.setCars(cars);
        }
        return entity;
    }

    @Override
    protected LeadDto formDTO(Object o) {
        LeadDto dto = null;
        if (o != null) {
            if (o instanceof Lead) {
                Lead entity = (Lead) o;
                dto = new LeadDto();
                dto.setId(entity.getId());
                dto.setFirstName(entity.getFirstName());
                dto.setLastName(entity.getLastName());
                dto.setCity(entity.getCity());
                List<CarDto> cars = new ArrayList<>();
                if (entity.getCars() != null && !entity.getCars().isEmpty()) {
                    CarDto carDto;
                    for (Car car : entity.getCars()) {
                        if (car != null) {
                            carDto = new CarDto();
                            carDto.setId(car.getId());
                            carDto.setNumDoors(car.getNumDoors());
                            carDto.setColor(car.getColor());
                            if (car.getEngineTypeId() != null) carDto.setEngineType(new EngineTypeDto(car.getEngineTypeId().getId(), car.getEngineTypeId().getName()));
                            if (car.getCarBrandId() != null) carDto.setCarBrand(new CarBrandDto(car.getCarBrandId().getId(), car.getCarBrandId().getName()));
                            if (car.getCarModelId() != null) carDto.setCarModel(new CarModelDto(car.getCarModelId().getId(), car.getCarModelId().getName()));
                            cars.add(carDto);
                        }
                    }
                }
                dto.setCars(cars);
            } else {
                Object[] entityArray = (Object[]) o;
                dto = new LeadDto();
                dto.setId((Integer) entityArray[0]);
                dto.setFirstName((String) entityArray[1]);
                dto.setLastName((String) entityArray[2]);
                dto.setCity((String) entityArray[3]);
                dto.setCars(carDao.getCarsByLeadId(dto.getId()));
            }
        }
        return dto;
    }

    @Override
    protected Class<?> entityClass() {
        return Lead.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return LeadDto.class;
    }

    @Override
    public String getBasicSql() {
        String sql = "select lead.id, lead.first_name, lead.last_name, lead.city from lead where 1 = 1 ";
        return sql;
    }

    @Override
    public String getDefaultOrderBySql() {
        return " order by lead.id ";
    }

    public List<LeadDto> filterLeads(LeadDto filterDto) {
        List<LeadDto> leads = new ArrayList<>();
        String sql = getBasicSql();
        if (filterDto != null) {
            if (filterDto.getId() != null) sql += " and lead.id = :leadId ";
            if (StringUtils.isNotBlank(filterDto.getFirstName())) sql += " and LOWER(lead.first_name) = :leadFirstName ";
            if (StringUtils.isNotBlank(filterDto.getLastName())) sql += " and LOWER(lead.last_name) = :leadLastName ";
            if (StringUtils.isNotBlank(filterDto.getCity())) sql += " and LOWER(lead.city) = :leadCity ";
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNativeQuery(sql);
        if (filterDto != null) {
            if (filterDto.getId() != null) query.setParameter("leadId", filterDto.getId());
            if (StringUtils.isNotBlank(filterDto.getFirstName())) query.setParameter("leadFirstName", StringUtils.lowerCase(filterDto.getFirstName()));
            if (StringUtils.isNotBlank(filterDto.getLastName())) query.setParameter("leadLastName", StringUtils.lowerCase(filterDto.getLastName()));
            if (StringUtils.isNotBlank(filterDto.getCity())) query.setParameter("leadCity", StringUtils.lowerCase(filterDto.getCity()));
        }
        List<Object[]> resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            LeadDto leadDto;
            for (Object[] o : resultList) {
                leadDto = formDTO(o);
                leads.add(leadDto);
            }
        }
        return leads;
    }

}
