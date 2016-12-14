package org.dtmhapcs.model.services.db.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.Movie;

public interface MovieDao {
    void createOrUpdateMovie(Movie movie);

    Movie readMovieById(String movieId);

    List<Movie> readAllMovies();

    void deleteMovie(String movieId);
}