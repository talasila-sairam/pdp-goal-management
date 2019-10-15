package com.ssp.apps.pdp.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ssp.apps.pdp.entity.PdpGoalEntity;

public interface PdpGoalRepository extends CrudRepository<PdpGoalEntity, Integer> {

  Optional<PdpGoalEntity> findByGoal(String name);

}
