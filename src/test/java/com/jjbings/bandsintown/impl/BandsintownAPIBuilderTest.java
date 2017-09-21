package com.jjbings.bandsintown.impl;

import com.jjbings.bandsintown.api.BandsintownAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;

import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
public class BandsintownAPIBuilderTest {

    private String appId;
    private BandsintownAPIBuilder bandsintownAPIBuilder;

    @Mock
    private Client client;

    private BandsintownAPI bandsintownAPI;

    @Before
    public void setUp() throws Exception {
        bandsintownAPIBuilder = new BandsintownAPIBuilder();
    }

    @Test
    public void build() throws Exception {
        //given
        appId = "123";
        bandsintownAPIBuilder.setAppId(appId);
        bandsintownAPIBuilder.setClient(client);
        //when
        bandsintownAPI = bandsintownAPIBuilder.build();
        //then
        assertNotNull(bandsintownAPI);
    }

    @Test(expected = NullPointerException.class)
    public void buildWithNoClient() throws Exception {
        //given no client is set
        appId = "123";
        bandsintownAPIBuilder.setAppId(appId);
        //when
        bandsintownAPI = bandsintownAPIBuilder.build();
        //then NullPointer is thrown
    }

    @Test(expected = NullPointerException.class)
    public void buildWithAppId() throws Exception {
        //given no appId is set
        bandsintownAPIBuilder.setClient(client);
        //when
        bandsintownAPI = bandsintownAPIBuilder.build();
        //then NullPointer is thrown
    }

}