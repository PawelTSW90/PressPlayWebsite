package com.sparta.pd.pressplaywebsite1.entities;

import org.apache.catalina.Store;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "sakila")
public class InventoryEntity {
    private Long inventoryId;
    private Long storeId;
    private Timestamp lastUpdate;
    private Long filmId;
    private Integer available;

    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmEntity filmEntity;

    public Long getFilmId(){
        return filmId;
    }

    public void setFilmId(Long filmId){
        this.filmId = filmId;
    }

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    public Long getStoreId(){
        return storeId;
    }

    public void setStoreId(Long storeId){
        this.storeId = storeId;
    }

    @Id
    @Column(name = "inventory_id")
    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Basic
    @Column(name = "available")
    public Integer getAvailable(){
        return available;
    }

    public void setAvailable(Integer available){
        this.available = available;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntity that = (InventoryEntity) o;
        return Objects.equals(inventoryId, that.inventoryId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, lastUpdate);
    }
}
