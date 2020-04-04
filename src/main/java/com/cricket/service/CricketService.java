package com.cricket.service;


import com.cricket.model.MatchInfo;
import com.cricket.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amitsingh on 04/04/20.
 */

@Service
public class CricketService {
    @Autowired
    private RestTemplate restTemplate;

    final static String URL = "http://cricapi.com/api/cricketScore";
    final static String PARAMETER_1 = "unique_id";
    final static String API_KEY = "WmPJrX2s3KMyZVPFwlm1vxXLXKw1"; //This should be in a config file or in some vault/secret store.


    public Response getMatchDetails(String uniqueID) throws Exception /*ResponseStatusException*/ {

        HttpHeaders headers = new HttpHeaders();
        headers.set("apiKey", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<MatchInfo> responseEntity = restTemplate.exchange(
                URL + '?' + PARAMETER_1 + '=' + uniqueID, HttpMethod.GET, entity, MatchInfo.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return getResponse(responseEntity);
        }

        //If the unique_id is wrong (does not exists), then response entity have 502 HTTPS Code.
        throw new Exception("[Invalid Unique ID] Exception occurred while fetching the record.",
                Exception.class.newInstance());
    }

    private Response getResponse(ResponseEntity response){
        MatchInfo matchInfo = (MatchInfo) response.getBody();
        String teamName = getWinningTeamByScore(matchInfo.getScore());

        String winningTeam = teamName.replaceAll("[^a-zA-Z]", " ");

        String team1 = matchInfo.getTeam1();
        String team2 = matchInfo.getTeam2();

        if (team1.trim().equals(winningTeam.trim())) {
            team1 = matchInfo.getTeam1() + " [WINNER]";
        } else if (team2.trim().equals(winningTeam.trim())) {
            team2 = matchInfo.getTeam2() + " [WINNER]";
        }

        Matcher m = Pattern.compile("^*\\d*/\\d*").matcher(teamName);
        String winningTeamScore = null;
        while (m.find()) {
            winningTeamScore=m.group(0);
        }
        Response responseModel = new Response();

        responseModel.setTeam1(team1);
        responseModel.setTeam2(team2);
        responseModel.setWinningTeamScore(winningTeamScore);
        responseModel.setRoundRotation(getRotatedValue(winningTeamScore));
        return responseModel;
    }

    private String getRotatedValue(String winningTeamScore2) {
        Matcher m = Pattern.compile("^*\\d*/\\d*").matcher(winningTeamScore2);
        String winningTeamScore=null;
        while (m.find()) {
            winningTeamScore=m.group(0);
        }
        String[] str=winningTeamScore.split("/");
        String test=str[1]+str[0]+"/";
        return test;
    }

    private String getWinningTeamByScore(String score) {
        String[] replacedSpace=score.split("v");
        String winningTeamName=null;
        for (String teamName : replacedSpace) {
            if(teamName.contains("*")) {
                winningTeamName=teamName;
            }
        }

        return winningTeamName;
    }
}