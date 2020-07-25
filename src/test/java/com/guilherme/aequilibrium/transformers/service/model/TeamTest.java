package com.guilherme.aequilibrium.transformers.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.handler.TeamNotFoundException;
import com.guilherme.aequilibrium.transformers.model.Team;

@RunWith(SpringJUnit4ClassRunner.class)
public class TeamTest {

    @Test
    public void should_return_autobots_if_value_is_A_when_getValue() {
	assertThat(Team.getByAcronym("A")).isEqualTo(Team.AUTOBOTS);
    }

    @Test
    public void should_return_decepticons_if_value_is_D_when_getValue() {
	assertThat(Team.getByAcronym("D")).isEqualTo(Team.DECEPTICONS);
    }

    @Test(expected = TeamNotFoundException.class)
    public void should_return_exception_if_value_is_C_when_getValue() {
	Team.getByAcronym("C");
    }

}
