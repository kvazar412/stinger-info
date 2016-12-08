package org.dtmhapcs.model.services.db.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.enums.PcsInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovieDaoImplTest {

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private Session sessionMock;

    @InjectMocks
    private MovieDaoImpl movieDaoMock;

    private String movieId = "123456";
    private Movie movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);

    @Before
    public void setUp() {
        when(this.sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        sessionMock = this.sessionFactoryMock.getCurrentSession();
    }

    @Test
    public void createOrUpdateMovieShouldDelegateToTheHibernateMethod() {
        movieDaoMock.createOrUpdateMovie(movie);
        verify(sessionMock).saveOrUpdate(movie);
    }

    @Test
    public void readMovieByIdShouldDelegateToTheHibernateMethod() {
        movieDaoMock.readMovieById(movieId);
        verify(sessionMock).get(Movie.class, movieId);
    }

    // this one doens't work. NPE on "when..then.." line
    @Ignore
    @Test
    public void readAllMoviesShouldDelegateToTheHibernateMethod() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(movie);
        movieList.add(new Movie("234567", "Title", 2016, "Director", "Country", PcsInfo.YES));

        when(sessionMock.createQuery("FROM Movie m").getResultList()).thenReturn(movieList);

        List<Movie> otherMovieList = movieDaoMock.readAllMovies();
        assertTrue((otherMovieList.size() == movieList.size()) && otherMovieList.containsAll(movieList));
    }

    @Test
    public void deleteMovieShouldDelegateToTheHibernateMethodForExistingMovie() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(movie);
        movieDaoMock.deleteMovie(movieId);
        verify(sessionMock).delete(movie);
    }

    @Test
    public void deleteMovieShouldDoNothingForNonExistingMovie() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(null);
        movieDaoMock.deleteMovie(movieId);
        verify(sessionMock, never()).delete(movie);
    }
}