package com.miltech.deliveryservice.repository;

import com.miltech.deliveryservice.interfaces.BatteryLevel;
import com.miltech.deliveryservice.interfaces.Id;
import com.miltech.deliveryservice.model.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Long> {
    Optional<Id> findTopByOrderByIdDesc();
    Optional<BoxEntity> findTopByWeightLessThanOrderByWeightAsc(Double weight);
    @Query("SELECT b FROM BoxEntity b WHERE b.weight < 500")
    List<BoxEntity> findBoxesWithWeightLessThan500();
    Optional<BatteryLevel> findBatteryLevelByBoxCode(String boxCode);
    Optional<BoxEntity> findByBoxCode(String boxCode);

}
