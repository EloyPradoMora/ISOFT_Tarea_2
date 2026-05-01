package com.example.demo.controllers;

import com.example.demo.TvShow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.TvMazeService;

import java.util.List;

@RestController
public class TvShowController {
    private final TvMazeService tvMazeService;

    public TvShowController(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    @GetMapping("/shows")
    public List<TvShow> fetchShows(@RequestParam(defaultValue = "1") int page) {
        return tvMazeService.getTvShows(page);
    }
}