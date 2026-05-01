package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.TvShow;
import com.example.demo.services.ComedyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/comedy")
public class ComedyController {
    private final ComedyService comedyService;
    
    public ComedyController(ComedyService comedyService)  {
        this.comedyService = comedyService;
    }

    @GetMapping("/list")
    public List<TvShow> getComedyShowsList(@RequestParam(defaultValue = "0") int page) {
        return comedyService.getComedyShowsList(page);
    }

    @GetMapping("/ranking")
    public List<TvShow> getTopRankingList(@RequestParam(defaultValue = "0") int page) {
        return comedyService.getTopRankingList(page);
    }
    
    @GetMapping("/recient")
    public List<TvShow> getRecientComedyList(@RequestParam(defaultValue = "0") int page) {
        return comedyService.getRecientComedyList(page);
    }
    
}
