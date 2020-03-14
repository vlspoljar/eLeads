package com.eleads.ws;

import com.eleads.dao.CarDao;
import com.eleads.dto.CarDto;
import com.eleads.dto.StatusInfoDto;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Vlatko
 */
@Path("carResource")
@Produces("application/json")
@RequestScoped
public class CarResource {

    @Inject CarDao carDao;

    @GET
    @Path("findAllCars")
    public StatusInfoDto<CarDto> findAllCars() {
        StatusInfoDto<CarDto> result = null;
        try {
            List<CarDto> response = carDao.findAll();
            if (response != null && !response.isEmpty()) {
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Cars fetched.", "findAllCars", response);
            } else {
                result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllCars");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllCars");
        }
        return result;
    }

    @GET
    @Path("findCarById/{id}")
    public StatusInfoDto<CarDto> findAllCarById(@PathParam("id") Integer id) {
        StatusInfoDto<CarDto> result = null;
        if (id != null) {
            try {
                CarDto response = carDao.findByPrimaryKey(id);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Car fetched.", "findAllCarById", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllCarById");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllCarById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "findAllCarById");
        }
        return result;
    }

    @POST
    @Path("/filterCars")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StatusInfoDto<CarDto> filterCars(CarDto filterDto) {
        StatusInfoDto<CarDto> result = null;
        if (filterDto != null) {
            try {
                List<CarDto> response = carDao.filterCars(filterDto);
                if (response != null && !response.isEmpty()) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Cars filtered.", "filterCars", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "filterCars");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "filterCars");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Filter is empty.", "filterCars");
        }
        return result;
    }

}
