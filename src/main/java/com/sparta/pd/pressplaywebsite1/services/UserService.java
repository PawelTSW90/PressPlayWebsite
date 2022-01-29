package com.sparta.pd.pressplaywebsite1.services;

import com.sparta.pd.pressplaywebsite1.entities.UsersEntity;
import com.sparta.pd.pressplaywebsite1.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getUserId(){
        for(UsersEntity entity: userRepository.findAll()){
            if(entity.getUserName().equals(getUserName())){
                return entity.getUserId();
            }
        }
        return 0L;
    }
    public String getUserName(){
        return auth.getName();
    }
}
