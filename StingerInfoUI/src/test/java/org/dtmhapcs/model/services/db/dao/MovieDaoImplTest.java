package org.dtmhapcs.model.services.db.dao;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.enums.PcsInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
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
    private MovieDaoImpl movieDao;

    private static String movieId;
    private static Movie movie;

    @BeforeClass
    public static void initialization() {
        movieId = "123456";
        movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);
    }

    @Before
    public void init() {
        when(this.sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        sessionMock = this.sessionFactoryMock.getCurrentSession();
    }

    @Test
    public void createOrUpdateMovieVerifyLaunching() {
        sessionMock.saveOrUpdate(movie);
        verify(sessionMock).saveOrUpdate(movie);
    }

    @Test
    public void readMovieById() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(movie);
        Movie otherMovie = sessionMock.get(Movie.class, movieId);
        assertTrue(movie.equals(otherMovie));
    }

    @Test
    public void readMovieByIdReturnTheSameObject() {
        when(sessionMock.get(Movie.class, movieId)).thenReturn(movie);
        Movie otherMovie = sessionMock.get(Movie.class, movieId);
        assertSame(movie, otherMovie);
    }
    
    //to be continued...
}