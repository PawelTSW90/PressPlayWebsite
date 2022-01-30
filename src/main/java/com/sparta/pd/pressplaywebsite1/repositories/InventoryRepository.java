package com.sparta.pd.pressplaywebsite1.repositories;

import com.sparta.pd.pressplaywebsite1.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
