package com.eleads.dao;

import com.eleads.dto.CarBrandDto;
import com.eleads.dto.CarDto;
import com.eleads.dto.CarModelDto;
import com.eleads.dto.EngineTypeDto;
import com.eleads.model.Car;
import com.eleads.model.CarBrand;
import com.eleads.model.CarModel;
import com.eleads.model.EngineType;
import com.eleads.util.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko
 */
public class CarDao extends GenericDao<Object, CarDto, Integer> {

    @Override
    protected Object formEntity(CarDto dto) {
        Car entity = null;
        if (dto != null) {
            entity = new Car();
            entity.setId(dto.getId());
            entity.setNumDoors(dto.getNumDoors());
            entity.setColor(dto.getColor());
            if (dto.getEngineType() != null) entity.setEngineTypeId(new EngineType(dto.getEngineType().getId(), dto.getEngineType().getName()));
            if (dto.getCarBrand() != null) entity.setCarBrandId(new CarBrand(dto.getCarBrand().getId(), dto.getCarBrand().getName()));
            if (dto.getCarModel() != null) entity.setCarModelId(new CarModel(dto.getCarModel().getId(), dto.getCarModel().getName()));
        }
        return entity;
    }

    @Override
    protected CarDto formDTO(Object o) {
        CarDto dto = null;
        if (o != null) {
            if (o instanceof Car) {
                Car entity = (Car) o;
                dto = new CarDto();
                dto.setId(entity.getId());
                dto.setNumDoors(entity.getNumDoors());
                dto.setColor(entity.getColor());
                if (entity.getEngineTypeId() != null) dto.setEngineType(new EngineTypeDto(entity.getEngineTypeId().getId(), entity.getEngineTypeId().getName()));
                if (entity.getCarBrandId() != null) dto.setCarBrand(new CarBrandDto(entity.getCarBrandId().getId(), entity.getCarBrandId().getName()));
                if (entity.getCarModelId() != null) dto.setCarModel(new CarModelDto(entity.getCarModelId().getId(), entity.getCarModelId().getName()));
            } else {
                Object[] entityArray = (Object[]) o;
                dto = new CarDto();
                dto.setId((Integer) entityArray[0]);
                dto.setNumDoors((Integer) entityArray[1]);
                dto.setColor((String) entityArray[2]);
                dto.setEngineType(new EngineTypeDto((Integer) entityArray[3], (String) entityArray[4]));
                dto.setCarBrand(new CarBrandDto((Integer) entityArray[5], (String) entityArray[6]));
                dto.setCarModel(new CarModelDto((Integer) entityArray[7], (String) entityArray[8]));
            }
        }
        return dto;
    }

    @Override
    protected Class<?> entityClass() {
        return Car.class;
    }

    @Override
    protected Class<?> dtoClass() {
        return CarDto.class;
    }

    @Override
    public String getBasicSql() {
        String sql = "select car.id as carId, car.num_doors as numDoors, car.color as color, engineType.id as engineTypeId, engineType.name as engineTypeName, carBrand.id as carBrandId, carBrand.name as carBrandName, carModel.id as carModelId, carModel.name as carModelName " +
                "from car car " +
                "inner join engine_type engineType on engineType.id = car.engine_type_id " +
                "inner join car_brand carBrand on carBrand.id = car.car_brand_id " +
                "inner join car_model carModel on carModel.id = car.car_model_id " +
                "where 1 = 1 ";
        return sql;
    }

    @Override
    public String getDefaultOrderBySql() {
        return " order by car.id ";
    }

    public List<CarDto> getCarsByLeadId(Integer leadId) {
        List<CarDto> cars = new ArrayList<>();
        if (leadId != null) {
            String sql = "select car.id as carId, car.num_doors as numDoors, car.color as color, engineType.id as engineTypeId, engineType.name as engineTypeName, carBrand.id as carBrandId, carBrand.name as carBrandName, carModel.id as carModelId, carModel.name as carModelName " +
                    "from lead " +
                    "inner join lead_car on lead_car.lead_id = lead.id " +
                    "inner join car on car.id = lead_car.car_id " +
                    "inner join engine_type engineType on engineType.id = car.engine_type_id " +
                    "inner join car_brand carBrand on carBrand.id = car.car_brand_id " +
                    "inner join car_model carModel on carModel.id = car.car_model_id " +
                    "where 1 = 1 ";
            sql += "and lead.id = :leadId ";
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createNativeQuery(sql);
            query.setParameter("leadId", leadId);
            List<Object[]> resultList = query.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                CarDto carDto;
                for (Object[] o : resultList) {
                    carDto = formDTO(o);
                    cars.add(carDto);
                }
            }
        }
        return cars;
    }

    public List<CarDto> filterCars(CarDto filterDto) {
        List<CarDto> cars = new ArrayList<>();
        String sql = "select car.id as carId, car.num_doors as numDoors, car.color as color, engineType.id as engineTypeId, engineType.name as engineTypeName, carBrand.id as carBrandId, carBrand.name as carBrandName, carModel.id as carModelId, carModel.name as carModelName " +
                "from car " +
                "inner join engine_type engineType on engineType.id = car.engine_type_id " +
                "inner join car_brand carBrand on carBrand.id = car.car_brand_id " +
                "inner join car_model carModel on carModel.id = car.car_model_id " +
                "where 1 = 1 ";
        if (filterDto != null) {
            if (filterDto.getId() != null) sql += " and car.id = :carId ";
            if (filterDto.getNumDoors() != null) sql += " and car.num_doors = :numDoors ";
            if (StringUtils.isNotBlank(filterDto.getColor())) sql += " and LOWER(car.color) = :color ";
            if (filterDto.getEngineType() != null && filterDto.getEngineType().getId() != null) sql += " and engineType.id = :engineTypeId ";
            if (filterDto.getEngineType() != null && StringUtils.isNotBlank(filterDto.getEngineType().getName())) sql += " and LOWER(engineType.name) = :engineTypeName ";
            if (filterDto.getCarBrand() != null && filterDto.getCarBrand().getId() != null) sql += " and carBrand.id = :carBrandId ";
            if (filterDto.getCarBrand() != null && StringUtils.isNotBlank(filterDto.getCarBrand().getName())) sql += " and LOWER(carBrand.name) = :carBrandName ";
            if (filterDto.getCarModel() != null && filterDto.getCarModel().getId() != null) sql += " and carModel.id = :carModelId ";
            if (filterDto.getCarModel() != null && StringUtils.isNotBlank(filterDto.getCarModel().getName())) sql += " and LOWER(carModel.name) = :carModelName ";
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNativeQuery(sql);
        if (filterDto != null) {
            if (filterDto.getId() != null) query.setParameter("carId", filterDto.getId());
            if (filterDto.getNumDoors() != null) query.setParameter("numDoors", filterDto.getNumDoors());
            if (StringUtils.isNotBlank(filterDto.getColor())) query.setParameter("color", StringUtils.lowerCase(filterDto.getColor()));
            if (filterDto.getEngineType() != null && filterDto.getEngineType().getId() != null) query.setParameter("engineTypeId", filterDto.getEngineType().getId());
            if (filterDto.getEngineType() != null && StringUtils.isNotBlank(filterDto.getEngineType().getName())) query.setParameter("engineTypeName", StringUtils.lowerCase(filterDto.getEngineType().getName()));
            if (filterDto.getCarBrand() != null && filterDto.getCarBrand().getId() != null) query.setParameter("carBrandId", filterDto.getCarBrand().getId());
            if (filterDto.getCarBrand() != null && StringUtils.isNotBlank(filterDto.getCarBrand().getName())) query.setParameter("carBrandName", StringUtils.lowerCase(filterDto.getCarBrand().getName()));
            if (filterDto.getCarModel() != null && filterDto.getCarModel().getId() != null) query.setParameter("carModelId", filterDto.getCarModel().getId());
            if (filterDto.getCarModel() != null && StringUtils.isNotBlank(filterDto.getCarModel().getName())) query.setParameter("carModelName", StringUtils.lowerCase(filterDto.getCarModel().getName()));
        }
        List<Object[]> resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            CarDto carDto;
            for (Object[] o : resultList) {
                carDto = formDTO(o);
                cars.add(carDto);
            }
        }
        return cars;
    }

}
