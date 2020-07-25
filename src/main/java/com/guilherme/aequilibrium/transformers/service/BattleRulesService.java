package com.guilherme.aequilibrium.transformers.service;

import java.util.List;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

@FunctionalInterface
public interface BattleRulesService {

    public TransformersBattleResponseDTO applyRules(List<TransformerEntity> transformers);

}
