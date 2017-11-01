package github.jjbinks.bandsintown.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.jjbinks.bandsintown.dto.Artist;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.dto.BITError;
import github.jjbinks.bandsintown.exception.BITException;
import github.jjbinks.bandsintown.impl.resource.ArtistEventsResource;
import github.jjbinks.bandsintown.impl.resource.ArtistInfoResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static github.jjbinks.bandsintown.TestParametersUnitTest.INVALID_APP_ID;
import static github.jjbinks.bandsintown.TestParametersUnitTest.VALID_APP_ID;
import static github.jjbinks.bandsintown.TestParametersUnitTest.VALID_ARTIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class BITAPIClientImplTest{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Artist mockArtist = new Artist();
    private List<ArtistEvent> mockEvents = new ArrayList<>();
    private ArtistEvent mockArtistEvent = new ArtistEvent();

    @Mock
    private WebTarget mockWebTarget;
    @Mock
    private Invocation.Builder mockInvocationBuilder;

    @Mock
    private Response response;

    @Mock
    private Client client = ClientBuilder.newClient();

    private BITAPIClientImpl bitapiClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getArtist() throws Exception {
        //given
        mockArtist.setName(VALID_ARTIST);

        mockResponse(Response.Status.OK.getStatusCode(), OBJECT_MAPPER.writeValueAsString(mockArtist));
        mockRestClient();

        bitapiClient = new BITAPIClientImpl(client, VALID_APP_ID);
        //when
        Artist artist =  bitapiClient.getBITResource(new ArtistInfoResource(VALID_ARTIST));
        //then
        assert(artist.getName().equals(VALID_ARTIST));
    }

    @Test(expected = BITException.class)
    public void getInvalidArtist() throws Exception {
        //given
        //mockArtist is not setup
        mockResponse(Response.Status.OK.getStatusCode(), OBJECT_MAPPER.writeValueAsString(mockArtist));
        mockRestClient();
        bitapiClient = new BITAPIClientImpl(client, VALID_APP_ID);
        //when
        Artist artist =  bitapiClient.getBITResource(new ArtistInfoResource(VALID_ARTIST));
    }

    @Test
    public void getEventsWithDate() throws Exception {
        //given
        mockEvents.add(mockArtistEvent);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusYears(10);
        mockResponse(Response.Status.OK.getStatusCode(), OBJECT_MAPPER.writeValueAsString(mockEvents));
        mockRestClient();
        bitapiClient = new BITAPIClientImpl(client, VALID_APP_ID);
        //when
        List<ArtistEvent> artistEvents =  bitapiClient.getBITResource(new ArtistEventsResource(VALID_ARTIST, fromDate, toDate));
        //then
        assert(artistEvents.size() == mockEvents.size());
    }

    @Test(expected = BITException.class)
    public void getWithInvalidAppId() throws Exception {
        //given
        //mockArtist is not setup
        mockResponse(Response.Status.BAD_REQUEST.getStatusCode(), new BITError());
        mockRestClient();
        bitapiClient = new BITAPIClientImpl(client, INVALID_APP_ID);
        //when
        Artist artist =  bitapiClient.getBITResource(new ArtistInfoResource(VALID_ARTIST));
    }

    private void mockRestClient() {
        when(mockInvocationBuilder.method(anyString())).thenReturn(response);
        when(mockWebTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(mockInvocationBuilder);

        when(mockWebTarget.queryParam(anyString(), any())).thenReturn(mockWebTarget);
        when(client.target(any(URI.class))).thenReturn(mockWebTarget);
    }

    private void  mockResponse(int statusCode, Object entity) throws JsonProcessingException {
        when(response.getStatus()).thenReturn(statusCode);
        when(response.getMediaType()).thenReturn(MediaType.APPLICATION_JSON_TYPE);
        when(response.readEntity(any(Class.class))).thenReturn(entity);
    }

}