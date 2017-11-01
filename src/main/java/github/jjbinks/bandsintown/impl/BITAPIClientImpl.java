package github.jjbinks.bandsintown.impl;

import github.jjbinks.bandsintown.api.BITAPIClient;
import github.jjbinks.bandsintown.api.BITResource;
import github.jjbinks.bandsintown.dto.BITError;
import github.jjbinks.bandsintown.exception.BITException;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static github.jjbinks.bandsintown.util.BITParameters.APPID_QUERY_PARAM;

public class BITAPIClientImpl implements BITAPIClient{

    private final Client restClient;
    //Mandatory parameter to be sent with each request
    private final String appId;

    public BITAPIClientImpl(Client client, String appId) {
        Objects.requireNonNull(client);
        this.restClient = client;
        //appId validation should be handled by server
        this.appId = appId;
    }

    @Override
    public <T> T getBITResource(BITResource bitResource) throws BITException {
        return bitResource.readResponseEntity(
                        handleResponse(
                                restCall(bitResource, appId)));
    }


    private String handleResponse(Response response) throws BITException {
        if(response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE) == false){
            throw new BITException("Media type not supported: " + response.getMediaType());
        }
        Response.Status responseStatus = Response.Status.fromStatusCode(response.getStatus());
        switch (responseStatus){
            case OK:
                return response.readEntity(String.class);
            case FORBIDDEN:
            case BAD_REQUEST:
                throw new BITException(responseStatus, response.readEntity(BITError.class).toString());
            default:
                throw new BITException(responseStatus);
        }

    }

    private Response restCall(BITResource bitResource, String appId){
        WebTarget webTarget =  restClient.target(bitResource.getTargetURI())
                .queryParam(APPID_QUERY_PARAM, appId);

        Map<String, Object> additionalQueryParams = bitResource.getAdditionalQuereyParams();

        for(Map.Entry<String, Object> entry : additionalQueryParams.entrySet()) {
            webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
        }

        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).method(bitResource.getHttpMethod());
    }

    @Override
    public Client getRestClient() {
        return restClient;
    }

    @Override
    public String getAppId() {
        return appId;
    }


}
