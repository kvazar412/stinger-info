package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.Vote.VoteId;
import org.dtmhapcs.model.enums.PcsInfo;
import org.dtmhapcs.model.enums.UserRole;
import org.dtmhapcs.model.enums.VoteValue;
import org.junit.Test;

public class VoteTest {
    private VoteId voteId = new VoteId("123456", "123456");
    private Movie movie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
    private User user = new User("123456", UserRole.USER);
    private Vote vote = new Vote(voteId, movie, user, VoteValue.YES);
    private Vote otherVote;

    @Test
    public void testEqualsForBoundaryConditions() {
        assertFalse(vote.equals(null));
        assertTrue(vote.equals(vote));
        assertFalse(vote.equals(new Object()));
    }

    @Test
    public void testEqualsReturnsNonEqualForDefaultVote() {
        otherVote = new Vote();

        assertFalse(vote.equals(otherVote));
    }

    @Test
    public void testEqualsReturnsNonEqualWithNonEqualVote() {
        otherVote = new Vote(voteId, movie, user, VoteValue.NO);

        assertFalse(vote.equals(otherVote));
    }

    @Test
    public void testToStringContainsMovieId() {
        assertTrue(vote.toString().contains(movie.getMovieId()));
    }

    @Test
    public void testToStringContainsUserId() {
        assertTrue(vote.toString().contains(user.getUserId()));
    }
}