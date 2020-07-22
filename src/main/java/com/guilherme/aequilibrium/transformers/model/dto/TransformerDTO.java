package com.guilherme.aequilibrium.transformers.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransformerDTO implements Serializable {

    private static final long serialVersionUID = -273715332577306549L;

    private Long id;
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
