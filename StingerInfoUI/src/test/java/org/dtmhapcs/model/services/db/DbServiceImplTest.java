package org.dtmhapcs.model.services.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
    private DbServiceImpl dbServiceMock;

    private String movieId = "123456";
    private String otherMovieId = "234567";

    private Movie movie = new Movie(movieId, "Title", 2016, "Director", "Country", PcsInfo.YES);
    private Movie otherMovie = new Movie(otherMovieId, "Title", 2016, "Director", "Country", PcsInfo.YES);

    private String userId = "123456";
    private String otherUserId = "234567";

    private User user = new User(userId, UserRole.USER);
    private User otherUser = new User(otherUserId, UserRole.USER);

    private VoteId voteId = new VoteId(movieId, userId);
    private VoteId otherVoteId = new VoteId(otherMovieId, otherUserId);

    private Vote vote = new Vote(voteId, movie, user, VoteValue.YES);
    private Vote otherVote = new Vote(otherVoteId, otherMovie, otherUser, VoteValue.YES);

    @Test
    public void createOrUpdateMovieShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.createOrUpdateMovie(movie);
        verify(movieDaoMock).createOrUpdateMovie(movie);
    }

    @Test
    public void readMovieByIdShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.readMovieById(movieId);
        verify(movieDaoMock).readMovieById(movieId);
    }

    @Test
    public void readMovieByIdShouldReturnTheSameMovieAsDaoMethod() {
        when(movieDaoMock.readMovieById(movieId)).thenReturn(movie);
        Movie movieFromService = dbServiceMock.readMovieById(movieId);
        assertEquals(movieFromService, movie);
    }

    @Test
    public void readAllMoviesShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.readAllMovies();
        verify(movieDaoMock).readAllMovies();
    }

    @Test
    public void readAllMoviesShouldReturnTheSameMovieListAsDaoMethod() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(movie);
        movieList.add(otherMovie);
        when(movieDaoMock.readAllMovies()).thenReturn(movieList);
        List<Movie> movieListFromService = dbServiceMock.readAllMovies();
        assertTrue((movieList.size() == movieListFromService.size()) && (movieList.containsAll(movieListFromService)));
    }

    @Test
    public void deleteMovieShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.deleteMovie(movieId);
        verify(movieDaoMock).deleteMovie(movieId);
    }

    @Test
    public void createOrUpdateUserShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.createOrUpdateUser(user);
        verify(userDaoMock).createOrUpdateUser(user);
    }

    @Test
    public void readUserByIdShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.readUserById(userId);
        verify(userDaoMock).readUserById(userId);
    }

    @Test
    public void readUserByIdShouldReturnTheSameUserAsDaoMethod() {
        when(userDaoMock.readUserById(userId)).thenReturn(user);
        User userFromService = dbServiceMock.readUserById(userId);
        assertEquals(userFromService, user);
    }

    @Test
    public void readAllUsersShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.readAllUsers();
        verify(userDaoMock).readAllUsers();
    }

    @Test
    public void readAllUsersShouldReturnTheSameUserListAsDaoMethod() {
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(otherUser);
        when(userDaoMock.readAllUsers()).thenReturn(userList);
        List<User> userListFromService = dbServiceMock.readAllUsers();
        assertTrue((userList.size() == userListFromService.size()) && userList.containsAll(userListFromService));
    }

    @Test
    public void deleteUserShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.deleteUser(userId);
        verify(userDaoMock).deleteUser(userId);
    }

    @Test
    public void createOrUpdateVoteShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.createOrUpdateVote(vote);
        verify(voteDaoMock).createOrUpdateVote(vote);
    }

    @Test
    public void readAllVotesShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.readAllVotes();
        verify(voteDaoMock).readAllVotes();
    }

    @Test
    public void readAllVotesShouldReturnTheSameVoteListAsDaoMethod() {
        List<Vote> voteList = new ArrayList<Vote>();
        voteList.add(vote);
        voteList.add(otherVote);
        when(voteDaoMock.readAllVotes()).thenReturn(voteList);
        List<Vote> voteListFromService = dbServiceMock.readAllVotes();
        assertTrue((voteList.size() == voteListFromService.size()) && voteList.containsAll(voteListFromService));
    }

    @Test
    public void deleteVoteShouldDelegateToTheSameDaoMethod() {
        dbServiceMock.deleteVote(movieId, userId);
        verify(voteDaoMock).deleteVote(movieId, userId);
    }
}