package com.example.demo.controllers;

import com.example.demo.TvShow;
import com.example.demo.services.ActionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/action/english20")
    public List<TvShow> get20EnglishActionShows() {
        return actionService.get20EnglishActionShows();
    }

    @GetMapping("/action/top5")
    public List<TvShow> getTop5BestEvaluatedActionShows() {
        return actionService.getTop5BestEvaluatedActionShows();
    }

    @GetMapping("/action/after2014")
    public List<TvShow> getActionShowsAfter2014() {
        return actionService.getActionShowsFrom2014();
    }
}