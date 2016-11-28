package org.dtmhapcs.model.services.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.services.db.dao.interfaces.MovieDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {
    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieDaoImpl.class);

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
        LOGGER.info("Movie {} is saved or updated to DB", movie);
    }

    @Override
    public Movie readMovieById(String movieId) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        LOGGER.debug("Movie {} is returned from DB", movie);
        return movie;
    }

    @Override
    public List<Movie> readAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        Session session = this.sessionFactory.getCurrentSession();
        for (Object obj : session.createQuery("FROM Movie m").getResultList()) {
            movieList.add((Movie) obj);
        }
        LOGGER.debug("List of movies are returned from DB");
        return movieList;
    }

    @Override
    public void deleteMovie(String movieId) {
        Session session = sessionFactory.getCurrentSession();
        Movie movie = session.get(Movie.class, movieId);
        if (movie != null) {
            session.delete(movie);
            LOGGER.info("Movie {} is deleted from DB", movie);
        }
    }
}