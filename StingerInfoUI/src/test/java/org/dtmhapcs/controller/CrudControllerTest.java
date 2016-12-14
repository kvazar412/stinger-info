package org.dtmhapcs.controller;

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
import org.dtmhapcs.model.services.db.interfaces.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class CrudControllerTest {

    @Mock
    private DbService dbServiceMock;

    @Mock
    private BindingResult bindingResultMock;

    @Mock
    private Model model;

    @InjectMocks
    private CrudController crudController;

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

    private List<Movie> movieList;
    private List<User> userList;
    private List<Vote> voteList;

    private String resultUrl;

    private void setUpForMovie() {
        movieList = new ArrayList<Movie>();
        movieList.add(movie);
        movieList.add(otherMovie);
        model = new ExtendedModelMap();
    }

    private void setUpForUser() {
        userList = new ArrayList<User>();
        userList.add(user);
        userList.add(otherUser);
        model = new ExtendedModelMap();
    }

    private void setUpForVote() {
        voteList = new ArrayList<Vote>();
        voteList.add(vote);
        voteList.add(otherVote);
        model = new ExtendedModelMap();
    }

    // ******* Tests for mapping urls of searchPage.jsp *******
    @Test
    public void testSearchPageReturnsRightUrl() {
        resultUrl = crudController.searchPage();

        assertTrue("searchPage".equals(resultUrl));
    }

    // ******* Tests for mapping urls of movieList.jsp *******
    @Test
    public void testCreateMovieReturnsRightUrlWhenFormHasErrors() {
        when(bindingResultMock.hasErrors()).thenReturn(true);
        resultUrl = crudController.createMovie(movie, bindingResultMock);

        assertTrue("movieList".equals(resultUrl));
    }

    @Test
    public void testCreateMovieReturnsRightUrlWhenFormHasNoErrors() {
        when(bindingResultMock.hasErrors()).thenReturn(false);
        resultUrl = crudController.createMovie(movie, bindingResultMock);

        assertTrue("redirect:/movieList".equals(resultUrl));
    }

    @Test
    public void testCreateMovieDelegatesToServiceMethodWithRightMovie() {
        when(bindingResultMock.hasErrors()).thenReturn(false);
        crudController.createMovie(movie, bindingResultMock);

        verify(dbServiceMock).createOrUpdateMovie(movie);
    }

    @Test
    public void testReadMovieByIdReturnsRightUrl() {
        setUpForMovie();

        when(dbServiceMock.readMovieById(movieId)).thenReturn(movie);
        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        resultUrl = crudController.readMovieById(movieId, model);

        assertTrue("movieList".equals(resultUrl));
    }

    @Test
    public void testReadMovieByIdReturnsRightMovieForNotNullInput() {
        setUpForMovie();

        when(dbServiceMock.readMovieById(movieId)).thenReturn(movie);
        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        crudController.readMovieById(movieId, model);
        Movie movieFromController = (Movie) model.asMap().get("movie");

        assertTrue(movieFromController.equals(movie));
    }

    @Test
    public void testReadMovieByIdReturnsRightMovieForNullInput() {
        setUpForMovie();

        when(dbServiceMock.readMovieById(movieId)).thenReturn(null);
        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        crudController.readMovieById(movieId, model);
        Movie movieFromController = (Movie) model.asMap().get("movie");

        assertTrue(movieFromController instanceof Movie);
        assertTrue(movieFromController.getMovieId() == null);
    }

    @Test
    public void testReadMovieByIdReturnsRightMovieListForNullInput() {
        setUpForMovie();

        when(dbServiceMock.readMovieById(movieId)).thenReturn(null);
        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        crudController.readMovieById(movieId, model);
        List<?> movieListFromController = (List<?>) model.asMap().get("movieList");

        assertTrue(movieListFromController.size() == movieList.size());
        assertTrue(movieList.containsAll(movieListFromController));
    }

    @Test
    public void testMovieListReturnsRightUrl() {
        setUpForMovie();

        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        resultUrl = crudController.movieList(model);

        assertTrue("movieList".equals(resultUrl));
    }

    @Test
    public void testMovieListReturnsRightModel() {
        setUpForMovie();

        when(dbServiceMock.readAllMovies()).thenReturn(movieList);
        crudController.movieList(model);
        List<?> movieListFromController = (List<?>) model.asMap().get("movieList");

        assertTrue(movieListFromController.size() == movieList.size());
        assertTrue(movieList.containsAll(movieListFromController));
    }

    @Test
    public void testDeleteMovieReturnsRightUrlForNullInput() {
        String movieIdNotValid = null;
        resultUrl = crudController.deleteMovie(movieIdNotValid);

        assertTrue("redirect:/movieList".equals(resultUrl));
    }

    @Test
    public void testDeleteMovieReturnsRightUrlForNonValidInput() {
        String movieIdNotValid = "   \t \n \r ";
        resultUrl = crudController.deleteMovie(movieIdNotValid);

        assertTrue("redirect:/movieList".equals(resultUrl));
    }

    @Test
    public void testDeleteMovieDelegatesToServiceMethodWithRightMovie() {
        resultUrl = crudController.deleteMovie(movieId);

        verify(dbServiceMock).deleteMovie(movieId);
    }

    // ******* Tests for mapping urls of userList.jsp *******
    @Test
    public void testCreateUserReturnsRightUrlWhenFormHasErrors() {
        when(bindingResultMock.hasErrors()).thenReturn(true);
        resultUrl = crudController.createUser(user, bindingResultMock);

        assertTrue("userList".equals(resultUrl));
    }

    @Test
    public void testCreateUserReturnsRightUrlWhenFormHasNoErrors() {
        when(bindingResultMock.hasErrors()).thenReturn(false);
        resultUrl = crudController.createUser(user, bindingResultMock);

        assertTrue("redirect:/userList".equals(resultUrl));
    }

    @Test
    public void testCreateUserDelegatesToServiceMethodWithRightUser() {
        when(bindingResultMock.hasErrors()).thenReturn(false);
        crudController.createUser(user, bindingResultMock);

        verify(dbServiceMock).createOrUpdateUser(user);
    }

    @Test
    public void testReadUserByIdReturnsRightUrl() {
        setUpForUser();

        when(dbServiceMock.readUserById(userId)).thenReturn(user);
        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        resultUrl = crudController.readUserById(userId, model);

        assertTrue("userList".equals(resultUrl));
    }

    @Test
    public void testReadUserByIdReturnsRightUserForNotNullInput() {
        setUpForUser();

        when(dbServiceMock.readUserById(userId)).thenReturn(user);
        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        crudController.readUserById(userId, model);
        User userFromController = (User) model.asMap().get("user");

        assertTrue(userFromController.equals(user));
    }

    @Test
    public void testReadUserByIdReturnsRightUserForNullInput() {
        setUpForUser();

        when(dbServiceMock.readUserById(userId)).thenReturn(null);
        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        crudController.readUserById(userId, model);
        User userFromController = (User) model.asMap().get("user");

        assertTrue(userFromController instanceof User);
        assertTrue(userFromController.getUserId() == null);
    }

    @Test
    public void testReadUserByIdReturnsRightUserListForNullInput() {
        setUpForUser();

        when(dbServiceMock.readUserById(userId)).thenReturn(null);
        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        crudController.readUserById(userId, model);
        List<?> userListFromController = (List<?>) model.asMap().get("userList");

        assertTrue(userListFromController.size() == userList.size());
        assertTrue(userList.containsAll(userListFromController));
    }

    @Test
    public void testUserListReturnsRightUrl() {
        setUpForUser();

        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        resultUrl = crudController.userList(model);

        assertTrue("userList".equals(resultUrl));
    }

    @Test
    public void testUserListReturnsRightModel() {
        setUpForUser();

        when(dbServiceMock.readAllUsers()).thenReturn(userList);
        crudController.userList(model);
        List<?> userListFromController = (List<?>) model.asMap().get("userList");

        assertTrue(userListFromController.size() == userList.size());
        assertTrue(userList.containsAll(userListFromController));
    }

    @Test
    public void testDeleteUserReturnsRightUrlForNullInput() {
        String userIdNotValid = null;

        resultUrl = crudController.deleteUser(userIdNotValid);

        assertTrue("redirect:/userList".equals(resultUrl));
    }

    @Test
    public void testDeleteUserReturnsRightUrlForNonValidInput() {
        String userIdNotValid = "   \t \n \r ";

        resultUrl = crudController.deleteUser(userIdNotValid);

        assertTrue("redirect:/userList".equals(resultUrl));
    }

    @Test
    public void testDeleteUserDelegatesToServiceMethodWithRightUser() {
        resultUrl = crudController.deleteUser(userId);

        verify(dbServiceMock).deleteUser(userId);
    }

    // ******* Tests for mapping urls of voteList.jsp *******
    @Test
    public void testVoteListReturnsRightUrl() {
        setUpForVote();

        when(dbServiceMock.readAllVotes()).thenReturn(voteList);
        resultUrl = crudController.voteList(model);

        assertTrue("voteList".equals(resultUrl));
    }

    @Test
    public void testVoteListReturnsRightModel() {
        setUpForVote();

        when(dbServiceMock.readAllVotes()).thenReturn(voteList);
        crudController.voteList(model);
        List<?> voteListFromController = (List<?>) model.asMap().get("voteList");

        assertTrue(voteListFromController.size() == voteList.size());
        assertTrue(voteList.containsAll(voteListFromController));
    }

    @Test
    public void testDeleteVoteReturnsRightUrlForNullPlusNullInput() {
        String movieIdNotValid = null;
        String userIdNotValid = null;

        resultUrl = crudController.deleteVote(movieIdNotValid, userIdNotValid);

        assertTrue("redirect:/voteList".equals(resultUrl));
    }

    @Test
    public void testDeleteVoteReturnsRightUrlForNullPlusValidInput() {
        String movieIdNotValid = null;
        String userIdNotValid = null;

        resultUrl = crudController.deleteVote(movieId, userIdNotValid);

        assertTrue(resultUrl.equals("redirect:/voteList"));

        resultUrl = crudController.deleteVote(movieIdNotValid, userId);

        assertTrue("redirect:/voteList".equals(resultUrl));
    }

    @Test
    public void testDeleteVoteReturnsRightUrlForNonValidPlusNonValidInput() {
        String movieIdNotValid = "   \t \n \r ";
        String userIdNotValid = "   \t \n \r ";

        resultUrl = crudController.deleteVote(movieIdNotValid, userIdNotValid);

        assertTrue("redirect:/voteList".equals(resultUrl));
    }

    @Test
    public void testDeleteVoteReturnsRightUrlForNonValidPlusValidInput() {
        String movieIdNotValid = "   \t \n \r ";
        String userIdNotValid = "   \t \n \r ";

        resultUrl = crudController.deleteVote(movieId, userIdNotValid);

        assertTrue("redirect:/voteList".equals(resultUrl));

        resultUrl = crudController.deleteVote(movieIdNotValid, userId);

        assertTrue("redirect:/voteList".equals(resultUrl));
    }

    @Test
    public void testDeleteVoteDelegatesToServiceMethodWithRightVote() {
        resultUrl = crudController.deleteVote(movieId, userId);

        verify(dbServiceMock).deleteVote(movieId, userId);
    }
}