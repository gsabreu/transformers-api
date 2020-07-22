package com.guilherme.aequilibrium.transformers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transfomers")
@Table(name = "transfomers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransformerEntity implements Serializable {

    private static final long serialVersionUID = 7974071224056657231L;

    private static final int MIN_RANGE = 1;

    private static final int MAX_RANGE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "strength")
    private Integer strength;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "intelligence")
    private Integer intelligence;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "speed")
    private Integer speed;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "endurance")
    private Integer endurance;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "rank")
    private Integer rank;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "courage")
    private Integer courage;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "firepower")
    private Integer firepower;

    @Range(min = MIN_RANGE, max = MAX_RANGE)
    @Column(name = "skill")
    private Integer skill;

    public Integer getOverallRating() {
	return this.strength + this.intelligence + this.speed + this.endurance + this.rank + this.courage
		+ this.firepower + this.skill;
    }

}
