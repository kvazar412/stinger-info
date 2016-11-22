package org.dtmhapcs.model.services.db.interfaces;

import java.util.List;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.User;
import org.dtmhapcs.model.Vote;

public interface DbService {
    void createOrUpdate(Movie movie);
    Movie readMovieById(String movieId);
    List<Movie> readAllMovies();
    void deleteMovie(String movieId);
    
    void createOrUpdate(User user);
    User readUserById(String userId);
    List<User> readAllUsers();
    void deleteUser(String userId); 
    
    void createOrUpdate(Vote vote);
    List<Vote> readAllVotes();
    void deleteVote(String movieId, String userId);
}