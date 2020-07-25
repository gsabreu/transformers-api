package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.service.BattleBasicRulesService;

@Service
public class BattleBasicRulesServiceImpl implements BattleBasicRulesService {

    @Override
    public List<TransformerEntity> sliptTeamByType(List<TransformerEntity> transformers, String type) {
	return transformers.stream().filter(transformer -> transformer.getTeam().equalsIgnoreCase(type))
		.collect(Collectors.toList());
    }

    @Override
    public List<TransformerEntity> sortTeamByRank(List<TransformerEntity> transformers) {
	// TODO Auto-generated method stub
	return null;
    }

}
