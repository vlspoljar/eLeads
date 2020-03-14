package com.eleads.ws;

import com.eleads.dao.CarBrandDao;
import com.eleads.dto.CarBrandDto;
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
@Path("carBrandResource")
@Produces("application/json")
@RequestScoped
public class CarBrandResource {

    @Inject CarBrandDao carBrandDao;

    @GET
    @Path("findAllCarBrands")
    public StatusInfoDto<CarBrandDto> findAllCarBrands() {
        StatusInfoDto<CarBrandDto> result = null;
        try {
            List<CarBrandDto> response = carBrandDao.findAll();
            if (response != null && !response.isEmpty()) {
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Car brands fetched.", "findAllCarBrands", response);
            } else {
                result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllCarBrands");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllCarBrands");
        }
        return result;
    }

    @GET
    @Path("findCarBrandById/{id}")
    public StatusInfoDto<CarBrandDto> findCarBrandById(@PathParam("id") Integer id) {
        StatusInfoDto<CarBrandDto> result = null;
        if (id != null) {
            try {
                CarBrandDto response = carBrandDao.findByPrimaryKey(id);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Car brand fetched.", "findCarBrandById", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findCarBrandById");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findCarBrandById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "findCarBrandById");
        }
        return result;
    }

}
