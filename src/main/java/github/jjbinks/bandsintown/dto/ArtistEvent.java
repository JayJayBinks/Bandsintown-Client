package github.jjbinks.bandsintown.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "artist",
        "artist_id",
        "url",
        "on_sale_datetime",
        "datetime",
        "description",
        "venue",
        "offers",
        "lineup"
})
public class ArtistEvent {

    @JsonProperty("id")
    private String id;
    @JsonProperty("artist")
    private String artist;
    @JsonProperty("artist_event_id")
    private String artistEventId;
    @JsonProperty("artist_id")
    private String artistId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("on_sale_datetime")
    private String onSaleDatetime;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("venue")
    private Venue venue;
    @JsonProperty("offers")
    private List<Offer> offers = null;
    @JsonProperty("lineup")
    private List<String> lineup = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("artist_event_id")
    public String getArtistEventId() {
        return artistEventId;
    }

    @JsonProperty("artist_event_id")
    public void setArtistEventId(String artistEventId) {
        this.artistEventId = artistEventId;
    }

    @JsonProperty("artist")
    public String getArtist() {
        return artist;
    }

    @JsonProperty("artist")
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @JsonProperty("artist_id")
    public String getArtistId() {
        return artistId;
    }

    @JsonProperty("artist_id")
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("on_sale_datetime")
    public String getOnSaleDatetime() {
        return onSaleDatetime;
    }

    @JsonProperty("on_sale_datetime")
    public void setOnSaleDatetime(String onSaleDatetime) {
        this.onSaleDatetime = onSaleDatetime;
    }

    @JsonProperty("datetime")
    public String getDatetime() {
        return datetime;
    }

    @JsonProperty("datetime")
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("venue")
    public Venue getVenue() {
        return venue;
    }

    @JsonProperty("venue")
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @JsonProperty("offers")
    public List<Offer> getOffers() {
        return offers;
    }

    @JsonProperty("offers")
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @JsonProperty("lineup")
    public List<String> getLineup() {
        return lineup;
    }

    @JsonProperty("lineup")
    public void setLineup(List<String> lineup) {
        this.lineup = lineup;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}


