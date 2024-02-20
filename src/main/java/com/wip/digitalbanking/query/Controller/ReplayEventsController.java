package com.wip.digitalbanking.query.Controller;

import com.wip.digitalbanking.query.Services.ReplayEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query/accounts")
public class ReplayEventsController {
    @Autowired
    public ReplayEventsService replayEventsService;
    @GetMapping("/replayer")
    public String replayEvents (){
        replayEventsService.replay();

        return " Success replay";
    }
}
