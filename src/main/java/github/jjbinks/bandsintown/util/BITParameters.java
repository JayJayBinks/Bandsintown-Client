package github.jjbinks.bandsintown.util;

import javax.ws.rs.core.UriBuilder;

public class BITParameters {

    private static final String BASE_URI = "https://rest.bandsintown.com/";
    private static final String ARTISTS_PATH = "artists/{artist}";
    private static final String EVENTS_PATH = "events";

    public static final String APPID_QUERY_PARAM = "app_id";
    public static final String DATE_QUERY_PARAM = "date";

    public static final UriBuilder ARTIST_EVENTS_URI_BUILDER = UriBuilder.fromUri(BASE_URI)
            .path(ARTISTS_PATH)
            .path(EVENTS_PATH);

    public static final UriBuilder ARTIST_URI_BUILDER = UriBuilder.fromUri(BASE_URI)
            .path(ARTISTS_PATH);

}
