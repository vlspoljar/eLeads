package com.eleads.dto;

import java.io.Serializable;

/**
 * Created by Vlatko
 */
public class StatusInfoDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // constants
    public static final String STATUS_SUCCESS = "info";
    public static final String STATUS_FAILURE = "error";
    public static final String STATUS_WARNING = "warning";

    // fields
    private String type;
    private String description;
    private String actionType;
    private T response;

    public StatusInfoDto() {
        super();
    }

    // constructors
    public StatusInfoDto(String type, String description, String actionType) {
        super();
        this.type = type;
        this.description = description;
        this.actionType = actionType;
    }

    public StatusInfoDto( String type, String description, String actionType, T response) {
        super();
        this.type = type;
        this.description = description;
        this.actionType = actionType;
        this.response = response;
    }

    // getters and setters
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
