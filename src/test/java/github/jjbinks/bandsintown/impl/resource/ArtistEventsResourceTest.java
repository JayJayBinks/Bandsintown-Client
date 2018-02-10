package github.jjbinks.bandsintown.impl.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.TestParametersUnitTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArtistEventsResourceTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private List<ArtistEvent> mockArtistEvents = new ArrayList<>();

    private ArtistEventsResource artistEventsResource = new ArtistEventsResource(TestParametersUnitTest.VALID_ARTIST);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readResponseEntity() throws Exception {
        //given
        mockArtistEvents.add(new ArtistEvent());
        String responseEntity = OBJECT_MAPPER.writeValueAsString(mockArtistEvents);
        //when
        List<ArtistEvent> artistEvents = artistEventsResource.readResponseEntity(responseEntity);
        //then
        assert(artistEvents.size() == mockArtistEvents.size());
        //object equality for each object in list is not tested
    }

}