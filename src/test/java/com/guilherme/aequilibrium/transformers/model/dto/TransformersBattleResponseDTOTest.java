package com.guilherme.aequilibrium.transformers.model.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.guilherme.aequilibrium.transformers.model.Team;

public class TransformersBattleResponseDTOTest {

    private TransformersBattleResponseDTO modelWithArgs;
    private TransformersBattleResponseDTO modelWithoutArgs;
    private TransformersBattleResponseDTO modelWithBuilder;

    @Before
    public void setup() {
	modelWithArgs = new TransformersBattleResponseDTO(1, Team.DECEPTICONS.name, Arrays.asList());
	modelWithoutArgs = new TransformersBattleResponseDTO();
	modelWithBuilder = TransformersBattleResponseDTO
		.builder()
		.numberOfBattles(1)
		.winnerTeam(Team.DECEPTICONS.name)
		.losingTeamSurvivors(Arrays.asList())
		.build();
    }
    
    
    @Test
    public void should_return_numberOfBattles_when_getNumberOfBattles() {
	assertThat(modelWithArgs.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_set_numberOfBattles_when_setNumberOfBattles() {
	modelWithArgs.setNumberOfBattles(2);
	assertThat(modelWithArgs.getNumberOfBattles()).isEqualTo(2);
    }
    
    @Test
    public void should_return_winnerTeam_when_getWinnerTeam() {
	assertThat(modelWithArgs.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
    }

    @Test
    public void should_set_winnerTeam_when_setWinnerTeam() {
	modelWithArgs.setWinnerTeam(Team.AUTOBOTS.name);
	assertThat(modelWithArgs.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
    }
    
    
    @Test
    public void should_return_losingTeamSurvivors_when_getLosingTeamSurvivors() {
	assertThat(modelWithArgs.getLosingTeamSurvivors()).isEmpty();
    }
    
    @Test
    public void should_set_losingTeamSurvivors_when_setLosingTeamSurvivors() {
	modelWithArgs.setLosingTeamSurvivors(Arrays.asList("Random"));
	assertThat(modelWithArgs.getLosingTeamSurvivors()).isNotEmpty();
    }

    
    @Test
    public void should_return_true_when_use_equals() {
	assertTrue(modelWithArgs.equals(modelWithArgs));
    }

    @Test
    public void should_return_string_when_use_toString() {
	assertNotNull(modelWithArgs.toString());
    }

    @Test
    public void should_return_NotNull_when_validateBuilder() {
	assertNotNull(modelWithBuilder);
    }

    @Test
    public void should_return_not_null_when_use_hash_for_no_args_constructor() {
	assertNotNull(modelWithoutArgs.hashCode());
    }

}
