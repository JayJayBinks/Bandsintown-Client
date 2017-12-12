
package github.jjbinks.bandsintown.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "name",
        "url",
        "image_url",
        "thumb_url",
        "facebook_page_url",
        "mbid",
        "tracker_count",
        "upcoming_event_count"
})
public class Artist {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("facebook_page_url")
    private String facebookPageUrl;
    @JsonProperty("mbid")
    private String mbid;
    @JsonProperty("tracker_count")
    private int trackerCount;
    @JsonProperty("upcoming_event_count")
    private int upcomingEventCount;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("thumb_url")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("thumb_url")
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @JsonProperty("facebook_page_url")
    public String getFacebookPageUrl() {
        return facebookPageUrl;
    }

    @JsonProperty("facebook_page_url")
    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    @JsonProperty("mbid")
    public String getMbid() {
        return mbid;
    }

    @JsonProperty("mbid")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    @JsonProperty("tracker_count")
    public int getTrackerCount() {
        return trackerCount;
    }

    @JsonProperty("tracker_count")
    public void setTrackerCount(int trackerCount) {
        this.trackerCount = trackerCount;
    }

    @JsonProperty("upcoming_event_count")
    public int getUpcomingEventCount() {
        return upcomingEventCount;
    }

    @JsonProperty("upcoming_event_count")
    public void setUpcomingEventCount(int upcomingEventCount) {
        this.upcomingEventCount = upcomingEventCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
