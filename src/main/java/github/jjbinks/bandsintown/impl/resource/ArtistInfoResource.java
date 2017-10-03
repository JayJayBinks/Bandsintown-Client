package github.jjbinks.bandsintown.impl.resource;

import github.jjbinks.bandsintown.dto.Artist;

import javax.ws.rs.HttpMethod;

import static github.jjbinks.bandsintown.util.BITParameters.ARTIST_URI_BUILDER;

public class ArtistInfoResource extends ArtistResource {

    public ArtistInfoResource(String artist) {
        super(artist, ARTIST_URI_BUILDER, HttpMethod.GET, Artist.class);
    }

}
