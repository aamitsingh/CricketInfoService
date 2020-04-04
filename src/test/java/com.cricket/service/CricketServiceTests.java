package com.cricket.service;

import com.cricket.model.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by amitsingh on 04/04/20.
 */

@Test
public class CricketServiceTests {

    private final static String UNIQUE_ID = "1136617";

    @Test
    public void testGetMatchDetails_returnsSuccess() throws Exception {

        final CricketService cricketService = new CricketService();

        Response expectedResponse = new Response();
        expectedResponse.setTeam1("Sunrisers Hyderabad");
        expectedResponse.setTeam2("Chennai Super Kings [WINNER]");
        expectedResponse.setWinningTeamScore("140/8");
        expectedResponse.setRoundRotation("8140/");

        //Need to mock rest template
        Response actualResponse = cricketService.getMatchDetails(UNIQUE_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetMatchDetails_throwsException() throws Exception {

        final CricketService cricketService = new CricketService();
        //Need to mock rest template
        //By setting 502 HTTP Code in the response entity the exception can be thrown.
    }

}
