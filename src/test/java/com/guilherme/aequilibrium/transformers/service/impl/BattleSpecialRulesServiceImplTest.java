package com.guilherme.aequilibrium.transformers.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class BattleSpecialRulesServiceImplTest {

    private static TransformerEntity OPTIMUS_PRIME;
    private static TransformerEntity PREDAKING;
    private static TransformerEntity RANDOM_AUTOBOT;
    private static TransformerEntity RANDOM_DECEPTICON;

    private static final String ALL_DESTROYED = "All Competitors destroyed";

    @InjectMocks
    BattleSpecialRulesServiceImpl service;

    @Before
    public void setup() {

	OPTIMUS_PRIME = TransformerEntity.builder().id(1L).name("Optimus Prime").team("A").build();
	PREDAKING = TransformerEntity.builder().id(2L).name("Predaking").team("D").build();
	RANDOM_AUTOBOT = TransformerEntity.builder().id(3L).name("Random Autobot").team("A").build();
	RANDOM_DECEPTICON = TransformerEntity.builder().id(2L).name("Random Decepticon").team("D").build();
    }

    @Test
    public void should_return_all_destroyed_if_optimus_prime_and_predaking_are_present_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service
		.applySpecialRules(Arrays.asList(OPTIMUS_PRIME, PREDAKING, RANDOM_DECEPTICON));
	assertNotNull(response);
	assertThat(response.getWinnerTeam()).isEqualTo(ALL_DESTROYED);
    }

    @Test
    public void should_return_all_destroyed_if_optimus_prime_is_duplicated_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service
		.applySpecialRules(Arrays.asList(OPTIMUS_PRIME, OPTIMUS_PRIME));
	assertNotNull(response);
	assertThat(response.getWinnerTeam()).isEqualTo(ALL_DESTROYED);
    }

    @Test
    public void should_return_all_destroyed_if_predaking_is_duplicated_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service.applySpecialRules(Arrays.asList(PREDAKING, PREDAKING));
	assertNotNull(response);
	assertThat(response.getWinnerTeam()).isEqualTo(ALL_DESTROYED);
    }

    @Test
    public void should_return_autobots_wins_if_optimus_prime_is_present_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service
		.applySpecialRules(Arrays.asList(RANDOM_AUTOBOT, OPTIMUS_PRIME, RANDOM_DECEPTICON));

	assertNotNull(response);
	assertThat(response.getWinnerTeam()).isEqualTo("A");
    }

    @Test
    public void should_return_decepticons_wins_if_predaking_is_present_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service
		.applySpecialRules(Arrays.asList(RANDOM_AUTOBOT, PREDAKING, RANDOM_DECEPTICON));

	assertNotNull(response);
	assertThat(response.getWinnerTeam()).isEqualTo("D");
    }

    @Test
    public void should_return_null_if_dont_have_any_team_lead_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service
		.applySpecialRules(Arrays.asList(RANDOM_AUTOBOT, RANDOM_DECEPTICON));

	assertNull(response);
    }

    @Test
    public void should_return_null_if_dont_have_any_transformers_when_verifySpecialRules() {
	TransformersBattleResponseDTO response = service.applySpecialRules(Arrays.asList());
	assertNull(response);
    }

}