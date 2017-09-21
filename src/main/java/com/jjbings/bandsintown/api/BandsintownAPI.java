package com.jjbings.bandsintown.api;

import com.jjbings.bandsintown.dto.ArtistEvent;

import java.util.List;
import java.util.Optional;

public interface BandsintownAPI {
    Optional<List<ArtistEvent>> getEventsForArtist(String artist);
}
