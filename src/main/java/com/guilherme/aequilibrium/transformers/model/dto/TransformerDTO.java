package com.guilherme.aequilibrium.transformers.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@JsonInclude(Include.NON_EMPTY)
public class TransformerDTO implements Serializable {

    private static final long serialVersionUID = -273715332577306549L;

    private static final int MIN_RANGE = 1;

    private static final int MAX_RANGE = 10;

    private Long id;

    @NotBlank(message = "{notblank.transformer.name}")
    private String name;

    @NotBlank(message = "{notblank.transformer.team}")
    private String team;

    @Min(value = MIN_RANGE, message = "{strength.range}")
    @Max(value = MAX_RANGE, message = "{strength.range}")
    private Integer strength;

    @Min(value = MIN_RANGE, message = "{intelligence.range}")
    @Max(value = MAX_RANGE, message = "{intelligence.range}")
    private Integer intelligence;

    @Min(value = MIN_RANGE, message = "{speed.range}")
    @Max(value = MAX_RANGE, message = "{speed.range}")
    private Integer speed;

    @Min(value = MIN_RANGE, message = "{endurance.range}")
    @Max(value = MAX_RANGE, message = "{endurance.range}")
    private Integer endurance;

    @Min(value = MIN_RANGE, message = "{rank.range}")
    @Max(value = MAX_RANGE, message = "{rank.range}")
    private Integer rank;

    @Min(value = MIN_RANGE, message = "{courage.range}")
    @Max(value = MAX_RANGE, message = "{courage.range}")
    private Integer courage;

    @Min(value = MIN_RANGE, message = "{firepower.range}")
    @Max(value = MAX_RANGE, message = "{firepower.range}")
    private Integer firepower;

    @Min(value = MIN_RANGE, message = "{skill.range}")
    @Max(value = MAX_RANGE, message = "{skill.range}")
    private Integer skill;

}
