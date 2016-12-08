package org.dtmhapcs.model.services.db.dao;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.User;
import org.dtmhapcs.model.Vote;
import org.dtmhapcs.model.Vote.VoteId;
import org.dtmhapcs.model.enums.PcsInfo;
import org.dtmhapcs.model.enums.UserRole;
import org.dtmhapcs.model.enums.VoteValue;
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
public class VoteDaoImplTest {

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Query<?> queryMock;

    @InjectMocks
    private VoteDaoImpl voteDaoMock;

    private String movieId = "123456";
    private Movie movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);
    private String userId = "123456";
    private User user = new User(userId, UserRole.USER);
    private VoteId voteId = new VoteId(movieId, userId);
    private Vote vote = new Vote(voteId, movie, user, VoteValue.YES);

    @Before
    public void setUp() {
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
    }

    @Test
    public void createOrUpdateVoteShouldDelegateToTheHibernateMethod() {
        voteDaoMock.createOrUpdateVote(vote);
        verify(sessionMock).saveOrUpdate(vote);
    }

    @Test
    public void deleteVoteShouldDoNothingForNullInput() {
        voteDaoMock.deleteVote(null, null);
        verify(sessionMock, never()).createQuery("DELETE FROM Vote v WHERE v.voteId.movieId = :movieId AND v.voteId.userId = :userId");
    }
}