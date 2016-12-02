package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.Vote.VoteId;
import org.dtmhapcs.model.enums.PcsInfo;
import org.dtmhapcs.model.enums.UserRole;
import org.dtmhapcs.model.enums.VoteValue;
import org.dtmhapcs.model.interfaces.BaseModelTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class VoteTest implements BaseModelTest {
    private static VoteId voteId;    
    private static Movie movie;    
    private static User user;    
    private static Vote vote;
    private  Vote otherVote;
    
    @BeforeClass
    public static void initialize() {
        voteId = new VoteId("123456", "123456");
        movie = new Movie("123456", "Title", 2016, "Director", "Country", PcsInfo.YES);
        user = new User("123456", UserRole.USER);
        vote = new Vote(voteId, movie, user, VoteValue.YES);        
    }
        
    @Override
    public void hashCodeForEqualsIsEqual() {
    }

    @Test
    @Override
    public void equalsForBoundaryConditions() {
        assertFalse(vote.equals(null));
        assertTrue(vote.equals(vote));
        assertFalse(vote.equals(new Object()));
    }

    @Test
    @Override
    public void equalsWithDefaultModel() {
        otherVote = new Vote();
        assertFalse(vote.equals(otherVote));
    }

    @Test
    @Override
    public void equalsWithOtherModel() {
        otherVote = new Vote(voteId, movie, user, VoteValue.NO);
        assertFalse(vote.equals(otherVote));
    }

    @Override
    public void equalsWithSameModel() {
    }

    @Test
    @Override
    public void toStringContainsId() {
        assertTrue(vote.toString().contains("123456"));
    }
}