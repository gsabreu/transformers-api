package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;
import com.guilherme.aequilibrium.transformers.service.BattleSpecialRulesService;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

@Service
public class TransformersBattleServiceImpl implements TransformersBattleService {

    @Autowired
    private TransformersRepository transformersRepository;

    @Autowired
    private BattleSpecialRulesService battleSpecialRulesService;

    @Override
    public TransformersBattleResponseDTO getTransfomersWinnerFromTheBattle(List<Long> transformersIds) {
	List<TransformerEntity> transformers = transformersRepository.findAllById(transformersIds);
	TransformersBattleResponseDTO transformersBattleResponseDTO = null;

	if (transformers != null && !transformers.isEmpty()) {
	    transformersBattleResponseDTO = battleSpecialRulesService.applySpecialRules(transformers);

	    if (transformersBattleResponseDTO != null) {
		List<TransformerEntity> autobots = this.sliptTeamByType(transformers, "A");
		List<TransformerEntity> decepticons = this.sliptTeamByType(transformers, "D");

		this.sortTeamByRank(autobots);
		this.sortTeamByRank(decepticons);

	    }
	}

	return transformersBattleResponseDTO;
    }

    private Integer getTransformerRank(TransformerEntity transfomer) {
	return transfomer.getRank();
    }

    private List<TransformerEntity> sliptTeamByType(List<TransformerEntity> transformers, String type) {
	return transformers.stream().filter(transformer -> transformer.getTeam().equalsIgnoreCase(type))
		.collect(Collectors.toList());
    }

    private void sortTeamByRank(List<TransformerEntity> transformers) {
	transformers.sort(Comparator.comparingInt(this::getTransformerRank));
    }

    private Integer getOverallRating(TransformerEntity transformer) {
	return transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed()
		+ transformer.getEndurance() + transformer.getFirepower();
    }

}
