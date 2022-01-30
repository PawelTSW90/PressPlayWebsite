package com.sparta.pd.pressplaywebsite1.repositories;

import com.sparta.pd.pressplaywebsite1.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}
