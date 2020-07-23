package com.guilherme.aequilibrium.transformers.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@JsonInclude(Include.NON_EMPTY)
public class TransformersBattleResponseDTO {

    private Integer numberOfBattles;
    private String winnerTeam;
    private List<String> losingTeamSurvivors;

}
