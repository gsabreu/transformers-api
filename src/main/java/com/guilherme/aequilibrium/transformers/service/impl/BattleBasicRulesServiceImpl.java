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

	this.sortTeamByRank(autobots);
	this.sortTeamByRank(decepticons);

	List<TransformerEntity> nonFightList = this.removeFighters(autobots, decepticons);

	numberOfBattles = 0;
	autobotsWins = 0;
	decepticonsWins = 0;

	for (int i = 0; i < decepticons.size(); i++) {
	    for (int j = 0; j < autobots.size(); j++) {
		this.oneOnOneFight(autobots.get(i), decepticons.get(i));
		i++;
	    }
	}

	String winnerTeam = this.getWinningTeam();

	return TransformersBattleResponseDTO.builder().numberOfBattles(numberOfBattles).winnerTeam(winnerTeam)
		.losingTeamSurvivors(
			nonFightList.isEmpty() ? null : this.getLosingTeamSurvivors(nonFightList, winnerTeam))
		.build();
    }

    private void oneOnOneFight(TransformerEntity autobot, TransformerEntity decepticon) {
	if (this.hasRunAwayOpponent(autobot, decepticon)) {
	    numberOfBattles++;
	} else if (this.hasSkillWinner(autobot, decepticon)) {
	    numberOfBattles++;
	} else {
	    this.getOverallRateWinner(autobot, decepticon);
	    numberOfBattles++;
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

    private void getOverallRateWinner(TransformerEntity autobot, TransformerEntity decepticon) {
	Integer autoBotOverallRate = this.getOverallRating(autobot);
	Integer decepticonOverrallRate = this.getOverallRating(decepticon);

	if (autoBotOverallRate > decepticonOverrallRate) {
	    autobotsWins++;
	} else if (autoBotOverallRate < decepticonOverrallRate) {
	    decepticonsWins++;
	} else {
	    autobotsWins++;
	    decepticonsWins++;
	}

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
	transformers.sort(Comparator.comparingInt(this::getTransformerRank).reversed());
    }

    private Integer getTransformerRank(TransformerEntity transfomer) {
	return transfomer.getRank();
    }

    private Integer getOverallRating(TransformerEntity transformer) {
	return transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed()
		+ transformer.getEndurance() + transformer.getFirepower();
    }

    private List<TransformerEntity> removeFighters(List<TransformerEntity> autobots,
	    List<TransformerEntity> decepticons) {
	List<TransformerEntity> nonFightList = new ArrayList<>();

	if (autobots.size() > decepticons.size()) {
	    nonFightList.add(autobots.remove(autobots.size() - 1));
	} else if (autobots.size() < decepticons.size()) {
	    nonFightList.add(decepticons.remove(decepticons.size() - 1));
	}
	return nonFightList;
    }

    private List<String> getLosingTeamSurvivors(List<TransformerEntity> nonFightList, String winnerTeam) {
	List<String> losingTeamSurvivors = new ArrayList<>();

	if (!winnerTeam.equals("Tie")) {
	    nonFightList.stream()
		    .filter(transformer -> Team.getByAcronym(transformer.getTeam()) != (Team.getByName(winnerTeam)))
		    .forEach(transformer -> losingTeamSurvivors.add(transformer.getName()));
	}

	return losingTeamSurvivors;

    }

}
