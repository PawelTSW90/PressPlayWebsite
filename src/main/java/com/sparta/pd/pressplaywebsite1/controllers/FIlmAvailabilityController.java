package com.sparta.pd.pressplaywebsite1.controllers;

import com.sparta.pd.pressplaywebsite1.entities.InventoryEntity;
import com.sparta.pd.pressplaywebsite1.repositories.InventoryRepository;

import java.util.ArrayList;

public class FIlmAvailabilityController {
    private final InventoryRepository inventoryRepository;

    public FIlmAvailabilityController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public ArrayList<String> checkFilmAvailability(String filmId) {
        ArrayList<String> availableStores = new ArrayList<>();
        for (InventoryEntity inventoryEntity : inventoryRepository.findAll()) {
            if (String.valueOf(inventoryEntity.getFilmId()).equals(filmId)) {
                if (inventoryEntity.getAvailable() == 1) {
                    if (inventoryEntity.getStoreId() == 1) {
                        if(!availableStores.contains("Skegness")) {
                            availableStores.add("Skegness");
                        }
                    } else if (inventoryEntity.getStoreId() == 2) {
                        if(!availableStores.contains("Stoke-On-Trent")) {
                            availableStores.add("Stoke-On-Trent");
                        }
                    } else if (inventoryEntity.getStoreId() == 3) {
                        if(!availableStores.contains("Wells")) {
                            availableStores.add("Wells");
                        }
                    } else if (inventoryEntity.getStoreId() == 4) {
                        if(!availableStores.contains("Salisbury")) {
                            availableStores.add("Salisbury");
                        }
                    } else if (inventoryEntity.getStoreId() == 5) {
                        if(!availableStores.contains("Ripon")) {
                            availableStores.add("Ripon");
                        }
                    }
                }

            }
        }
        return availableStores;

    }
}
