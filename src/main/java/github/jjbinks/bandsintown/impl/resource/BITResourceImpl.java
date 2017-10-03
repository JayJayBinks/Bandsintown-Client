package github.jjbinks.bandsintown.impl.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.jjbinks.bandsintown.api.BITResource;
import github.jjbinks.bandsintown.exception.BITException;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public abstract class BITResourceImpl implements BITResource {
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected final URI targetURI;
    protected final String httpMethod;

    protected Class dtoClass;

    public BITResourceImpl(URI targetURI, String httpMethod) {
       this(targetURI, httpMethod, null);
    }

    public BITResourceImpl(URI targetURI, String httpMethod, Class dtoClass) {
        Objects.requireNonNull(targetURI);
        Objects.requireNonNull(httpMethod);
        this.targetURI = targetURI;
        this.httpMethod = httpMethod;
        this.dtoClass = dtoClass;
    }

    protected abstract void validateResponse(String json) throws BITException;

    public <T> T readResponseEntity(String json) throws BITException {
        Objects.requireNonNull(dtoClass);
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
    public Class getDtoClass() {
        return dtoClass;
    }

    @Override
    public void setDtoClass(Class dtoClass) {
        this.dtoClass = dtoClass;
    }


}
