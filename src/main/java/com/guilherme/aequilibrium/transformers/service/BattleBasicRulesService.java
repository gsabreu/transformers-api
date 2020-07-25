
package com.guilherme.aequilibrium.transformers.service;

import java.util.List;

import com.guilherme.aequilibrium.transformers.model.TransformerEntity;

public interface BattleBasicRulesService {

    List<TransformerEntity> sliptTeamByType(List<TransformerEntity> transformers, String type);

    List<TransformerEntity> sortTeamByRank(List<TransformerEntity> transformers);

}
