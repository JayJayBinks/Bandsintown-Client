package github.jjbinks.bandsintown.impl.resource;

import github.jjbinks.bandsintown.exception.BITException;

import javax.ws.rs.core.UriBuilder;

public abstract class ArtistResource extends BITResourceImpl {

    protected final String artist;

    public ArtistResource(String artist, UriBuilder uriBuilder, String httpMethod) {
       this(artist, uriBuilder, httpMethod, null);
    }

    public ArtistResource(String artist, UriBuilder uriBuilder, String httpMethod, Class dtoClass) {
        super(uriBuilder.build(artist), httpMethod, dtoClass);
        this.artist = artist;
    }

    @Override
    protected void validateResponse(String json) throws BITException {
        if(json.contains(artist) == false){
            throw new BITException("Information for this artist were not found!");
        }
    }
}
