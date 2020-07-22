package com.guilherme.aequilibrium.transformers.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransformerDTO {

    private String name;
    private String team;
    private Integer strength;
    private Integer intelligence;
    private Integer speed;
    private Integer endurance;
    private Integer rank;
    private Integer courage;
    private Integer firepower;
    private Integer skill;

}
