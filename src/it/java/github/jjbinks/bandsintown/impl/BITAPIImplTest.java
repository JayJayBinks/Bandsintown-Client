package github.jjbinks.bandsintown.impl;

import github.jjbinks.bandsintown.api.BITAPI;
import github.jjbinks.bandsintown.dto.Artist;
import github.jjbinks.bandsintown.dto.ArtistEvent;
import github.jjbinks.bandsintown.exception.BITException;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import javax.ws.rs.client.Client;
import java.time.LocalDate;
import java.util.List;

import static github.jjbinks.bandsintown.TestParameters.*;

public class BITAPIImplTest {

    private BITAPI bitapi;
    private List<ArtistEvent> artistEventList;
    private Artist artist;

    @Test
    public void getArtistEvents() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artistEventList = bitapi.getArtistEvents(VALID_ARTIST);
        //then
        assert(artistEventList.isEmpty() == false);
    }

    @Test
    public void getArtistEventsWithDate() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusYears(10);
        //when
        artistEventList = bitapi.getArtistEvents(VALID_ARTIST, fromDate , toDate);
        //then
        assert(artistEventList.isEmpty() == false);
    }

    @Test(expected = BITException.class)
    public void getArtistEventsWithInvalidDate() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.minusYears(10);
        //when
        artistEventList = bitapi.getArtistEvents(VALID_ARTIST, fromDate , toDate);
    }

    @Test(expected = BITException.class)
    public void getArtistEventsWithInvalidArtist() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artistEventList = bitapi.getArtistEvents(INVALID_ARTIST);
    }

    @Test
    public void getArtist() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artist = bitapi.getArtist(VALID_ARTIST);
        //then
        assert(artist.getName().equals(VALID_ARTIST));
    }

    @Test
    public void getArtistWithCustomClient() throws Exception {
        //given
        Client client = JerseyClientBuilder.createClient();
        bitapi = new BITAPIImpl(client, VALID_APP_ID);
        //when
        artist = bitapi.getArtist(VALID_ARTIST);
        //then
        assert(artist.getName().equals(VALID_ARTIST));
    }

    @Test(expected = BITException.class)
    public void getArtistWithInvalidArtist() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artist = bitapi.getArtist(INVALID_ARTIST);
    }

    @Test(expected = BITException.class)
    public void getArtistWithInvalidAppId() throws Exception {
        //given
        bitapi = new BITAPIImpl(INVALID_APP_ID);
        //when
        artist = bitapi.getArtist(VALID_ARTIST);
    }



}