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

    private Integer numberOfBattles;
    private Integer autobotsWins;
    private Integer decepticonsWins;

    @Override
    public TransformersBattleResponseDTO applyRules(List<TransformerEntity> transformers) {
	List<TransformerEntity> autobots = this.sliptTeamByType(transformers, Team.AUTOBOTS);
	List<TransformerEntity> decepticons = this.sliptTeamByType(transformers, Team.DECEPTICONS);
	List<String> losingTeamSurvivors = new ArrayList<>();

	this.sortTeamByRank(autobots);
	this.sortTeamByRank(decepticons);

	numberOfBattles = 0;
	autobotsWins = 0;
	decepticonsWins = 0;

	if (autobots.size() > decepticons.size()) {
	    for (int i = 0; i < decepticons.size(); i++) {
		for (int j = 0; j < autobots.size(); j++) {
		    this.oneOnOneFight(autobots.get(i), decepticons.get(i));
		    i++;
		}
	    }
	}
	
	return TransformersBattleResponseDTO
		.builder()
		.numberOfBattles(numberOfBattles)
		.winnerTeam(this.getWinningTeam())
		.losingTeamSurvivors(losingTeamSurvivors.isEmpty() ? null: losingTeamSurvivors)
		.build();
    }

    private void oneOnOneFight(TransformerEntity autobot, TransformerEntity decepticon) {
	if (this.hasRunAwayOpponent(autobot, decepticon)) {
	    numberOfBattles++;
	    return;
	} else if (this.hasSkillWinner(autobot, decepticon)) {
	    numberOfBattles++;
	    return;
	} else if (this.hasOverallRateWinner(autobot, decepticon)) {
	    numberOfBattles++;
	    return;
	}

    }

    private Boolean hasRunAwayOpponent(TransformerEntity autobot, TransformerEntity decepticon) {
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

    private Boolean hasSkillWinner(TransformerEntity autobot, TransformerEntity decepticon) {
	if (autobot.getSkill() - decepticon.getSkill() >= 3) {
	    autobotsWins++;
	    return true;
	} else if (decepticon.getSkill() - autobot.getSkill() >= 3) {
	    decepticonsWins++;
	    return true;
	}
	return false;
    }

    private Boolean hasOverallRateWinner(TransformerEntity autobot, TransformerEntity decepticon) {
	Integer autoBotOverallRate = this.getOverallRating(autobot);
	Integer decepticonOverrallRate = this.getOverallRating(decepticon);

	if (autoBotOverallRate > decepticonOverrallRate) {
	    autobotsWins++;
	    return true;
	} else if (autoBotOverallRate < decepticonOverrallRate) {
	    decepticonsWins++;
	    return true;
	}
	autobotsWins++;
	decepticonsWins++;
	return true;
    }

    private String getWinningTeam() {
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
