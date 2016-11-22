package org.dtmhapcs.controller;

import javax.validation.Valid;

import org.dtmhapcs.model.Movie;
import org.dtmhapcs.model.User;
import org.dtmhapcs.model.services.db.interfaces.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CrudController {
    private DbService dbService;

    @Autowired(required = true)
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    // ******* Mapping urls of searchPage.jsp *******
    @RequestMapping(value = "searchPage")
    public String searchPage() {
        return "searchPage";
    }

    // ******* Mapping urls of movieList.jsp *******
    @RequestMapping(value = "/createOrUpdateMovie", method = RequestMethod.POST)
    public String createMovie(@Valid Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "movieList";
        } else {
            this.dbService.createOrUpdate(movie);
            return "redirect:/movieList";
        }
    }

    @RequestMapping(value = "/readMovie/{movieId}", method = RequestMethod.GET)
    public String readMovieById(@PathVariable("movieId") String movieId, Model model) {
        model.addAttribute("movie", this.dbService.readMovieById(movieId));
        model.addAttribute("movieList", this.dbService.readAllMovies());
        return "movieList";
    }

    @RequestMapping(value = "/movieList", method = RequestMethod.GET)
    public String movieList(Model model) {
        // VoteId voteId1 = new VoteId("123456", "123456");
        // Vote vote1 = new Vote(voteId1, dbService.readMovieById("123456"),
        // dbService.readUserById("123456"), VoteValue.YES);
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieList", this.dbService.readAllMovies());
        return "movieList";
    }

    @RequestMapping(value = "/deleteMovie/{movieId}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("movieId") String movieId) {
        this.dbService.deleteMovie(movieId);
        return "redirect:/movieList";
    }

    // ******* Mapping urls of userList.jsp *******
    @RequestMapping(value = "createOrUpdateUser", method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userList";
        } else {
            this.dbService.createOrUpdate(user);
            return "redirect:/userList";
        }
    }

    @RequestMapping(value = "/readUser/{userId}", method = RequestMethod.GET)
    public String readUserById(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", this.dbService.readUserById(userId));
        model.addAttribute("userList", this.dbService.readAllUsers());
        return "userList";
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userList", this.dbService.readAllUsers());
        return "userList";
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") String userId) {
        this.dbService.deleteUser(userId);
        return "redirect:/userList";
    }

    // ******* Mapping urls of voteList.jsp *******
    @RequestMapping(value = "voteList", method = RequestMethod.GET)
    public String voteList(Model model) {
        model.addAttribute("voteList", this.dbService.readAllVotes());
        return "voteList";
    }

    @RequestMapping(value = "/deleteVote/{movieId}&{userId}", method = RequestMethod.GET)
    public String deleteVote(@PathVariable("movieId") String movieId, @PathVariable("userId") String userId) {
        this.dbService.deleteVote(movieId, userId);
        return "redirect:/voteList";
    }
}