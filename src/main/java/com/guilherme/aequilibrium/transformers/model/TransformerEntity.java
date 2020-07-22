package com.guilherme.aequilibrium.transformers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "endurance")
    private Integer endurance;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "courage")
    private Integer courage;

    @Column(name = "firepower")
    private Integer firepower;

    @Column(name = "skill")
    private Integer skill;

}
