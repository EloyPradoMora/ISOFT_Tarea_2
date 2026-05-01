package com.example.demo.controllers;

import com.example.demo.TvShow;
import com.example.demo.services.DramaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DramaController {
    private final DramaService dramaService;

    public DramaController(DramaService dramaService) {
        this.dramaService = dramaService;
    }

    @GetMapping("/drama/english20")
    public List<TvShow> get20EnglishDramaShows() {
        return dramaService.get20EnglishDramaShows();
    }

    @GetMapping("/drama/top5")
    public List<TvShow> getTop5BestEvaludatedDramaShows() {
        return dramaService.getTop5BestEvaluatedDramaShows();
    }

    @GetMapping("/drama/after2014")
    public List<TvShow> getDramaShowsAfter2014() {
        return dramaService.getDramaShowsPriorTo2014();
    }
}
