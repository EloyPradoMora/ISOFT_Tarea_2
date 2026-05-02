package com.example.demo.services;

import com.example.demo.TvShow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DramaService {
    private final TvMazeService tvMazeService;

    public DramaService(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    private List<TvShow> getDramaShows() {
        List<TvShow> result = new ArrayList<>();
        int pageNumber = 1;
        while (result.size() <= 25) {
            List<TvShow> allShows = tvMazeService.getTvShows(pageNumber);
            for (TvShow tvShow : allShows) {
                if (Arrays.stream(tvShow.genres()).anyMatch(genre -> genre.equals("Drama"))) {
                    result.add(tvShow);
                }
            }
            pageNumber++;
        }
        return result;
    }

    public List<TvShow> get20EnglishDramaShows() {
        List<TvShow> dramaShows = getDramaShows();
        List<TvShow> result = new ArrayList<>();
        int i = 0;
        while (result.size() < 20) {
            if (dramaShows.get(i).language().equals("English")) {
                result.add(dramaShows.get(i));
            }
            i++;
        }
        return result;
    }

    public List<TvShow> getTop5BestEvaluatedDramaShows() {
        List<TvShow> dramaShows = getDramaShows();
        List<TvShow> result = new ArrayList<>();
        dramaShows.sort((show1, show2) -> {
            Double rating1 = (show1.rating() != null && show1.rating().average() != null) ? show1.rating().average() : 0.0;
            Double rating2 = (show2.rating() != null && show2.rating().average() != null) ? show2.rating().average() : 0.0;
            return Double.compare(rating2, rating1);
        });
        for (int i = 0; i < 5; i++) {
            result.add(dramaShows.get(i));
        }
        return result;
    }

    public List<TvShow> getDramaShowsPriorTo2014(){
        List<TvShow> dramaShows = getDramaShows();
        List<TvShow> result = new ArrayList<>();
        for (TvShow show : dramaShows) {
            if (show.premiered() != null) {
                LocalDate timeOfPremier = LocalDate.parse(show.premiered());
                if (timeOfPremier.getYear() >= 2014) {
                    result.add(show);
                }
            }
        }
        return result;
    }
}