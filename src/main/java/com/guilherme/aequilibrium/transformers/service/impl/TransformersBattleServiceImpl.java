package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;
import com.guilherme.aequilibrium.transformers.service.BattleBasicRulesService;
import com.guilherme.aequilibrium.transformers.service.BattleSpecialRulesService;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

@Service
public class TransformersBattleServiceImpl implements TransformersBattleService {

    @Autowired
    private TransformersRepository transformersRepository;

    @Autowired
    private BattleSpecialRulesService battleSpecialRulesService;

    @Autowired
    private BattleBasicRulesService battleBasicRulesService;

    @Override
    public TransformersBattleResponseDTO getTeamWinner(List<Long> transformersIds) {
	List<TransformerEntity> transformers = transformersRepository.findAllById(transformersIds);
	TransformersBattleResponseDTO transformersBattleResponseDTO = null;

	if (!transformers.isEmpty()) {
	    transformersBattleResponseDTO = battleSpecialRulesService.applyRules(transformers);

	    if (transformersBattleResponseDTO == null) {
		transformersBattleResponseDTO = battleBasicRulesService.applyRules(transformers);
	    }
	}

	return transformersBattleResponseDTO;
    }

}
