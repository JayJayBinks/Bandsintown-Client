package github.jjbinks.bandsintown.impl.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.jjbinks.bandsintown.api.BITResource;
import github.jjbinks.bandsintown.exception.BITException;
import javassist.scopedpool.SoftValueHashMap;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class BITResourceImpl implements BITResource {
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected final URI targetURI;
    protected final String httpMethod;
    protected final Optional<Class> dtoClassOptional;
    protected final Map<String, Object> additionalQueryParams;

    public BITResourceImpl(URI targetURI, String httpMethod) {
       this(targetURI, httpMethod, Optional.empty(), new HashMap<>());
    }

    public BITResourceImpl(URI targetURI, String httpMethod, Optional<Class> dtoClass,
                           Map<String, Object> additionalQueryParams) {

        Objects.requireNonNull(targetURI);
        Objects.requireNonNull(httpMethod);
        this.targetURI = targetURI;
        this.httpMethod = httpMethod;
        this.dtoClassOptional = dtoClass;
        this.additionalQueryParams = additionalQueryParams;
    }

    protected abstract void validateResponse(String json) throws BITException;

    public <T> T readResponseEntity(String json) throws BITException {
        Class dtoClass = dtoClassOptional.orElseThrow(() -> new BITException("DTO class needs to be set!"));
        validateResponse(json);
        try {
            return (T) MAPPER.readValue(json, dtoClass);
        } catch (IOException e) {
            throw new BITException("Response could not be read!", e);
        }
    }

    @Override
    public URI getTargetURI() {
        return targetURI;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Optional<Class> getDtoClass() {
        return dtoClassOptional;
    }

    @Override
    public Map<String, Object> getAdditionalQuereyParams() {
        return additionalQueryParams;
    }
}
