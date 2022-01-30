package com.sparta.pd.pressplaywebsite1.controllers;

import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.repositories.*;
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
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;

    public IndexController(FilmRepository filmRepository, UserRepository userRepository, AddressRepository addressRepository,
    CityRepository cityRepository, CountryRepository countryRepository, RentalRepository rentalRepository, InventoryRepository inventoryRepository) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
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
    public String getAccessDeniedPage() {
        return "access-denied";
    }

    @PostMapping("/tryLogin")
    public String tryLogin() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "login";
    }

    @GetMapping("/homepage")
    public String homePage() {
        return "index";
    }

    @GetMapping("/selectFilm/{id}")
    public String getSingleFilmData(@PathVariable("id") String id, Model model) {
        FilmEntity filmsEntity = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        model.addAttribute("singleFilm", filmsEntity);
        return "single-film";
    }

    @GetMapping("/userPage")
    public String getUserPage(Model model) {
        model.addAttribute("user", new UserController(userRepository, addressRepository, cityRepository, countryRepository));
        model.addAttribute("rental", new RentalController(userRepository,rentalRepository, inventoryRepository, filmRepository));
        return "userPage";
    }
}