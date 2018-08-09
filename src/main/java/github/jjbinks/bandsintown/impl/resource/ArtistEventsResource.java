package github.jjbinks.bandsintown.impl.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;
import github.jjbinks.bandsintown.util.BITParameters;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static github.jjbinks.bandsintown.util.BITParameters.ARTIST_EVENTS_URI_BUILDER;

public class ArtistEventsResource extends ArtistResource {

    public ArtistEventsResource(String artist) {
        super(artist, ARTIST_EVENTS_URI_BUILDER, HttpMethod.GET);
    }

    public ArtistEventsResource(String artist, LocalDate fromDate, LocalDate toDate) {
        super(artist, ARTIST_EVENTS_URI_BUILDER, HttpMethod.GET, null, datesToQueryParams(fromDate, toDate));
    }

    private static Map<String, Object> datesToQueryParams(LocalDate fromDate, LocalDate toDate) {
        HashMap<String, Object> additionalQueryParameters = new HashMap<>();

        additionalQueryParameters.put(BITParameters.DATE_QUERY_PARAM,
                fromDate.toString() + "," + toDate.toString());

        return additionalQueryParameters;
    }

    @Override
    public <T> T readResponseEntity(String json) throws BITException {
        try {
            return MAPPER.readValue(json, new TypeReference<List<ArtistEvent>>() {
            });
        } catch (IOException e) {
            throw new BITException("Response could not be read!", e);
        }
    }

}
