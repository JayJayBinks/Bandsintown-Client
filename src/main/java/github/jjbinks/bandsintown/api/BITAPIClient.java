package github.jjbinks.bandsintown.api;

import github.jjbinks.bandsintown.exception.BITException;

import javax.ws.rs.client.Client;

public interface BITAPIClient {
    <T> T getBITResource(BITResource bitResource) throws BITException;
    Client getRestClient();
    String getAppId();
}
