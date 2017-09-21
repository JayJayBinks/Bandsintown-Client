package com.jjbings.bandsintown;


import com.jjbings.bandsintown.api.BandsintownAPI;
import com.jjbings.bandsintown.impl.BandsintownAPIBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Test {

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder().build();

        BandsintownAPI bandsintownAPI = new BandsintownAPIBuilder().setAppId("").setClient(client).build();

        System.out.println(bandsintownAPI.getEventsForArtist("bbb"));

    }
}
