package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.Vote.VoteId;
import org.junit.Test;

public class VoteIdTest {

    private VoteId voteId = new VoteId("123456", "234567");
    private VoteId otherVoteId;

    @Test
    public void testHashCodeReturnsEqualForEquals() {
        otherVoteId = new VoteId("123456", "234567");

        assertTrue(voteId.hashCode() == otherVoteId.hashCode());
    }

    @Test
    public void testEqualsForBoundaryConditions() {
        assertFalse(voteId == null);
        assertTrue(voteId.equals(voteId));
        assertFalse(voteId.equals(new Object()));
    }

    @Test
    public void testEqualsReturnsNonEqualForDefaultVoteId() {
        VoteId otherVoteId = new VoteId();

        assertFalse(voteId.equals(otherVoteId));
    }

    @Test
    public void testEqualsReturnsNonEqualWithNonEqualVoteId() {
        VoteId otherVoteId = new VoteId("234567", "345678");

        assertFalse(voteId.equals(otherVoteId));
    }

    @Test
    public void testEqualsReturnsEqualForEqualVoteId() {
        otherVoteId = new VoteId("123456", "234567");

        assertTrue(voteId.equals(otherVoteId));
    }

    @Test
    public void testToStringContainsMovieId() {
        assertTrue(voteId.toString().contains("123456"));
    }

    @Test
    public void testToStringContainsUserId() {
        assertTrue(voteId.toString().contains("234567"));
    }
}