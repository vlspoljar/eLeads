package com.eleads.ws;

import com.eleads.dao.CarModelDao;
import com.eleads.dto.CarModelDto;
import com.eleads.dto.StatusInfoDto;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Vlatko
 */
@Path("carModelResource")
@Produces("application/json")
@RequestScoped
public class CarModelResource {

    @Inject CarModelDao carModelDao;

    @GET
    @Path("findAllCarModels")
    public StatusInfoDto<CarModelDto> findAllCarModels() {
        StatusInfoDto<CarModelDto> result = null;
        try {
            List<CarModelDto> response = carModelDao.findAll();
            if (response != null && !response.isEmpty()) {
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Car models fetched.", "findAllCarModels", response);
            } else {
                result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllCarModels");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllCarModels");
        }
        return result;
    }

    @GET
    @Path("findCarModelById/{id}")
    public StatusInfoDto<CarModelDto> findCarModelById(@PathParam("id") Integer id) {
        StatusInfoDto<CarModelDto> result = null;
        if (id != null) {
            try {
                CarModelDto response = carModelDao.findByPrimaryKey(id);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Car model fetched.", "findCarModelById", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findCarModelById");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findCarModelById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "findCarModelById");
        }
        return result;
    }

}
