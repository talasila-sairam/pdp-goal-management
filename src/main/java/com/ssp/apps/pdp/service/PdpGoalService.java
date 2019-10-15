package com.ssp.apps.pdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ssp.apps.pdp.dao.PdpGoalRepository;
import com.ssp.apps.pdp.entity.PdpGoalEntity;
import com.ssp.apps.pdp.exception.DuplicateRecordException;
import com.ssp.apps.pdp.exception.NoRecordFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PdpGoalService {

    private PdpGoalRepository pdpRepository;

    public List<PdpGoalEntity> getAllPdp() {
        return (List<PdpGoalEntity>) pdpRepository.findAll();
    }

    public Optional<PdpGoalEntity> getPdpGoal(Integer id) {
        return pdpRepository.findById(id);
    }


    public Optional<PdpGoalEntity> getPdpGoal(String name) {
        return pdpRepository.findByGoal(name);
    }

    public PdpGoalEntity savePdp(PdpGoalEntity pdpEntity) {
        Optional.ofNullable(pdpEntity)
                .orElseThrow(() -> new IllegalArgumentException("Pdp goal can not be null"));

        getPdpGoal(pdpEntity.getGoal()).ifPresent((temp) -> {
            throw new DuplicateRecordException(
                    "Pdp goal already exisyt with name: " + pdpEntity.getGoal());
        });

        return pdpRepository.save(pdpEntity);
    }

    public PdpGoalEntity updatePdpGoal(PdpGoalEntity pdpEntity) {
        Optional.ofNullable(pdpEntity)
                .orElseThrow(() -> new IllegalArgumentException("Pdp goal can not be null"));

        Optional.ofNullable(pdpEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Pdp goal id can not be null"));

        getPdpGoal(pdpEntity.getId()).orElseThrow(
                () -> new NoRecordFoundException("No Pdp goal found  with id: " + pdpEntity.getId()));

        return pdpRepository.save(pdpEntity);
    }


    public void deletePdpGoal(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Pdp goal Id can not be null"));

        getPdpGoal(id)
                .orElseThrow(() -> new NoRecordFoundException("No Pdp goal found  with id: " + id));

        pdpRepository.deleteById(id);
    }
}
