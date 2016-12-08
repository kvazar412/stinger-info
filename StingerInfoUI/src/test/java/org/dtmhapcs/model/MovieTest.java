package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.enums.PcsInfo;
import org.junit.Test;

public class MovieTest {
    private Movie movie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);

    @Test
    public void hashCodeShouldBeEqualForEquals() {
        Movie otherMovie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
        assertTrue(movie.hashCode() == otherMovie.hashCode());
    }

    @Test
    public void equalsForBoundaryConditions() {
        assertFalse(movie.equals(null));
        assertTrue(movie.equals(movie));
        assertFalse(movie.equals(new Object()));
    }

    @Test
    public void shouldNotBeEqualWithDefaultMovie() {
        Movie otherMovie = new Movie();
        assertFalse(movie.equals(otherMovie));
    }

    @Test
    public void shouldNotBeEqualWithNonEqualMovie() {
        Movie otherMovie = new Movie("123456", "OtherTitle", 2016, "Director", "Country", PcsInfo.YES);
        assertFalse(movie.equals(otherMovie));
    }

    @Test
    public void shouldBeEqualWithEqualMovie() {
        Movie otherMovie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
        assertTrue(movie.equals(otherMovie));
    }

    @Test
    public void toStringShouldContainMovieId() {
        assertTrue(movie.toString().contains("123456"));
    }
}