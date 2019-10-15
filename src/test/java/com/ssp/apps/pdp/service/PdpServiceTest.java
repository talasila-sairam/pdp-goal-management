package com.ssp.apps.pdp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import com.ssp.apps.pdp.dao.PdpGoalRepository;
import com.ssp.apps.pdp.entity.PdpGoalEntity;
import com.ssp.apps.pdp.exception.DuplicateRecordException;
import com.ssp.apps.pdp.exception.NoRecordFoundException;

@RunWith(MockitoJUnitRunner.class)
public class PdpServiceTest {

    @Mock
    PdpGoalRepository pdpGoalRepository;

    @InjectMocks
    private PdpGoalService pdpGoalService;

    @Test(expected = IllegalArgumentException.class)
    public void savePdpGoal_should_throw_IllegalArgumentException() {
        pdpGoalService.savePdp(null);
    }

    @Test(expected = DuplicateRecordException.class)
    public void savePdpGoal_should_throw_DuplicateRecordException() {
        when(pdpGoalRepository.findByGoal(anyString())).thenReturn(Optional.of(new PdpGoalEntity("Java8")));
        pdpGoalService.savePdp(new PdpGoalEntity("Java8"));
    }

    @Test
    public void savePdpGoal_shoud_save_return_id() {
        PdpGoalEntity pdpEntity = new PdpGoalEntity("Unit Testing");

        PdpGoalEntity pdpEntityReturn = new PdpGoalEntity("Unit Testing");
        pdpEntityReturn.setId(1);

        when(pdpGoalRepository.save(any(PdpGoalEntity.class))).thenReturn(pdpEntityReturn);
        when(pdpGoalRepository.findByGoal(anyString())).thenReturn(Optional.empty());

        // doNothing().when(pdpGoalRepository).save(pdpEntity);

        PdpGoalEntity savedEntity = pdpGoalService.savePdp(pdpEntity);

        verify(pdpGoalRepository, atMost(1)).save(pdpEntity);
        assertNotNull(savedEntity.getGoal(), is("Unit Testing"));
        assertNotNull(savedEntity.getId());
    }

    @Test
    public void savePdpGoal_shoud_save_return_id_thenAnswer() {
        PdpGoalEntity pdpEntity = new PdpGoalEntity("Unit Testing");

        when(pdpGoalRepository.findByGoal(anyString())).thenReturn(Optional.empty());
        when(pdpGoalRepository.save(any(PdpGoalEntity.class))).thenAnswer(new Answer<PdpGoalEntity>() {

            @Override
            public PdpGoalEntity answer(InvocationOnMock invocation) throws Throwable {
                PdpGoalEntity pdpGoal = invocation.getArgument(0);
                pdpGoal.setId(100001);
                return pdpGoal;
            }
        });

        PdpGoalEntity savedEntity = pdpGoalService.savePdp(pdpEntity);

        verify(pdpGoalRepository, atMost(1)).save(pdpEntity);
        assertNotNull(savedEntity.getGoal(), is("Unit Testing"));
        assertNotNull(savedEntity.getId());
    }

    @Test(expected = DuplicateRecordException.class)
    public void savePdpGoal_should_throw_duplicateRecordException() {
        PdpGoalEntity pdpEntity = new PdpGoalEntity("XXXXX");
        when(pdpGoalRepository.findByGoal(anyString()))
                .thenReturn(Optional.of(new PdpGoalEntity("ZZZZ")));
        pdpGoalService.savePdp(pdpEntity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePdpGoal_should_throw_exception() {
        pdpGoalService.updatePdpGoal(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateGoal_should_throw_IllegalArgumentException_with_noid() {
        pdpGoalService.updatePdpGoal(new PdpGoalEntity("Java8"));
    }

    @Test(expected = NoRecordFoundException.class)
    public void updateGoal_should_throw_NoRecordFoundException() {
        PdpGoalEntity pdpGoal = new PdpGoalEntity("Java8");
        pdpGoal.setId(10);
        when(pdpGoalRepository.findById(anyInt())).thenReturn(Optional.empty());
        pdpGoalService.updatePdpGoal(pdpGoal);
    }

    @Test
    public void shouldUpdate_pdpgoal() {
        PdpGoalEntity pdpEntity = new PdpGoalEntity(10, "Java8");
        when(pdpGoalRepository.findById(anyInt())).thenReturn(Optional.of(pdpEntity));
        pdpGoalService.updatePdpGoal(pdpEntity);

        verify(pdpGoalRepository, atMost(1)).save(pdpEntity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletePdpGoal_should_throw_IllegalArgumentException() {
        pdpGoalService.deletePdpGoal(null);
    }


    @Test(expected = NoRecordFoundException.class)
    public void deletePdpGoal_should_throw_NoRecordFoundException() {
        when(pdpGoalRepository.findById(anyInt())).thenReturn(Optional.empty());
        pdpGoalService.deletePdpGoal(10);
    }

    @Test
    public void shouldDelete_pdpgoal() {
        when(pdpGoalRepository.findById(anyInt())).thenReturn(Optional.of(new PdpGoalEntity(10, "Java8")));
        pdpGoalService.deletePdpGoal(10);
        verify(pdpGoalRepository, atMost(1)).deleteById(10);
    }
}
