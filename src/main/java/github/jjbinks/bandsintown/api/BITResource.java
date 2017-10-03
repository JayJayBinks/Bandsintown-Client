package github.jjbinks.bandsintown.api;

import github.jjbinks.bandsintown.exception.BITException;

import java.net.URI;

/**
 * Returns the specific Object or throws an BITException on error
 */
public interface BITResource {
    <T> T readResponseEntity(String json) throws BITException;
    URI getTargetURI();
    String getHttpMethod();
    Class getDtoClass();
    void setDtoClass(Class dtoClass);
}
