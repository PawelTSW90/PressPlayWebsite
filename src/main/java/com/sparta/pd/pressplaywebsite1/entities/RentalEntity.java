package com.sparta.pd.pressplaywebsite1.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "sakila")
public class RentalEntity {
    private Long rentalId;
    private Long customerId;
    private Long inventoryId;
    private Timestamp rentalDate;
    private Timestamp returnDate;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "rental_id")
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    @OneToOne
    @JoinColumn(name = "customer_id")
    private UsersEntity usersEntity;

    public Long getCustomerId(){
        return customerId;
    }


    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventoryEntity;

    public Long getInventoryId(){
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId){
        this.inventoryId = inventoryId;
    }

    @Basic
    @Column(name = "rental_date")
    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Basic
    @Column(name = "return_date")
    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
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
        RentalEntity that = (RentalEntity) o;
        return Objects.equals(rentalId, that.rentalId) && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(returnDate, that.returnDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, rentalDate, returnDate, lastUpdate);
    }
}
