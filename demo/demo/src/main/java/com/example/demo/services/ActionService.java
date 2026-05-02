package com.example.demo.services;

import com.example.demo.TvShow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {
    private final TvMazeService tvMazeService;

    public ActionService(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    private List<TvShow> getActionShows() {
        List<TvShow> result = new ArrayList<>();
        int pageNumber = 1;
        while (result.size() <= 30) { 
            List<TvShow> allShows = tvMazeService.getTvShows(pageNumber);
            if (allShows == null || allShows.isEmpty()) break;

            for (TvShow tvShow : allShows) {
                if (tvShow.genres() != null) {
                    for (String genre : tvShow.genres()) {
                        if (genre.equals("Action")) {
                            result.add(tvShow);
                            break;
                        }
                    }
                }
            }
            pageNumber++;
        }
        return result;
    }

    public List<TvShow> get20EnglishActionShows() {
        List<TvShow> actionShows = getActionShows();
        List<TvShow> result = new ArrayList<>();
        int i = 0;
        while (result.size() < 20 && i < actionShows.size()) {
            if ("English".equalsIgnoreCase(actionShows.get(i).language())) {
                result.add(actionShows.get(i));
            }
            i++;
        }
        return result;
    }

    public List<TvShow> getTop5BestEvaluatedActionShows() {
        List<TvShow> actionShows = getActionShows();
        List<TvShow> result = new ArrayList<>();
        
        actionShows.sort((show1, show2) -> {
            Double rating1 = (show1.rating() != null && show1.rating().average() != null) ? show1.rating().average() : 0.0;
            Double rating2 = (show2.rating() != null && show2.rating().average() != null) ? show2.rating().average() : 0.0;
            return Double.compare(rating2, rating1);
        });

        int limit = Math.min(5, actionShows.size());
        for (int i = 0; i < limit; i++) {
            result.add(actionShows.get(i));
        }
        return result;
    }

    public List<TvShow> getActionShowsFrom2014() {
        List<TvShow> actionShows = getActionShows();
        List<TvShow> result = new ArrayList<>();
        for (TvShow show : actionShows) {
            if (show.premiered() != null && !show.premiered().isEmpty()) {
                LocalDate timeOfPremier = LocalDate.parse(show.premiered());
                if (timeOfPremier.getYear() >= 2014) {
                    result.add(show);
                }
            }
        }
        return result;
    }
}