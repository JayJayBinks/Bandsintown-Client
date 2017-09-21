package com.jjbings.bandsintown.impl;

import com.jjbings.bandsintown.api.BandsintownAPI;

import javax.ws.rs.client.Client;
import java.util.Objects;

public class BandsintownAPIBuilder {
    private String appId;
    private Client client;

    public BandsintownAPIBuilder setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public BandsintownAPIBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    public BandsintownAPI build() {
        Objects.requireNonNull(appId);
        Objects.requireNonNull(client);
        return new BandsintownImpl(appId, client);
    }
}