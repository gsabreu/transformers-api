package com.guilherme.aequilibrium.transformers.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.exception.TeamNotFoundException;
import com.guilherme.aequilibrium.transformers.model.Team;

@RunWith(SpringJUnit4ClassRunner.class)
public class TeamTest {

    @Test
    public void should_return_autobots_if_value_is_A_when_getByAcronym() {
	assertThat(Team.getByAcronym("A")).isEqualTo(Team.AUTOBOTS);
    }

    @Test
    public void should_return_decepticons_if_value_is_D_getByAcronym() {
	assertThat(Team.getByAcronym("D")).isEqualTo(Team.DECEPTICONS);
    }

    @Test(expected = TeamNotFoundException.class)
    public void should_return_exception_if_value_is_C_when_getByAcronym() {
	Team.getByAcronym("C");
    }
    
    @Test
    public void should_return_autobots_if_value_is_Autobots_when_getByName() {
	assertThat(Team.getByName("Autobots")).isEqualTo(Team.AUTOBOTS);
    }

    @Test
    public void should_return_decepticons_if_value_is_Decepticons_when_getByName() {
	assertThat(Team.getByName("Decepticons")).isEqualTo(Team.DECEPTICONS);
    }
    
    @Test(expected = TeamNotFoundException.class)
    public void should_return_exception_if_value_is_C_when_getByName() {
	Team.getByName("C");
    }

}
