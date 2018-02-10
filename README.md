# Bandsintown-Client
[![Build Status](https://travis-ci.org/JayJayBinks/Bandsintown-API.svg?branch=master)](https://travis-ci.org/JayJayBinks/Bandsintown-API.svg?branch=master)
[![BCH compliance](https://bettercodehub.com/edge/badge/JayJayBinks/Bandsintown-API?branch=master)](https://bettercodehub.com/edge/badge/JayJayBinks/Bandsintown-API?branch=master)

This is a Java 8 Client for accessing the Bandsintown API v3
https://app.swaggerhub.com/apis/Bandsintown/PublicAPI/3.0.0

Usage can best be seen via the integration tests.

    // This will simply return the artist information described in the API as DTO
    public void getArtist() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artist = bitapi.getArtist(VALID_ARTIST);
        //then
        assert(artist.getName().equals(VALID_ARTIST));
    }

    // In case there is an error e.g. the artist does not exist
    // an BITException will be thrown containing the error description from the server
    @Test(expected = BITException.class)
    public void getArtistWithInvalidArtist() throws Exception {
        //given
        bitapi = new BITAPIImpl(VALID_APP_ID);
        //when
        artist = bitapi.getArtist(INVALID_ARTIST);
    }

    // this tests return the BITException: Information for this artist were not found!
