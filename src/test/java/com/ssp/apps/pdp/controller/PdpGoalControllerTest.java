package com.ssp.apps.pdp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssp.apps.pdp.entity.PdpGoalEntity;
import com.ssp.apps.pdp.service.PdpGoalService;

@RunWith(SpringRunner.class)
@WebMvcTest(PdpGoalController.class)
public class PdpGoalControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PdpGoalService pdpGoalService;

	@Test
	public void testGetAllPdpGoals() throws Exception {
		List<PdpGoalEntity> pdpGoals = new ArrayList<PdpGoalEntity>();
		pdpGoals.add(new PdpGoalEntity("Java8"));
		when(pdpGoalService.getAllPdp()).thenReturn(pdpGoals);

		mockmvc.perform(get("/pdpgoal")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$.[0].goal").value("Java8"));

	}

	@Test
	public void testSave() throws Exception {
		PdpGoalEntity pdpGoalEntity = new PdpGoalEntity("Java8");
		when(pdpGoalService.savePdp(any(PdpGoalEntity.class)))
				.thenReturn(pdpGoalEntity);
		mockmvc.perform(post("/pdpgoal")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonValue(pdpGoalEntity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.goal").value("Java8"));
	}

	private String asJsonValue(PdpGoalEntity pdpGoalEntity)
			throws JsonProcessingException {
		return objectMapper.writeValueAsString(pdpGoalEntity);
	}

}
