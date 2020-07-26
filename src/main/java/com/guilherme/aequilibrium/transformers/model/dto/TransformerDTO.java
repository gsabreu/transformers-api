package com.guilherme.aequilibrium.transformers.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.PropertySource;

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
public class TransformerDTO implements Serializable {

    private static final long serialVersionUID = -273715332577306549L;

    private static final int MIN_RANGE = 1;

    private static final int MAX_RANGE = 10;

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String team;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer strength;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer intelligence;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer speed;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer endurance;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer rank;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer courage;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer firepower;

    @Min(value = MIN_RANGE)
    @Max(value = MAX_RANGE)
    private Integer skill;

}
