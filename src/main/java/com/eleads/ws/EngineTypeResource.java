package com.eleads.ws;

import com.eleads.dao.EngineTypeDao;
import com.eleads.dto.EngineTypeDto;
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
@Path("engineTypeResource")
@Produces("application/json")
@RequestScoped
public class EngineTypeResource {

    @Inject EngineTypeDao engineTypeDao;

    @GET
    @Path("findAllEngineTypes")
    public StatusInfoDto<EngineTypeDto> findAllEngineTypes() {
        StatusInfoDto<EngineTypeDto> result = null;
        try {
            List<EngineTypeDto> response = engineTypeDao.findAll();
            if (response != null && !response.isEmpty()) {
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Engine types fetched.", "findAllEngineTypes", response);
            } else {
                result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllEngineTypes");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllEngineTypes");
        }
        return result;
    }

    @GET
    @Path("findEngineTypeById/{id}")
    public StatusInfoDto<EngineTypeDto> findEngineTypeById(@PathParam("id") Integer id) {
        StatusInfoDto<EngineTypeDto> result = null;
        if (id != null) {
            try {
                EngineTypeDto response = engineTypeDao.findByPrimaryKey(id);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Engine type fetched.", "findEngineTypeById", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findEngineTypeById");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findEngineTypeById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "findEngineTypeById");
        }
        return result;
    }

}
