package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvShow(Long id, String name, String[] genres, String language, Rating rating, String premiered) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Rating(Double average) {}
}
