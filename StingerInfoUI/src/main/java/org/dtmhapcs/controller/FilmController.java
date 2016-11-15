package org.dtmhapcs.controller;

import javax.validation.Valid;

import org.dtmhapcs.model.Film;
import org.dtmhapcs.service.interfaces.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FilmController {
    private FilmService filmService;    

    @Autowired(required = true)
    @Qualifier(value = "filmService")
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
    
    @RequestMapping(value = "searchPage")
    public String searchPage(){
        return "searchPage";
    }

    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST)
    public String createFilm(@Valid Film film, BindingResult bindingResult) { 
        if (bindingResult.hasErrors()){
            return "filmList";
        } else {
            this.filmService.createOrUpdate(film);           
            return "redirect:/filmList";
        }        
    }   
    
    @RequestMapping(value = "/filmList", method = RequestMethod.GET)
    public String filmList(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("filmList", this.filmService.readAllFilms());
        return "filmList";
    }

    @RequestMapping(value = "/deleteFilm/{filmId}", method = RequestMethod.GET)
    public String delete(@PathVariable("filmId") String filmId) {
        this.filmService.deleteFilm(filmId);
        return "redirect:/filmList";
    }
    
    @RequestMapping(value = "/readFilm/{filmId}", method = RequestMethod.GET)
    public String readFilmById(@PathVariable("filmId") String filmId, Model model) {
        model.addAttribute("film", this.filmService.readFilmById(filmId));
        model.addAttribute("filmList", this.filmService.readAllFilms());
        return "filmList";
    }
}