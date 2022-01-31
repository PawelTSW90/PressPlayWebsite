package com.sparta.pd.pressplaywebsite1.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "staff", schema = "sakila")
public class StaffEntity {
    private Long staffId;
    private String userName;
    private String lastName;
    private byte[] picture;
    private String email;
    private Byte userEnabled;
    private String username;
    private String password;
    private Timestamp lastUpdate;
    private String userRole;

    @Id
    @Column(name = "staff_id")
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
    public Byte getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Byte userEnabled) {
        this.userEnabled = userEnabled;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffEntity that = (StaffEntity) o;
        return Objects.equals(staffId, that.staffId) && Objects.equals(userName, that.userName) && Objects.equals(lastName, that.lastName) && Arrays.equals(picture, that.picture) && Objects.equals(email, that.email) && Objects.equals(userEnabled, that.userEnabled) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(staffId, userName, lastName, email, userEnabled, username, password, lastUpdate, userRole);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
