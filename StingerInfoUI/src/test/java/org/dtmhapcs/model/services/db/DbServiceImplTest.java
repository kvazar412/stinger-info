package org.dtmhapcs.model.services.db;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.User;
import org.dtmhapcs.model.Vote;
import org.dtmhapcs.model.Vote.VoteId;
import org.dtmhapcs.model.enums.PcsInfo;
import org.dtmhapcs.model.enums.UserRole;
import org.dtmhapcs.model.enums.VoteValue;
import org.dtmhapcs.model.services.db.dao.MovieDaoImpl;
import org.dtmhapcs.model.services.db.dao.UserDaoImpl;
import org.dtmhapcs.model.services.db.dao.VoteDaoImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceImplTest {
    @Mock
    private MovieDaoImpl movieDaoMock;

    @Mock
    private UserDaoImpl userDaoMock;
    
    @Mock
    private VoteDaoImpl voteDaoMock;

    @InjectMocks
    private DbServiceImpl dbService;

    private static String movieId;
    private static Movie movie;
    private static String userId;
    private static User user;    
    private static VoteId voteId;
    private static Vote vote;

    @BeforeClass
    public static void initialize() {
        movieId = "123456";
        movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);
        userId = "123456";
        user = new User(userId, UserRole.USER);
        voteId = new VoteId(movieId, userId);
        vote = new Vote(voteId, movie, user, VoteValue.YES);
    }

    @Test
    public void createOrUpdateMovieDelegateToTheSameDaoMethod() {
        dbService.createOrUpdateMovie(movie);
        verify(movieDaoMock).createOrUpdateMovie(movie);
    }

    @Test
    public void readMovieByIdDelegateToTheSameDaoMethod() {
        dbService.readMovieById(movieId);
        verify(movieDaoMock).readMovieById(movieId);
    }

    @Test
    public void readMovieByIdShouldReturnTheSameMovieAsDaoMethod() {
        when(movieDaoMock.readMovieById(movieId)).thenReturn(movie);
        Movie movieFromService = dbService.readMovieById(movieId);
        assertTrue(movieFromService.equals(movie));
    }

    @Test
    public void readAllMoviesDelegateToTheSameDaoMethod() {
        dbService.readAllMovies();
        verify(movieDaoMock).readAllMovies();
    }

    @Test
    public void deleteMovieDelegateToTheSameDaoMethod() {
        dbService.deleteMovie(movieId);
        verify(movieDaoMock).deleteMovie(movieId);
    }

    @Test
    public void createOrUpdateUserDelegateToTheSameDaoMethod() {
        dbService.createOrUpdateUser(user);
        verify(userDaoMock).createOrUpdateUser(user);
    }

    @Test
    public void readUserByIdDelegateToTheSameDaoMethod() {
        dbService.readUserById(userId);
        verify(userDaoMock).readUserById(userId);
    }

    @Test
    public void readUserByIdShouldReturnTheSameUserAsDaoMethod() {
        when(userDaoMock.readUserById(userId)).thenReturn(user);
        User userFromService = dbService.readUserById(userId);
        assertTrue(user.equals(userFromService));
        assertSame(user, userFromService);
    }
    
    @Test
    public void readAllUsersDelegateToTheSameDaoMethod() {
        dbService.readAllUsers();
        verify(userDaoMock).readAllUsers();        
    }
    
    @Test
    public void deleteUserDelegateToTheSameDaoMethod() {
        dbService.deleteUser(userId);
        verify(userDaoMock).deleteUser(userId);
    }
    
    @Test
    public void createOrUpdateVoteDelegateToTheSameDaoMethod() {
        dbService.createOrUpdateVote(vote);
        verify(voteDaoMock).createOrUpdateVote(vote);
    }
    
    @Test
    public void readAllVotesDelegateToTheSameDaoMethod() {
        dbService.readAllVotes();
        verify(voteDaoMock).readAllVotes();
    }
    
    @Test
    public void deleteVoteDelegateToTheSameDaoMethod() {
        dbService.deleteVote(movieId, userId);
        verify(voteDaoMock).deleteVote(movieId, userId);
    }
}