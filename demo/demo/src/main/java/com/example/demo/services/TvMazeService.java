package com.example.demo.services;

import com.example.demo.TvShow;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TvMazeService {
    private final RestClient restClient;

    public TvMazeService() {
        this.restClient = RestClient.create("https://api.tvmaze.com");
    }

    public List<TvShow> getTvShows(int page) {
        return restClient.get().uri("/shows?page={page}", page).retrieve()
                .body(new ParameterizedTypeReference<List<TvShow>>(){});
    }
}