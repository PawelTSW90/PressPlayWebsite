package com.sparta.pd.pressplaywebsite1;

import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.entities.InventoryEntity;
import com.sparta.pd.pressplaywebsite1.repositories.FilmRepository;
import com.sparta.pd.pressplaywebsite1.repositories.InventoryRepository;

import java.util.ArrayList;

public class UserBasket {
    private static ArrayList<FilmEntity> filmsInBasket = new ArrayList<>();
    private static ArrayList<String> filmsStores = new ArrayList<>();
    private final FilmRepository filmRepository;
    private InventoryRepository inventoryRepository;

    public UserBasket(FilmRepository filmRepository, InventoryRepository inventoryRepository) {
        this.filmRepository = filmRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void addFilmToBasket(Long filmId, String store){
        for (FilmEntity filmEntity: filmRepository.findAll()){
            if(filmEntity.getFilmId().equals(filmId)){
                filmsInBasket.add(filmEntity);
                filmsStores.add(store);
            }
        }
    }

    public  void clearBasket(){
        filmsInBasket = new ArrayList<>();
        filmsStores = new ArrayList<>();
    }

    public Long getInventoryId(FilmEntity filmEntity, String store){
        long storeId;
        for (InventoryEntity inventoryEntity: inventoryRepository.findAll()){
            if(inventoryEntity.getFilmId().equals(filmEntity.getFilmId())){
                storeId = inventoryEntity.getStoreId();
                if(storeId==1){
                    if(store.equals("Skegness")){
                        inventoryEntity.setAvailable(0);
                        return inventoryEntity.getInventoryId();
                    }
                }else if(storeId==2){
                    if(store.equals("Stoke-On-Trent")){
                        inventoryEntity.setAvailable(0);
                        return inventoryEntity.getInventoryId();
                    }
                }else if(storeId==3){
                    if(store.equals("Wells")){
                        inventoryEntity.setAvailable(0);
                        return inventoryEntity.getInventoryId();
                    }
                }else if(storeId==4){
                    if(store.equals("Salisbury")){
                        inventoryEntity.setAvailable(0);
                        return inventoryEntity.getInventoryId();
                    }
                }else if(storeId==5){
                    if(store.equals("Ripon")){
                        inventoryEntity.setAvailable(0);
                        return inventoryEntity.getInventoryId();
                    }
                }

            }
        }
        return null;
    }

    public Long getStaffId(String store){
        return switch (store) {
            case "Skegness" -> 1L;
            case "Stoke-On-Trent" -> 2L;
            case "Wells" -> 3L;
            case "Salisbury" -> 4L;
            case "Ripon" -> 5L;
            default -> null;
        };
    }

    public ArrayList<FilmEntity> getFilmsInBasket() {
        return filmsInBasket;
    }

    public ArrayList<String> getFilmsStores() {
        return filmsStores;
    }
}
