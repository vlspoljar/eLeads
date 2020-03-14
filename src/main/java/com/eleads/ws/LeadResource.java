package com.eleads.ws;

import com.eleads.dao.LeadDao;
import com.eleads.dto.LeadDto;
import com.eleads.dto.StatusInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Vlatko
 */
@Path("leadResource")
@Produces("application/json")
@RequestScoped
public class LeadResource {

    @Inject LeadDao leadDao;

    @POST
    @Path("/createLead")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StatusInfoDto<LeadDto> createLead(LeadDto leadDto) {
        StatusInfoDto<LeadDto> result = null;
        if (leadDto != null) {
            try {
                // validation
                if (StringUtils.isBlank(leadDto.getFirstName())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "First name is empty.", "createLead");
                if (StringUtils.isBlank(leadDto.getLastName())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Last name is empty.", "createLead");
                if (StringUtils.isBlank(leadDto.getCity())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "City is empty.", "createLead");
                if (leadDto.getCars() == null || leadDto.getCars().isEmpty()) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Cars list is empty.", "createLead");
                // create
                LeadDto response = leadDao.create(leadDto);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Lead created.", "createLead", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "createLead");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "createLead");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Lead is empty.", "createLead");
        }
        return result;
    }

    @POST
    @Path("/editLead")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StatusInfoDto<LeadDto> editLead(LeadDto leadDto) {
        StatusInfoDto<LeadDto> result = null;
        if (leadDto != null) {
            try {
                // validation
                if (leadDto.getId() == null) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "editLead");
                if (StringUtils.isBlank(leadDto.getFirstName())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "First name is empty.", "editLead");
                if (StringUtils.isBlank(leadDto.getLastName())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Last name is empty.", "editLead");
                if (StringUtils.isBlank(leadDto.getCity())) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "City is empty.", "editLead");
                if (leadDto.getCars() == null || leadDto.getCars().isEmpty()) return new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Cars list is empty.", "editLead");
                // edit
                LeadDto response = leadDao.edit(leadDto);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Lead edited.", "editLead", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "editLead");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "editLead");
            }
        }
        return result;
    }

    @GET
    @Path("deleteLeadById/{id}")
    public StatusInfoDto<?> deleteLeadById(@PathParam("id") Integer id) {
        StatusInfoDto<?> result = null;
        if (id != null) {
            try {
                leadDao.delete(id);
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Lead deleted.", "deleteLeadById");
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "deleteLeadById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "deleteLeadById");
        }
        return result;
    }

    @GET
    @Path("findAllLeads")
    public StatusInfoDto<LeadDto> findAllLeads() {
        StatusInfoDto<LeadDto> result = null;
        try {
            List<LeadDto> response = leadDao.findAll();
            if (response != null && !response.isEmpty()) {
                result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Leads fetched.", "findAllLeads", response);
            } else {
                result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findAllLeads");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findAllLeads");
        }
        return result;
    }

    @GET
    @Path("findLeadById/{id}")
    public StatusInfoDto<LeadDto> findLeadById(@PathParam("id") Integer id) {
        StatusInfoDto<LeadDto> result = null;
        if (id != null) {
            try {
                LeadDto response = leadDao.findByPrimaryKey(id);
                if (response != null) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Lead fetched.", "findLeadById", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "findLeadById");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "findLeadById");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "ID is empty.", "findLeadById");
        }
        return result;
    }

    @POST
    @Path("/filterLeads")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StatusInfoDto<LeadDto> filterLeads(LeadDto filterDto) {
        StatusInfoDto<LeadDto> result = null;
        if (filterDto != null) {
            try {
                List<LeadDto> response = leadDao.filterLeads(filterDto);
                if (response != null && !response.isEmpty()) {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_SUCCESS, "Leads filtered.", "filterLeads", response);
                } else {
                    result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Response is empty.", "filterLeads");
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
                result = new StatusInfoDto(StatusInfoDto.STATUS_FAILURE, "There happened internal error in application.", "filterLeads");
            }
        } else {
            result = new StatusInfoDto(StatusInfoDto.STATUS_WARNING, "Filter is empty.", "filterLeads");
        }
        return result;
    }

}
