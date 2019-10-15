package com.ssp.apps.pdp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssp.apps.pdp.entity.PdpGoalEntity;
import com.ssp.apps.pdp.service.PdpGoalService;

@RestController
@RequestMapping("/pdpgoal")
public class PdpGoalController {

	@Autowired
	private PdpGoalService pdpGoalService;

	@GetMapping
	public List<PdpGoalEntity> getAllPdpGoals() {
		return pdpGoalService.getAllPdp();
	}

	@GetMapping("/{id}")
	public PdpGoalEntity getPdpGoal(@PathVariable Integer id) {
		return pdpGoalService.getPdpGoal(id).get();
	}

	@PostMapping
	public PdpGoalEntity savePdpGoal(@RequestBody PdpGoalEntity pdpGoalEntity) {
		return pdpGoalService.savePdp(pdpGoalEntity);
	}

	@PutMapping("/{id}")
	public PdpGoalEntity updatePdpGoal(@RequestBody PdpGoalEntity pdpGoalEntity,
			@PathVariable Integer id) {
		return pdpGoalService.updatePdpGoal(pdpGoalEntity);
	}

	@DeleteMapping("/{id}")
	public void deletePdpGoals(@PathVariable Integer id) {
		pdpGoalService.deletePdpGoal(id);
	}
}
