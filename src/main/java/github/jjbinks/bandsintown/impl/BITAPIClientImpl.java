package github.jjbinks.bandsintown.impl;

import github.jjbinks.bandsintown.api.BITAPIClient;
import github.jjbinks.bandsintown.api.BITResource;
import github.jjbinks.bandsintown.exception.BITException;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static github.jjbinks.bandsintown.util.BITParameters.APPID_QUERY_PARAM;


public class BITAPIClientImpl implements BITAPIClient{

    private Client restClient;
    //Mandatory parameter to be sent with each request
    private final String appId;

    public BITAPIClientImpl(Client client, String appId) {
        this.restClient = client;
        this.appId = appId;
    }

    @Override
    public <T> T getBITResource(BITResource bitResource) throws BITException {
        return bitResource.readResponseEntity(
                        handleResponse(
                                restCall(
                                        bitResource.getHttpMethod(),
                                        bitResource.getTargetURI(),
                                        appId)));
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
                throw new BITException("Access was forbidden. Is the appId set?");
            default:
                throw new BITException("Response type not identified HTTP:" + responseStatus);
        }

    }

    private Response restCall(String httpMethod, URI target, String appId){
        return restClient.target(target)
                .queryParam(APPID_QUERY_PARAM, appId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .method(httpMethod);
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
