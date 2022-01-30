package com.sparta.pd.pressplaywebsite1.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "sakila")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Integer storeId;
    private Long addressId;
    private String userName;
    private String lastName;
    private String email;
    private Integer userEnabled;
    private String userRole;
    private Timestamp createDate;
    private Timestamp lastUpdate;
    private String password;

    public UsersEntity() {
    }

    public UsersEntity(String userName, String lastName, Integer storeId, String email, Integer userEnabled, String userRole, Timestamp createDate,
                       Timestamp lastUpdate, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.userEnabled = userEnabled;
        this.userRole = userRole;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.password = password;
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Basic
    @Column(name = "store_id")
    public Integer getStoreId(){
        return storeId;
    }

    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "address_id")
    public Long getAddressId(){
        return addressId;
    }

    public void setAddressId(Long addressId){
        this.addressId = addressId;
    }


    @Id
    @Column(name = "customer_id")
    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long customerId) {
        this.userId = customerId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String firstName) {
        this.userName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_enabled")
    public Integer getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Integer active) {
        this.userEnabled = active;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "user_role")
    public String getUserRole(){
        return userRole;
    }
    public void setUserRole(String userRole){
        userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(userEnabled, that.userEnabled) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, lastName, email, userEnabled, createDate, lastUpdate);
    }
}
