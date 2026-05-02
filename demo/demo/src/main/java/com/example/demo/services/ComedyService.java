package com.example.demo.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.TvShow;

@Service
public class ComedyService {
    private final RestClient restClient;
    private String genreString = "Comedy";

    public ComedyService() {
        this.restClient = RestClient.create("https://api.tvmaze.com");
    }

    public List<TvShow> getTvShows(int page) {
        return restClient.get().uri("/shows?page={page}", page).retrieve()
                .body(new ParameterizedTypeReference<List<TvShow>>(){});
    }

    public List<TvShow> getComedyShowsList(int page){
        return getTvShows(page).stream()
                .filter(s -> Arrays.asList(s.genres()).contains(genreString))
                .filter(s -> "English".equalsIgnoreCase(s.language()))
                .limit(20)
                .toList();
    }

    public List<TvShow> getTopRankingList(int page){
        return getTvShows(page).stream()
                .filter(s -> Arrays.asList(s.genres()).contains(genreString))
                .filter(s -> s.rating() != null && s.rating().average() != null)
                .sorted((s1, s2) -> Double.compare(s1.rating().average(), s2.rating().average()))
                .limit(5)
                .toList();
    }

    public List<TvShow> getRecientComedyList(int page){
        return getTvShows(page).stream()
                .filter(s -> Arrays.asList(s.genres()).contains(genreString))
                .filter(s -> s.premiered() != null && !s.premiered().isEmpty())
                .filter(this::maxYearSearch )
                .toList();
    }

    public boolean maxYearSearch(TvShow show){
        try {
            LocalDate timerOfPremier = LocalDate.parse(show.premiered());
            return timerOfPremier.getYear() >= 2014;
        } catch (Exception e) {
            return false;
        }
    }
}
