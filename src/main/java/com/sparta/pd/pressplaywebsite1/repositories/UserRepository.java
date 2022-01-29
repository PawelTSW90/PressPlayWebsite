package com.sparta.pd.pressplaywebsite1.repositories;

import com.sparta.pd.pressplaywebsite1.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {


}
