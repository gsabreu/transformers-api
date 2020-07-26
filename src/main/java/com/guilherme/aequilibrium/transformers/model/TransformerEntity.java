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
    
    private static final String RANGE_MESSAGE = "must be between 1 and 10";
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message = "strength " + RANGE_MESSAGE)
    @Column(name = "strength")
    private Integer strength;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message = "intelligence " + RANGE_MESSAGE)
    @Column(name = "intelligence")
    private Integer intelligence;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "speed " + RANGE_MESSAGE)
    @Column(name = "speed")
    private Integer speed;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "endurance " + RANGE_MESSAGE)
    @Column(name = "endurance")
    private Integer endurance;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "rank " + RANGE_MESSAGE)
    @Column(name = "rank")
    private Integer rank;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "courage " + RANGE_MESSAGE)
    @Column(name = "courage")
    private Integer courage;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "firepower " + RANGE_MESSAGE)
    @Column(name = "firepower")
    private Integer firepower;

    @Range(min = MIN_RANGE, max = MAX_RANGE, message =  "skill " + RANGE_MESSAGE)
    @Column(name = "skill")
    private Integer skill;

}
