package com.sparta.pd.pressplaywebsite1;

import com.sparta.pd.pressplaywebsite1.entities.FilmEntity;
import com.sparta.pd.pressplaywebsite1.entities.InventoryEntity;
import com.sparta.pd.pressplaywebsite1.repositories.FilmRepository;
import com.sparta.pd.pressplaywebsite1.repositories.InventoryRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class UserFilm {
    private final FilmRepository filmRepository;
    private final InventoryRepository inventoryRepository;
    private FilmEntity filmEntity;
    private InventoryEntity inventoryEntity;
    private String filmTitle;
    private String rentalDuration;
    private Double price;
    private Long inventoryId;
    private Long staffId;
    private String store;
    private Long filmId;

    public UserFilm(Long filmId, String store, FilmRepository filmRepository, InventoryRepository inventoryRepository) {
        this.store = store;
        this.filmId = filmId;
        this.filmRepository = filmRepository;
        this.inventoryRepository = inventoryRepository;
        setFilmData();

    }

    public void setStaffId(){
         switch (store) {
             case "Skegness" -> this.staffId = 1L;
             case "Stoke-On-Trent" -> this.staffId = 2L;
             case "Wells" -> this.staffId = 3L;
             case "Salisbury" -> this.staffId = 4L;
             case "Ripon" -> this.staffId = 5L;
         }
    }


    public void setFilmEntity(Long filmId){
        for (FilmEntity filmEntity: filmRepository.findAll()){
            if(filmEntity.getFilmId().equals(filmId)){
                this.filmEntity = filmEntity;
                break;
            }
        }
    }
    public void setFilmAsRented(){
        inventoryEntity.setAvailable(0);
        inventoryRepository.save(inventoryEntity);
    }

    public InventoryEntity getInventoryEntity() {
        return inventoryEntity;
    }

    public void setInventoryId(){
        long storeId;
        for (InventoryEntity inventoryEntity: inventoryRepository.findAll()){
            if(inventoryEntity.getFilmId().equals(filmEntity.getFilmId())){
                storeId = inventoryEntity.getStoreId();
                if(storeId==1){
                    if(store.equals("Skegness")){
                        this.inventoryEntity = inventoryEntity;
                        inventoryEntity.setAvailable(0);
                        inventoryId = inventoryEntity.getInventoryId();
                        break;
                    }
                }else if(storeId==2){
                    if(store.equals("Stoke-On-Trent")){
                        this.inventoryEntity = inventoryEntity;
                        inventoryId = inventoryEntity.getInventoryId();
                        break;
                    }
                }else if(storeId==3){
                    if(store.equals("Wells")){
                        this.inventoryEntity = inventoryEntity;
                        inventoryId = inventoryEntity.getInventoryId();
                        break;
                    }
                }else if(storeId==4){
                    if(store.equals("Salisbury")){
                        this.inventoryEntity = inventoryEntity;
                        inventoryId = inventoryEntity.getInventoryId();
                        break;
                    }
                }else if(storeId==5){
                    if(store.equals("Ripon")){
                        this.inventoryEntity = inventoryEntity;
                        inventoryId = inventoryEntity.getInventoryId();
                        break;
                    }
                }

            }
        }
    }

    public void setFilmData(){
        setStaffId();
        setFilmEntity(filmId);
        setFilmTitle();
        setRentalDuration();
        setPrice();
        setInventoryId();
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle() {
        this.filmTitle = filmEntity.getTitle();
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration() {
        Timestamp returnDate = new Timestamp(new Date().getTime());
        Integer rentDays;
        rentDays = filmEntity.getRentalDuration();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(returnDate);
        calendar.add(Calendar.DAY_OF_WEEK, rentDays);
        returnDate.setTime(calendar.getTime().getTime());
        this.rentalDuration = rentDays + " days (" + returnDate + ")";
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = filmEntity.getRentalRate();
    }

    public Long getInventoryId() {
        return inventoryId;
    }


    public String getStore() {
        return store;
    }
}
