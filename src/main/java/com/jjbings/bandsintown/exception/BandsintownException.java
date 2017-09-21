package com.jjbings.bandsintown.exception;

import javax.ws.rs.core.Response;

public class BandsintownException extends RuntimeException {

    private Response.Status httpStatus;

    public BandsintownException(Response.Status httpStatus, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public BandsintownException(String message, Throwable e) {
        super(message, e);
    }

    public BandsintownException(String message) {
        super(message);
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Response.Status httpStatus) {
        this.httpStatus = httpStatus;
    }
}
