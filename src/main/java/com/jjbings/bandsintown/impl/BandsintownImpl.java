package com.jjbings.bandsintown.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.jjbings.bandsintown.api.BandsintownAPI;
import com.jjbings.bandsintown.dto.ArtistEvent;
import com.jjbings.bandsintown.exception.BandsintownException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.jjbings.bandsintown.util.BandsintownParameters.*;

public class BandsintownImpl implements BandsintownAPI{

    private static final ObjectMapper mapper = new ObjectMapper();

    private Client client;

    //Mandatory parameter to be sent with each request
    private String appId;

    /**
     * This should only be invoked by the BandsintownApiBuilder
     * @param appId Can be anything describing the application
     * @param client JAX RS rest client
     */
    protected BandsintownImpl(String appId, Client client) {
        this.appId = appId;
        this.client = client;
        this.client.register(JacksonJsonProvider.class);
    }

    public Optional<List<ArtistEvent>> getEventsForArtist(String artist){
        WebTarget target = buildPath(String.format(ARTISTS_PATH, artist), EVENTS_PATH);
        Invocation.Builder invocationBuilder = getInvocationBuilder(target);

        String json = handleResponse(invocationBuilder.buildGet().invoke());
        return readArtistEvents(artist, json);
    }

    private Optional<List<ArtistEvent>> readArtistEvents(String artist, String json) {
        Optional<List<ArtistEvent>> artistEvents = Optional.empty();
        try {
            List<ArtistEvent> artistEventsList = mapper.readValue(json, new TypeReference<List<ArtistEvent>>(){});
            if(artistEventsList.isEmpty() == false){
                artistEvents = Optional.of(artistEventsList);
            }
        } catch (IOException e) {
            throw new BandsintownException("Response could not be read!", e);
        }
        artistEvents.ifPresent(x -> x.forEach(artistEvent -> artistEvent.setArtist(artist)));
        return artistEvents;
    }

    private String handleResponse(Response response) {
        if(response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE) == false){
            throw new BandsintownException("Media type not supported: " + response.getMediaType());
        }
        Response.Status responseStatus = Response.Status.fromStatusCode(response.getStatus());
        switch (responseStatus){
            case OK:
                return response.readEntity(String.class);
            case FORBIDDEN:
                throw new BandsintownException("Access was forbidden. Did you set an appId?");
            default:
                throw new BandsintownException("Response type not identified HTTP:" + responseStatus);
        }

    }

    private Invocation.Builder getInvocationBuilder(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON_TYPE);
    }

    private WebTarget buildPath(String... pathParams){
        WebTarget target = client.target(BASE_URI);
        for(String path : pathParams){
            target = target.path(path);
        }
        return target.queryParam(APPID_QUERY_PARAM, appId);
    }

}
