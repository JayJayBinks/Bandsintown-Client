package github.jjbinks.bandsintown.api;

import github.jjbinks.bandsintown.exception.BITException;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Returns the specific Object or throws an BITException on error
 */
public interface BITResource {
    <T> T readResponseEntity(String json) throws BITException;
    URI getTargetURI();
    String getHttpMethod();
    Optional<Class> getDtoClass();
    Map<String, Object> getAdditionalQueryParams();
}
