package com.sparta.pd.pressplaywebsite1.controllers;

import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.entities.InventoryEntity;
import com.sparta.pd.pressplaywebsite1.entities.RentalEntity;
import com.sparta.pd.pressplaywebsite1.entities.UsersEntity;
import com.sparta.pd.pressplaywebsite1.repositories.FilmRepository;
import com.sparta.pd.pressplaywebsite1.repositories.InventoryRepository;
import com.sparta.pd.pressplaywebsite1.repositories.RentalRepository;
import com.sparta.pd.pressplaywebsite1.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class RentalController {
    private final Authentication auth;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private ArrayList<RentalEntity> filmsHistoryRentalEntities = new ArrayList<>();
    private ArrayList<RentalEntity> filmsToReturnRentalEntities = new ArrayList<>();
    private ArrayList<InventoryEntity> filmsHistoryInventoryEntities = new ArrayList<>();
    private ArrayList<InventoryEntity> filmsToReturnInventoryEntities = new ArrayList<>();
    private ArrayList<FilmEntity> filmsHistoryFilmEntities = new ArrayList<>();
    private ArrayList<FilmEntity> filmsToReturnFilmEntities = new ArrayList<>();
    private Long userId;

    public RentalController(UserRepository userRepository, RentalRepository rentalRepository, InventoryRepository inventoryRepository,
                            FilmRepository filmRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.filmRepository = filmRepository;
        auth = SecurityContextHolder.getContext().getAuthentication();
        setUserId();
        setUserRentalEntities(userId);
        setUserInventoryEntities(filmsHistoryRentalEntities, false);
        setUserInventoryEntities(filmsToReturnRentalEntities, true);
        setFilmEntities(filmsHistoryInventoryEntities, false);
        setFilmEntities(filmsToReturnInventoryEntities, true);
    }

    public void setUserId() {
        for (UsersEntity entity : userRepository.findAll()) {
            if (entity.getUserName().equals(auth.getName())) {
                userId = entity.getUserId();
                break;
            }
        }
    }

    public Integer getRentedFilmsNumber() {
        return filmsToReturnRentalEntities.size();
    }

    public void setUserRentalEntities(Long userId) {
        filmsHistoryRentalEntities = new ArrayList<>();
        filmsToReturnRentalEntities = new ArrayList<>();
        for (RentalEntity rentalEntity : rentalRepository.findAll()) {
            if (rentalEntity.getCustomerId().equals(userId)) {
                try {
                    if (rentalEntity.getReturnDate().equals(null)) {

                    } else {
                        filmsHistoryRentalEntities.add(rentalEntity);
                    }
                } catch (NullPointerException e) {
                    filmsToReturnRentalEntities.add(rentalEntity);
                }


            }
        }
    }

    public void setUserInventoryEntities(ArrayList<RentalEntity> rentalEntities, boolean filmToReturn) {
        for (InventoryEntity inventoryEntity : inventoryRepository.findAll()) {
            for (RentalEntity rentalEntity : rentalEntities) {
                if (inventoryEntity.getInventoryId().equals(rentalEntity.getInventoryId())) {
                    if(filmToReturn) {
                        filmsToReturnInventoryEntities.add(inventoryEntity);
                    } else{
                        filmsHistoryInventoryEntities.add(inventoryEntity);
                    }
                }
            }
        }
    }

    public void setFilmEntities(ArrayList<InventoryEntity> inventoryEntities, boolean filmToReturn) {
        for (FilmEntity filmEntity : filmRepository.findAll()) {
            for (InventoryEntity inventoryEntity : inventoryEntities) {
                if (filmEntity.getFilmId().equals(inventoryEntity.getFilmId())) {
                    if(filmToReturn) {
                        filmsToReturnFilmEntities.add(filmEntity);
                    } else{
                        filmsHistoryFilmEntities.add(filmEntity);
                    }

                }
            }
        }
    }

    public ArrayList<RentalEntity> getFilmsHistoryRentalEntities() {
        return filmsHistoryRentalEntities;
    }

    public ArrayList<InventoryEntity> getFilmsHistoryInventoryEntities() {
        return filmsHistoryInventoryEntities;
    }

    public ArrayList<FilmEntity> getFilmsHistoryFilmEntities() {
        return filmsHistoryFilmEntities;
    }

    public ArrayList<RentalEntity> getFilmsToReturnRentalEntities() {
        return filmsToReturnRentalEntities;
    }

    public ArrayList<FilmEntity> getFilmsToReturnFilmEntities() {
        return filmsToReturnFilmEntities;
    }
}
