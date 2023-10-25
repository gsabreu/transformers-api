package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.service.BattleSpecialRulesService;

@Service
public class BattleSpecialRulesServiceImpl implements BattleSpecialRulesService {

    private static final String OPTIMUS_PRIME = "Optimus Prime";
    private static final String PREDAKING = "Predaking";

    @Override
    public TransformersBattleResponseDTO applyRules(List<TransformerEntity> transformers) {
	Set<String> transformersSet = transformers.stream().map(TransformerEntity::getName)
		.collect(Collectors.toSet());
	TransformersBattleResponseDTO response = null;

	if ((transformersSet.contains(OPTIMUS_PRIME) && transformersSet.contains(PREDAKING))
		|| transformers.stream().filter(transformer -> transformer.getName().equalsIgnoreCase(OPTIMUS_PRIME))
			.count() > 1
		|| transformers.stream().filter(transformer -> transformer.getName().equalsIgnoreCase(PREDAKING))
			.count() > 1) {
	    return TransformersBattleResponseDTO.builder().winnerTeam("All Competitors destroyed").build();
	}

	else if (transformersSet.contains(OPTIMUS_PRIME) && !transformersSet.contains(PREDAKING)) {
	    return TransformersBattleResponseDTO.builder().winnerTeam(Team.AUTOBOTS.name).build();
	}

	else if (transformersSet.contains(PREDAKING) && !transformersSet.contains(OPTIMUS_PRIME)) {
	    return TransformersBattleResponseDTO.builder().winnerTeam(Team.DECEPTICONS.name).build();
	}

	return response;
    }

}
