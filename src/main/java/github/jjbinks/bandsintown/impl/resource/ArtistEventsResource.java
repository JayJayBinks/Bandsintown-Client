package github.jjbinks.bandsintown.impl.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.util.List;

import static github.jjbinks.bandsintown.util.BITParameters.ARTIST_EVENTS_URI_BUILDER;

public class ArtistEventsResource extends ArtistResource {

    public ArtistEventsResource(String artist) {
        super(artist, ARTIST_EVENTS_URI_BUILDER, HttpMethod.GET);
    }

    @Override
    public <T> T readResponseEntity(String json) throws BITException {
        try {
            List<ArtistEvent> artistEventsList = MAPPER.readValue(json, new TypeReference<List<ArtistEvent>>(){});
            for(ArtistEvent artistEvent : artistEventsList){
                artistEvent.setArtist(artist);
            }
            return (T) artistEventsList;
        } catch (IOException e) {
            throw new BITException("Response could not be read!", e);
        }
    }

}
