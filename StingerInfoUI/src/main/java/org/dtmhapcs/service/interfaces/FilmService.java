package org.dtmhapcs.service.interfaces;

import java.util.List;

import org.dtmhapcs.model.Film;

public interface FilmService {
    void createOrUpdate (Film film);
    Film readFilmById (String filmId);
    List<Film> readAllFilms();
    void deleteFilm (String filmId);
}
