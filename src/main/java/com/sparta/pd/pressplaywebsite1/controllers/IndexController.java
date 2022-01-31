package com.sparta.pd.pressplaywebsite1.controllers;

import com.sparta.pd.pressplaywebsite1.FIlmAvailability;
import com.sparta.pd.pressplaywebsite1.User;
import com.sparta.pd.pressplaywebsite1.UserBasket;
import com.sparta.pd.pressplaywebsite1.UserDetails;
import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.entities.RentalEntity;
import com.sparta.pd.pressplaywebsite1.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class IndexController {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;
    public static UserBasket userBasket;
    private static UserDetails userDetails;
    private static FIlmAvailability fIlmAvailability;

    public IndexController(FilmRepository filmRepository, UserRepository userRepository, AddressRepository addressRepository,
    CityRepository cityRepository, CountryRepository countryRepository, RentalRepository rentalRepository, InventoryRepository inventoryRepository,
                           StoreRepository storeRepository) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.storeRepository = storeRepository;
        userBasket = new UserBasket(filmRepository, inventoryRepository);
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


    @GetMapping("/homepage")
    public String homePage() {
        return "index";
    }

    @GetMapping("checkout/{id}/{store}")
    public String checkoutPage(@PathVariable("id") Long id, @PathVariable("store") String store, Model model){
        RentalEntity rentalEntity = new RentalEntity();
        model.addAttribute("rental", rentalEntity);
        userBasket.addFilmToBasket(id, store);
        model.addAttribute("userBasket", userBasket);
        model.addAttribute("rental", rentalEntity);
        return "checkout";

    }

    @PostMapping("/rentFilms")
    public String rentFilms(@ModelAttribute("rental") RentalEntity rentalEntity) {
        userDetails = new UserDetails(userRepository,rentalRepository, inventoryRepository, filmRepository,
                storeRepository);
        RentalEntity rentalEntity1 = new RentalEntity();
        rentalEntity1.setRentalDate(new Timestamp(new Date().getTime()));
        rentalEntity1.setInventoryId(userBasket.getInventoryId(userBasket.getFilmsInBasket().get(0), userBasket.getFilmsStores().get(0)));
        rentalEntity1.setCustomerId(userDetails.getUserId());
        rentalEntity1.setStaffId(1L);
        rentalEntity1.setLastUpdate(new Timestamp(new Date().getTime()));
        rentalRepository.save(rentalEntity1);
        userBasket.clearBasket();
        return "filmRentSuccess";
    }


    @GetMapping("/selectFilm/{id}")
    public String getSingleFilmData(@PathVariable("id") Long id, Model model) {
        FilmEntity filmsEntity = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        model.addAttribute("singleFilm", filmsEntity);
        model.addAttribute("availability", new FIlmAvailability(inventoryRepository).checkFilmAvailability(id));
        return "single-film";
    }


    @GetMapping("/userPage")
    public String getUserPage(Model model) {
        model.addAttribute("user", new User(userRepository, addressRepository, cityRepository, countryRepository));
        model.addAttribute("rental", userDetails = new UserDetails(userRepository,rentalRepository, inventoryRepository, filmRepository,
            storeRepository));
        return "userPage";
    }
}