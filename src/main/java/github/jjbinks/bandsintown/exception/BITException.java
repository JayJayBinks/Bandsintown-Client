package github.jjbinks.bandsintown.exception;

import javax.ws.rs.core.Response;

public class BITException extends Exception {

    private Response.Status httpStatus;

    public BITException(Response.Status httpStatus, Exception cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public BITException(Response.Status httpStatus) {
        super("");
        this.httpStatus = httpStatus;
    }

    public BITException(Response.Status httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BITException(String message) {
        super(message);
    }

    public BITException(String message, Exception e) {
        super(message, e);
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Response.Status httpStatus) {
        this.httpStatus = httpStatus;
    }
}
