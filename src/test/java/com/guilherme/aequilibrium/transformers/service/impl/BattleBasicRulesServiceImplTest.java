package com.guilherme.aequilibrium.transformers.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class BattleBasicRulesServiceImplTest {

    private static TransformerEntity SOUNDWAVE;
    private static TransformerEntity BLUESTREAK;
    private static TransformerEntity HUBCAP;

    @InjectMocks
    private BattleBasicRulesServiceImpl service;

    @Before
    public void setup() {
	SOUNDWAVE = new TransformerEntity(1L, "Soundwave", Team.DECEPTICONS.acronym, 8, 9, 2, 6, 7, 5, 6, 10);
	BLUESTREAK = new TransformerEntity(2L, "Bluestreak", Team.AUTOBOTS.acronym, 6, 6, 7, 9, 5, 2, 9, 7);
	HUBCAP = new TransformerEntity(3L, "Hubcap", Team.AUTOBOTS.acronym, 4, 4, 4, 4, 4, 4, 4, 4);
    }

    @Test
    public void should_return_decepticon_wins_if_decepticon_is_stronger_than_autobot_when_applyRules() {
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(SOUNDWAVE, BLUESTREAK));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_autobot_wins_if_autobot_is_stronger_than_decepticon_when_applyRules() {

	TransformerEntity autobot = new TransformerEntity(1L, "Autobot Stronger", Team.AUTOBOTS.acronym, 8, 9, 2, 6, 7,
		8, 8, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(SOUNDWAVE, autobot));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_tie__if_autobot_is_stronger_than_autobot_when_applyRules() {

	TransformerEntity autobot = new TransformerEntity(1L, "Autobot Stronger", Team.AUTOBOTS.acronym, 8, 9, 2, 6, 7,
		5, 6, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(SOUNDWAVE, autobot));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo("Tie");
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_deceptipcon_wins_if_deceptipcon_is_more_courageous_than_autobot_when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "Decepticon courageous", Team.DECEPTICONS.acronym, 8,
		9, 2, 6, 7, 9, 6, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(decepticon, HUBCAP));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_deceptipcon_wins_if_deceptipcon_has_more_strength_than_autobot_when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "Decepticon Stronger", Team.DECEPTICONS.acronym, 8, 9,
		2, 6, 7, 4, 6, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(decepticon, HUBCAP));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_autobot_wins_if_autobot_is_more_courageous_than_decepticon_when_applyRules() {

	TransformerEntity autobot = new TransformerEntity(1L, "autobot courageous", Team.AUTOBOTS.acronym, 8, 9, 2, 6,
		7, 9, 6, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(autobot, SOUNDWAVE));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_autobot_wins_if_autobot_has_more_strength_than_decepticon__when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "decepticon", Team.DECEPTICONS.acronym, 1, 9, 2, 6, 7,
		4, 6, 10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(decepticon, BLUESTREAK));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_autobot_wins_if_autobot_has_more_skill_than_decepticon__when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "decepticon", Team.DECEPTICONS.acronym, 5, 9, 2, 6, 7,
		4, 6, 4);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(decepticon, BLUESTREAK));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_decepticon_wins_if_overall_rate_better_than_autobots_when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "decepticon", Team.DECEPTICONS.acronym, 4, 4, 4, 4, 4,
		4, 6, 4);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(decepticon, HUBCAP));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }

    @Test
    public void should_return_decepticons_wins_with_two_fighters_when_applyRules() {

	TransformerEntity decepticon = new TransformerEntity(1L, "decepticon", Team.DECEPTICONS.acronym, 4, 4, 4, 4, 4,
		4, 6, 4);

	TransformersBattleResponseDTO result = service
		.applyRules(Arrays.asList(decepticon, BLUESTREAK, HUBCAP, SOUNDWAVE));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(2);

    }

    @Test
    public void should_return_one_losing_survivor_and_decepticons_wins_if_has_3_fighter_when_applyRules() {
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(BLUESTREAK, HUBCAP, SOUNDWAVE));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
	assertThat(result.getLosingTeamSurvivors()).size().isEqualTo(1);
    }

    @Test
    public void should_return_zero_losing_survivor_and_autobot_wins_if_has_3_fighter_when_applyRules() {
	TransformerEntity strongDecepticon = new TransformerEntity(3L, "dec", Team.DECEPTICONS.acronym, 6, 6, 7, 8, 5,
		2, 9, 7);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(BLUESTREAK, HUBCAP, strongDecepticon));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
	assertThat(result.getLosingTeamSurvivors()).isEmpty();
    }

    @Test
    public void should_return_one_losing_survivor_and_autobot_wins_if_has_3_fighter_when_applyRules() {
	TransformerEntity weakDecepticon = new TransformerEntity(3L, "dec", Team.DECEPTICONS.acronym, 4, 4, 4, 4, 4, 4,
		4, 4);
	TransformerEntity strongDecepticon = new TransformerEntity(3L, "dec", Team.DECEPTICONS.acronym, 6, 6, 7, 8, 5,
		2, 9, 7);
	TransformersBattleResponseDTO result = service
		.applyRules(Arrays.asList(BLUESTREAK, weakDecepticon, strongDecepticon));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.AUTOBOTS.name);
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
	assertThat(result.getLosingTeamSurvivors()).size().isEqualTo(1);
    }

    @Test
    public void should_return_tie_if_has_3_fighter_when_applyRules() {
	TransformerEntity autobot = new TransformerEntity(1L, "autobot", Team.AUTOBOTS.acronym, 8, 9, 2, 6, 7, 5, 6,
		10);
	TransformersBattleResponseDTO result = service.applyRules(Arrays.asList(autobot, HUBCAP, SOUNDWAVE));
	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo("Tie");
	assertThat(result.getNumberOfBattles()).isEqualTo(1);
    }
}
