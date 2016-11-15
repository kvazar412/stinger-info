package org.dtmhapcs.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.Film;

public interface FilmDao {
    void createOrUpdate (Film film);
    Film readFilmById (String filmId);
    List<Film> readAllFilms();
    void deleteFilm (String filmId);
}
