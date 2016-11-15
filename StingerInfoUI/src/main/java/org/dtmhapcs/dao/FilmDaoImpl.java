package org.dtmhapcs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.dtmhapcs.dao.interfaces.FilmDao;
import org.dtmhapcs.model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository("filmDao")
public class FilmDaoImpl implements FilmDao {

    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOrUpdate(Film film) {       
        Session session = this.sessionFactory.getCurrentSession();        
        session.saveOrUpdate(film);
    }

    @Override
    public Film readFilmById(String filmId) {
        Session session = this.sessionFactory.getCurrentSession();
        Film film = session.get(Film.class, filmId);
        return film;
    }
    
    @Override
    @SuppressWarnings({ "deprecation", "unchecked" })
    public List<Film> readAllFilms() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Film> filmList = session.createQuery("FROM Film f").list();
        return filmList;
    }

    @Override
    public void deleteFilm(String filmId) {
        Session session = sessionFactory.getCurrentSession();
        Film film = session.get(Film.class, filmId);
        if (film != null) {
            session.delete(film);
        }
    }
}