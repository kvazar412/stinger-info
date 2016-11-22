package org.dtmhapcs.model.services.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.services.db.dao.interfaces.MovieDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOrUpdate(Movie movie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(movie);
    }

    @Override
    public Movie readMovieById(String movieId) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        return movie;
    }

    @Override
    public List<Movie> readAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        Session session = this.sessionFactory.getCurrentSession();
        for (Object obj : session.createQuery("FROM Movie m").getResultList()) {
            movieList.add((Movie) obj);
        }
        return movieList;
    }

    @Override
    public void deleteMovie(String movieId) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        if (movie != null) {
            session.delete(movie);
        }
    }
}