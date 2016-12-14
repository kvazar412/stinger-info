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
    private DbServiceImpl dbService;

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
    public void testCreateOrUpdateMovieDelegatesToTheSameDaoMethod() {
        dbService.createOrUpdateMovie(movie);

        verify(movieDaoMock).createOrUpdateMovie(movie);
    }

    @Test
    public void testReadMovieByIdDelegatesToTheSameDaoMethod() {
        dbService.readMovieById(movieId);

        verify(movieDaoMock).readMovieById(movieId);
    }

    @Test
    public void testReadMovieByIdReturnsTheSameMovieAsDaoMethod() {
        when(movieDaoMock.readMovieById(movieId)).thenReturn(movie);
        Movie movieFromService = dbService.readMovieById(movieId);

        assertEquals(movieFromService, movie);
    }

    @Test
    public void testReadAllMoviesDelegatesToTheSameDaoMethod() {
        dbService.readAllMovies();

        verify(movieDaoMock).readAllMovies();
    }

    @Test
    public void testReadAllMoviesReturnsTheSameMovieListAsDaoMethod() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(movie);
        movieList.add(otherMovie);

        when(movieDaoMock.readAllMovies()).thenReturn(movieList);
        List<Movie> movieListFromService = dbService.readAllMovies();

        assertTrue((movieList.size() == movieListFromService.size()) && (movieList.containsAll(movieListFromService)));
    }

    @Test
    public void testDeleteMovieDelegatesToTheSameDaoMethod() {
        dbService.deleteMovie(movieId);

        verify(movieDaoMock).deleteMovie(movieId);
    }

    @Test
    public void testCreateOrUpdateUserDelegatesToTheSameDaoMethod() {
        dbService.createOrUpdateUser(user);

        verify(userDaoMock).createOrUpdateUser(user);
    }

    @Test
    public void testReadUserByIdDelegatesToTheSameDaoMethod() {
        dbService.readUserById(userId);

        verify(userDaoMock).readUserById(userId);
    }

    @Test
    public void testReadUserByIdReturnsTheSameUserAsDaoMethod() {
        when(userDaoMock.readUserById(userId)).thenReturn(user);
        User userFromService = dbService.readUserById(userId);

        assertEquals(userFromService, user);
    }

    @Test
    public void testReadAllUsersDelegatesToTheSameDaoMethod() {
        dbService.readAllUsers();

        verify(userDaoMock).readAllUsers();
    }

    @Test
    public void testReadAllUsersReturnsTheSameUserListAsDaoMethod() {
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(otherUser);

        when(userDaoMock.readAllUsers()).thenReturn(userList);
        List<User> userListFromService = dbService.readAllUsers();

        assertTrue((userList.size() == userListFromService.size()) && userList.containsAll(userListFromService));
    }

    @Test
    public void testDeleteUserDelegatesToTheSameDaoMethod() {
        dbService.deleteUser(userId);

        verify(userDaoMock).deleteUser(userId);
    }

    @Test
    public void testCreateOrUpdateVoteDelegatesToTheSameDaoMethod() {
        dbService.createOrUpdateVote(vote);

        verify(voteDaoMock).createOrUpdateVote(vote);
    }

    @Test
    public void testReadAllVotesDelegatesToTheSameDaoMethod() {
        dbService.readAllVotes();

        verify(voteDaoMock).readAllVotes();
    }

    @Test
    public void testReadAllVotesReturnsTheSameVoteListAsDaoMethod() {
        List<Vote> voteList = new ArrayList<Vote>();
        voteList.add(vote);
        voteList.add(otherVote);

        when(voteDaoMock.readAllVotes()).thenReturn(voteList);
        List<Vote> voteListFromService = dbService.readAllVotes();

        assertTrue((voteList.size() == voteListFromService.size()) && voteList.containsAll(voteListFromService));
    }

    @Test
    public void testDeleteVoteDelegatesToTheSameDaoMethod() {
        dbService.deleteVote(movieId, userId);

        verify(voteDaoMock).deleteVote(movieId, userId);
    }
}