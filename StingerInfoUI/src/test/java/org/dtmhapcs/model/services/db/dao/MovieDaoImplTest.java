package org.dtmhapcs.model.services.db.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.enums.PcsInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
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

    @Mock
    private Query<Movie> queryMock;

    @InjectMocks
    private MovieDaoImpl movieDao;

    private String movieId = "123456";
    private Movie movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);

    private List<Movie> movieList = new ArrayList<Movie>();

    @Before
    public void setUp() {
        when(this.sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
    }

    @Test
    public void testCreateOrUpdateMovieDelegatesToHibernate() {
        movieDao.createOrUpdateMovie(movie);

        verify(sessionMock).saveOrUpdate(movie);
    }

    @Test
    public void testReadMovieByIdDelegatesToHibernate() {
        movieDao.readMovieById(movieId);

        verify(sessionMock).get(Movie.class, movieId);
    }

    @Test
    public void testReadAllMoviesDelegatesToHibernate() {
        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(movieList);
        movieDao.readAllMovies();

        verify(sessionMock).createQuery(anyString());
    }

    @Test
    public void testReadAllMoviesReturnsTheSameMovieListAsHibernate() {
        movieList.add(movie);
        movieList.add(new Movie("234567", "Title", 2016, "Director", "Country", PcsInfo.YES));

        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(movieList);
        List<Movie> movieListFromDao = movieDao.readAllMovies();

        assertTrue(movieListFromDao.size() == movieList.size());
        assertTrue(movieListFromDao.containsAll(movieList));
    }

    @Test
    public void testReadAllMoviesReturnesEmptyListForEmptyDbTable() {
        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(movieList);
        List<Movie> movieListFromDao = movieDao.readAllMovies();

        assertTrue(movieListFromDao.size() == movieList.size());
        assertTrue(movieListFromDao.containsAll(movieList));
    }

    @Test
    public void testDeleteMovieDelegatesToHibernateForExistingMovie() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(movie);
        movieDao.deleteMovie(movieId);

        verify(sessionMock).delete(movie);
    }

    @Test
    public void testDeleteMovieDoesNothingForNonExistingMovie() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(null);
        movieDao.deleteMovie(movieId);

        verify(sessionMock, never()).delete(movie);
    }
}