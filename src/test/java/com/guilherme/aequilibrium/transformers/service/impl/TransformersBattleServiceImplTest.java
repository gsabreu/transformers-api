package com.guilherme.aequilibrium.transformers.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;
import com.guilherme.aequilibrium.transformers.service.BattleBasicRulesService;
import com.guilherme.aequilibrium.transformers.service.BattleSpecialRulesService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransformersBattleServiceImplTest {

    @InjectMocks
    private TransformersBattleServiceImpl service;

    @Mock
    private TransformersRepository transformersRepository;

    @Mock
    private BattleSpecialRulesService battleSpecialRulesService;

    @Mock
    private BattleBasicRulesService battleBasicRulesService;

    @Test
    public void shoul_return_Autobots_wins_if_transformers_has_Optimus_prime_when_getTeamWinner() {

	List<TransformerEntity> repositoryResponse = Arrays.asList(
		TransformerEntity.builder().id(1L).name("Optimus Prime").team(Team.AUTOBOTS.acronym).build(),
		TransformerEntity.builder().id(2L).name("Random Decepticon").team(Team.DECEPTICONS.acronym).build());

	// setup
	when(transformersRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(repositoryResponse);
	when(battleSpecialRulesService.applyRules(repositoryResponse))
		.thenReturn(TransformersBattleResponseDTO.builder().winnerTeam(Team.AUTOBOTS.name).build());

	assertNotNull(service.getTeamWinner(Arrays.asList(1L, 2L)));

    }

    @Test
    public void shoul_return_Decepticons_wins_if_decepction_has_mora_corage_than_autobot_when_getTeamWinner() {

	List<TransformerEntity> repositoryResponse = Arrays.asList(
		TransformerEntity.builder().id(3L).name("Random Autobot").team(Team.AUTOBOTS.acronym).courage(1)
			.build(),
		TransformerEntity.builder().id(4L).name("Random Decepticon").team(Team.DECEPTICONS.acronym).courage(6)
			.build());

	// setup
	when(transformersRepository.findAllById(Arrays.asList(3L, 4L))).thenReturn(repositoryResponse);
	when(battleSpecialRulesService.applyRules(repositoryResponse)).thenReturn(null);

	when(battleBasicRulesService.applyRules(repositoryResponse))
		.thenReturn(TransformersBattleResponseDTO.builder().winnerTeam(Team.DECEPTICONS.name).build());

	TransformersBattleResponseDTO result = service.getTeamWinner(Arrays.asList(3L, 4L));

	assertNotNull(result);
	assertThat(result.getWinnerTeam()).isEqualTo(Team.DECEPTICONS.name);

    }

    @Test
    public void shoul_return_null_if_transformers_dont_Exist_when_getTeamWinner() {
	when(transformersRepository.findAllById(Arrays.asList(10L, 11L))).thenReturn(Arrays.asList());
	assertNull(service.getTeamWinner(Arrays.asList(10L, 11L)));

    }

}
