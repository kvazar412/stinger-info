package org.dtmhapcs.model.services.db.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.Movie;

public interface MovieDao {
    void createOrUpdate (Movie movie);
    Movie readMovieById (String filmId);
    List<Movie> readAllMovies();
    void deleteMovie (String filmId);
}