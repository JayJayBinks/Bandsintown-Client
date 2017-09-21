package com.jjbings.bandsintown.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjbings.bandsintown.dto.ArtistEvent;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class BandsintownImplTest {

    private static final EnhancedRandom ENHANCED_RANDOM = EnhancedRandomBuilder.aNewEnhancedRandom();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static String APP_ID = "testId";
    private static String VALID_ARTIST = "Grouplove";
    private static String INVALID_ARTIST = "This is invalid";

    private Optional<List<ArtistEvent>> artistEvents;

    private String responseEntity;
    private int numberOfEvents;

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder invocationBuilder;

    @Mock
    private Invocation invocation;

    @Mock
    private Response response;

    @InjectMocks
    private BandsintownImpl bandsintownImpl;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getEventsForArtist() throws Exception {
        //given
        aValidEventEntity(numberOfEvents);
        mockResponse(Response.Status.OK);
        mockRestCall();
        //when
        artistEvents = bandsintownImpl.getEventsForArtist(VALID_ARTIST);
        //then
        assert(artistEvents.isPresent());
        assert(artistEvents.get().size() == numberOfEvents);
    }

    private void mockRestCall() {
        when(invocation.invoke()).thenReturn(response);
        when(invocationBuilder.buildGet()).thenReturn(invocation);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(invocationBuilder);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.queryParam(anyString(), any())).thenReturn(webTarget);
        when(client.target(anyString())).thenReturn(webTarget);
    }

    private void mockResponse(Response.Status httpStatus) {
        when(response.getMediaType()).thenReturn(MediaType.APPLICATION_JSON_TYPE);
        when(response.readEntity(String.class)).thenReturn(responseEntity);
        when(response.getStatus()).thenReturn(httpStatus.getStatusCode());
    }

    private void aValidEventEntity(int numberOfEvents) {
        ArrayList<ArtistEvent> eventResponse = new ArrayList<>();
        for(int i = 0; i < numberOfEvents; i++){
            eventResponse.add(ENHANCED_RANDOM.nextObject(ArtistEvent.class));
        }
        try {
            responseEntity = MAPPER.writeValueAsString(eventResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail("Response could not be parsed to JSON");
        }
    }

}