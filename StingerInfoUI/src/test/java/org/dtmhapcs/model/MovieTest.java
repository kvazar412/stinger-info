package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.enums.PcsInfo;
import org.dtmhapcs.model.interfaces.BaseModelTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class MovieTest implements BaseModelTest {
    private static Movie movie;
    
    @BeforeClass
    public static void initialize() {        
        movie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
    }

    @Test
    public void hashCodeForEqualsIsEqual() {
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
    public void equalsWithDefaultModel() {
        Movie otherMovie = new Movie();
        assertFalse(movie.equals(otherMovie));
    }

    @Test
    public void equalsWithOtherModel() {
        Movie otherMovie = new Movie("123456", "OtherTitle", 2016, "Director", "Country", PcsInfo.YES);
        assertFalse(movie.equals(otherMovie));
    }

    @Test
    public void equalsWithSameModel() {
        Movie otherMovie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
        assertTrue(movie.equals(otherMovie));
    }

    @Test
    public void toStringContainsId() {
        assertTrue(movie.toString().contains("123456"));
    }
}