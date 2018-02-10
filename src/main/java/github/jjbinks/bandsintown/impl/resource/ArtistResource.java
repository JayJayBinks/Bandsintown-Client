package github.jjbinks.bandsintown.impl.resource;

import github.jjbinks.bandsintown.exception.BITException;

import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ArtistResource extends BITResourceImpl {

    protected final String artist;

    public ArtistResource(String artist, UriBuilder uriBuilder, String httpMethod) {
       this(artist, uriBuilder, httpMethod, null, new HashMap<>());
    }

    public ArtistResource(String artist, UriBuilder uriBuilder, String httpMethod, Class dtoClass, Map<String, Object> additionalQueryParamas) {
        super(uriBuilder.build(artist), httpMethod, Optional.ofNullable(dtoClass), additionalQueryParamas);
        this.artist = artist;
    }

    @Override
    protected void validateResponse(String json) throws BITException {
        if(json.contains(artist) == false){
            throw new BITException("Information for this artist were not found!");
        }
    }
}
