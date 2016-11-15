package org.dtmhapcs.service;

import java.util.List;

import org.dtmhapcs.dao.interfaces.FilmDao;
import org.dtmhapcs.model.Film;
import org.dtmhapcs.service.interfaces.FilmService;
import org.springframework.transaction.annotation.Transactional;

public class FilmServiceImpl implements FilmService{
    private FilmDao filmDao;
    
    public void setFilmDao(FilmDao filmDao) {
        this.filmDao = filmDao;
    }   
    
    @Transactional
    @Override
    public void createOrUpdate(Film film) {
        
        this.filmDao.createOrUpdate(film);
    }

    @Transactional
    @Override
    public Film readFilmById(String filmId) {
        return this.filmDao.readFilmById(filmId);
    }

    @Transactional
    @Override
    public List<Film> readAllFilms() {        
        return this.filmDao.readAllFilms();
    }

    @Transactional
    @Override
    public void deleteFilm(String filmId) {
        this.filmDao.deleteFilm(filmId);
    } 
}