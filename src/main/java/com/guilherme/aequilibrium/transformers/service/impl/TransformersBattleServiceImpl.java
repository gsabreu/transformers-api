package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

@Service
public class TransformersBattleServiceImpl implements TransformersBattleService {

    @Autowired
    private TransformersRepository transformersRepository;

    @Autowired
    private TransfomersBasicRulesService transfomersBasicRulesService;

    @Override
    public TransformersBattleResponseDTO getTransfomersWinnerFromTheBattle(List<Long> transformersIds) {
	List<TransformerEntity> transfomers = transformersRepository.findAllById(transformersIds);
	this.verifySpecialRules(transfomers);
	return null;
    }

    private TransformersBattleResponseDTO verifySpecialRules(List<TransformerEntity> transfomers) {
	Set<String> transformersSet = transfomers.stream().map(transfomer -> transfomer.getName())
		.collect(Collectors.toSet());
	TransformersBattleResponseDTO response = null;

	if (transformersSet.contains("Optimus Prime") && !transformersSet.contains("Predaking")) {
	    return TransformersBattleResponseDTO.builder().winnerTeam("A").build();
	} else if (transformersSet.contains("Predaking") && !transformersSet.contains("Optimus Prime")) {
	    return TransformersBattleResponseDTO.builder().winnerTeam("D").build();
	} else if (transformersSet.contains("Optimus Prime") && !transformersSet.contains("Predaking")) {
	    return TransformersBattleResponseDTO.builder()
		    .losingTeamSurvivors(transfomers.stream().map(this::createLoserName).collect(Collectors.toList()))
		    .build();
	}

	return response;
    }

    private String createLoserName(TransformerEntity transfomers) {
	return transfomers.getName();

    }

    private List<TransformerEntity> sliptTeamByType(List<TransformerEntity> transformers, String type) {
	return transformers.stream().filter(transformer -> transformer.getTeam().equalsIgnoreCase(type))
		.collect(Collectors.toList());
    }

}
