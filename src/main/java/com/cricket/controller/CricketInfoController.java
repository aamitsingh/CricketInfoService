package com.cricket.controller;

import com.cricket.model.Response;
import com.cricket.service.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by amitsingh on 04/04/20.
 */
@RestController
@RequestMapping("/cric-info")
public class CricketInfoController {

    @Autowired
    CricketService cricketService;

    @RequestMapping("/result")
    public Response getMatchDetails(@RequestParam("unique_id") String uniqueID ) throws Exception {

        return cricketService.getMatchDetails(uniqueID);
    }
}
