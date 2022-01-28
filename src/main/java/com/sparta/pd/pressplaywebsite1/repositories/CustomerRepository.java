package com.sparta.pd.pressplaywebsite1.repositories;

import com.sparta.pd.pressplaywebsite1.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {


}
