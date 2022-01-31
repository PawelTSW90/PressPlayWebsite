package com.sparta.pd.pressplaywebsite1;

import com.sparta.pd.pressplaywebsite1.entities.AddressEntity;
import com.sparta.pd.pressplaywebsite1.entities.CityEntity;
import com.sparta.pd.pressplaywebsite1.entities.CountryEntity;
import com.sparta.pd.pressplaywebsite1.entities.UsersEntity;
import com.sparta.pd.pressplaywebsite1.repositories.AddressRepository;
import com.sparta.pd.pressplaywebsite1.repositories.CityRepository;
import com.sparta.pd.pressplaywebsite1.repositories.CountryRepository;
import com.sparta.pd.pressplaywebsite1.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class User {
    private final Authentication auth;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private UsersEntity usersEntity;
    private AddressEntity addressEntity;
    private CityEntity cityEntity;
    private CountryEntity countryEntity;



    public User(UserRepository userRepository, AddressRepository addressRepository, CityRepository cityRepository,
                CountryRepository countryRepository) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        setUserEntity();
        setAddressEntity();
        setCityEntity();
        setCountryEntity();
    }


    public void setUserEntity() {
        for (UsersEntity entity : userRepository.findAll()) {
            if (entity.getUserName().equals(auth.getName())) {
                usersEntity = entity;
                break;
            }
        }
    }


        public void setAddressEntity() {
        long userAddressId = usersEntity.getAddressId();
            for (AddressEntity entity : addressRepository.findAll()) {
                if(entity.getAddressId().equals(userAddressId)){
                    addressEntity = entity;
                    break;
                }
            }
    }

    public void setCityEntity() {
        long userCityId = addressEntity.getCityId();
        for(CityEntity entity : cityRepository.findAll()) {
            if(entity.getCityId().equals(userCityId)){
                cityEntity = entity;
            }
        }
    }

    public void setCountryEntity(){
        long userCountryId = cityEntity.getCountryId();
        for(CountryEntity entity: countryRepository.findAll()){
            if(entity.getCountryId().equals(userCountryId)){
                countryEntity = entity;
            }
        }
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

}
