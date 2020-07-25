package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.service.BattleBasicRulesService;

@Service
public class BattleBasicRulesServiceImpl implements BattleBasicRulesService {

    @Override
    public TransformersBattleResponseDTO applyRules(List<TransformerEntity> transformers) {
	List<TransformerEntity> autobots = this.sliptTeamByType(transformers, Team.AUTOBOTS);
	List<TransformerEntity> decepticons = this.sliptTeamByType(transformers,Team.DECEPTICONS);
	List<String> losingTeamSurvivors = new ArrayList<>();

	this.sortTeamByRank(autobots);
	this.sortTeamByRank(decepticons);

	Integer numberOfBattles = 0;
	Integer autobotsWins = 0;
	Integer decepticonsWins = 0;
	int i = 0;

	if (autobots.size() > decepticons.size()) {
	    while (i <= autobots.size()) {

	    }
	}
	return TransformersBattleResponseDTO
		.builder()
		.numberOfBattles(numberOfBattles)
		.winnerTeam(this.getWinningTeam(autobotsWins, decepticonsWins))
		.losingTeamSurvivors(losingTeamSurvivors)
		.build();
    }

    private void startBattle(TransformerEntity autobot, TransformerEntity decepticon, Integer numberOfBattles,
	    Integer autobotsWins, Integer decepticonsWins) {
	if (this.runAwayOpponent(autobot, decepticon, autobotsWins, decepticonsWins)) {
	    numberOfBattles++;
	    return;
	} else if (this.winnerSkill(autobot, decepticon, autobotsWins, decepticonsWins)) {
	    numberOfBattles++;
	    return;
	} else if (this.overallRateWinner(autobot, decepticon, autobotsWins, decepticonsWins)) {
	    numberOfBattles++;
	    return;
	}

    }

    private Boolean runAwayOpponent(TransformerEntity autobot, TransformerEntity decepticon, Integer autobotsWins,
	    Integer decepticonsWins) {
	if (autobot.getCourage() - decepticon.getCourage() >= 4
		|| autobot.getStrength() - decepticon.getStrength() >= 3) {
	    autobotsWins++;
	    return true;
	} else if (decepticon.getCourage() - autobot.getCourage() >= 4
		|| decepticon.getStrength() - autobot.getStrength() >= 3) {
	    decepticonsWins++;
	    return true;
	}
	return false;
    }

    private Boolean winnerSkill(TransformerEntity autobot, TransformerEntity decepticon, Integer autobotsWins,
	    Integer decepticonsWins) {
	if (autobot.getSkill() - decepticon.getSkill() >= 3) {
	    autobotsWins++;
	    return true;
	} else if (decepticon.getSkill() - autobot.getSkill() >= 3) {
	    decepticonsWins++;
	    return true;
	}
	return false;
    }

    private Boolean overallRateWinner(TransformerEntity autobot, TransformerEntity decepticon, Integer autobotsWins,
	    Integer decepticonsWins) {
	Integer autoBotOverallRate = this.getOverallRating(autobot);
	Integer decepticonOverrallRate = this.getOverallRating(decepticon);

	if (autoBotOverallRate > decepticonOverrallRate) {
	    autobotsWins++;
	    return true;
	} else if (autoBotOverallRate < decepticonOverrallRate) {
	    decepticonsWins++;
	    return true;
	}
	return false;
    }

    private String getWinningTeam(Integer autobotsWins, Integer decepticonsWins) {
	if (autobotsWins > decepticonsWins) {
	    return Team.AUTOBOTS.name;
	} else if (autobotsWins < decepticonsWins) {
	    return Team.DECEPTICONS.name;
	}
	return "Tie";
    }

    private List<TransformerEntity> sliptTeamByType(List<TransformerEntity> transformers, Team team) {
	return transformers.stream().filter(transformer -> transformer.getTeam().equalsIgnoreCase(team.acronym))
		.collect(Collectors.toList());
    }

    private void sortTeamByRank(List<TransformerEntity> transformers) {
	transformers.sort(Comparator.comparingInt(this::getTransformerRank));
    }

    private Integer getTransformerRank(TransformerEntity transfomer) {
	return transfomer.getRank();
    }

    private Integer getOverallRating(TransformerEntity transformer) {
	return transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed()
		+ transformer.getEndurance() + transformer.getFirepower();
    }

}
