package com.guilherme.aequilibrium.transformers.service;

import java.util.List;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

public interface BattleSpecialRulesService {

    TransformersBattleResponseDTO applySpecialRules(List<TransformerEntity> transfomers);

}
