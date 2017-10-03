package github.jjbinks.bandsintown.impl;

import github.jjbinks.bandsintown.api.BITAPI;
import github.jjbinks.bandsintown.api.BITAPIClient;
import github.jjbinks.bandsintown.dto.Artist;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;
import github.jjbinks.bandsintown.impl.resource.ArtistEventsResource;
import github.jjbinks.bandsintown.impl.resource.ArtistInfoResource;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import java.util.List;

public class BITAPIImpl implements BITAPI {

    private BITAPIClient bitAPIClient;

    public BITAPIImpl(String appId) {
        this(JerseyClientBuilder.createClient(), appId);
    }

    public BITAPIImpl(Client restClient, String appId) {
        bitAPIClient = new BITAPIClientImpl(restClient, appId);
    }


    @Override
    public List<ArtistEvent> getArtistEvents(String artist) throws BITException {
        return bitAPIClient.getBITResource(new ArtistEventsResource(artist));
    }

    @Override
    public Artist getArtist(String artist) throws BITException {
        return bitAPIClient.getBITResource(new ArtistInfoResource(artist));
    }

}
