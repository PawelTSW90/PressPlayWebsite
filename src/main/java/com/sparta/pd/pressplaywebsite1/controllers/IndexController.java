package com.sparta.pd.pressplaywebsite1.controllers;

import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.repositories.CustomerRepository;
import com.sparta.pd.pressplaywebsite1.repositories.FilmRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;

@Controller
public class IndexController {

    private final FilmRepository filmRepository;
    private final CustomerRepository customerRepository;

    public IndexController(FilmRepository filmRepository, CustomerRepository customerRepository) {
        this.filmRepository = filmRepository;
        this.customerRepository = customerRepository;
    }


    @GetMapping("/about")
    public String getAboutPage() {
        return "about";

    }

    @GetMapping("/single-film")
    public String getSingleFilm() {
        return "single-film";

    }

    @GetMapping("/films")
    public String getFilmsPage(Model model) {
        model.addAttribute("film", filmRepository.findAll());
        return "films";
    }

    @PostMapping("/search-film")
    public String getSearchResults(@ModelAttribute("filmName") String filmName, Model model) {
        ArrayList<FilmEntity> foundFilms = new ArrayList<>();
        for (FilmEntity filmsEntity : filmRepository.findAll()) {
            if (filmsEntity.getTitle().contains(filmName)) {
                foundFilms.add(filmsEntity);
            }
        }
        model.addAttribute("searchResults", foundFilms);
        return "quick-search-result";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){
        return "access-denied";
    }

    @GetMapping("/selectFilm/{id}")
    public String getSingleFilmData(@PathVariable("id") String id, Model model) {
        FilmEntity filmsEntity = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        model.addAttribute("singleFilm", filmsEntity);
        return "single-film";
    }
}