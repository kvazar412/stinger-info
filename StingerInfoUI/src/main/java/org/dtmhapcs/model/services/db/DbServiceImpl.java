package org.dtmhapcs.model.services.db;

import java.util.List;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.User;
import org.dtmhapcs.model.Vote;
import org.dtmhapcs.model.services.db.dao.interfaces.MovieDao;
import org.dtmhapcs.model.services.db.dao.interfaces.UserDao;
import org.dtmhapcs.model.services.db.dao.interfaces.VoteDao;
import org.dtmhapcs.model.services.db.interfaces.DbService;
import org.springframework.transaction.annotation.Transactional;

public class DbServiceImpl implements DbService {
    private MovieDao movieDao;
    private UserDao userDao;
    private VoteDao voteDao;

    @Transactional
    @Override
    public void createOrUpdateMovie(Movie movie) {
        this.movieDao.createOrUpdateMovie(movie);
    }

    @Transactional
    @Override
    public Movie readMovieById(String movieId) {
        return this.movieDao.readMovieById(movieId);
    }

    @Transactional
    @Override
    public List<Movie> readAllMovies() {
        return this.movieDao.readAllMovies();
    }

    @Transactional
    @Override
    public void deleteMovie(String movieId) {
        this.movieDao.deleteMovie(movieId);
    }

    @Transactional
    @Override
    public void createOrUpdateUser(User user) {
        this.userDao.createOrUpdateUser(user);
    }

    @Transactional
    @Override
    public User readUserById(String userId) {
        return this.userDao.readUserById(userId);
    }

    @Transactional
    @Override
    public List<User> readAllUsers() {
        return this.userDao.readAllUsers();
    }

    @Transactional
    @Override
    public void deleteUser(String userId) {
        this.userDao.deleteUser(userId);
    }

    @Transactional
    @Override
    public void createOrUpdateVote(Vote vote) {
        this.voteDao.createOrUpdateVote(vote);
    }

    @Transactional
    @Override
    public List<Vote> readAllVotes() {
        return this.voteDao.readAllVotes();
    }

    @Transactional
    @Override
    public void deleteVote(String movieId, String userId) {
        this.voteDao.deleteVote(movieId, userId);
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public VoteDao getVoteDao() {
        return voteDao;
    }

    public void setVoteDao(VoteDao voteDao) {
        this.voteDao = voteDao;
    }
}