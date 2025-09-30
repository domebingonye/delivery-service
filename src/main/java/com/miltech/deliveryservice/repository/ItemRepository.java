package com.miltech.deliveryservice.repository;

import com.miltech.deliveryservice.interfaces.BoxCode;
import com.miltech.deliveryservice.interfaces.Id;
import com.miltech.deliveryservice.interfaces.Weight;
import com.miltech.deliveryservice.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<Id> findTopByOrderByIdDesc();
    List<ItemEntity> findByBoxCode(String boxCode);
    Optional<Weight> findWeightByCode(String code);
    Optional<BoxCode> findByCode(String code);
}
