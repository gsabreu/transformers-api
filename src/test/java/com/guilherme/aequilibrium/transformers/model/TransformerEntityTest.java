package com.guilherme.aequilibrium.transformers.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TransformerEntityTest {

    private final static String NAME = "OPTIMIUS_PRIME";
    private final static String TEAM = Team.AUTOBOTS.acronym;

    private TransformerEntity modelWithArgs;
    private TransformerEntity modelWithoutArgs;
    private TransformerEntity modelWithBuilder;

    @Before
    public void setup() {
	modelWithArgs = new TransformerEntity(1L, NAME, TEAM, 1, 1, 1, 1, 1, 1, 1, 1);
	modelWithoutArgs = new TransformerEntity();
	modelWithBuilder = TransformerEntity
		.builder()
		.id(1L)
		.name(NAME)
		.team(TEAM)
		.strength(1)
		.intelligence(1)
		.speed(1)
		.endurance(1)
		.rank(1)
		.courage(1)
		.firepower(1)
		.skill(1)
		.build();
    }

    @Test
    public void should_return_id_when_getId() {
	assertThat(modelWithArgs.getId()).isEqualTo(1l);
    }

    @Test
    public void should_set_id_when_getId() {
	modelWithArgs.setId(2L);
	assertThat(modelWithArgs.getId()).isEqualTo(2L);
    }

    @Test
    public void should_return_name_when_getName() {
	assertThat(modelWithArgs.getName()).isEqualTo(NAME);
    }

    @Test
    public void should_set_name_when_setName() {
	modelWithArgs.setName(NAME + 1);
	assertThat(modelWithArgs.getName()).isEqualTo(NAME + 1);
    }

    @Test
    public void should_return_team_when_getTeam() {
	assertThat(modelWithArgs.getTeam()).isEqualTo(TEAM);
    }

    @Test
    public void should_set_team_when_setTeam() {
	modelWithArgs.setTeam(TEAM + 1);
	assertThat(modelWithArgs.getTeam()).isEqualTo(TEAM + 1);
    }

    @Test
    public void should_return_strength_when_getStrength() {
	assertThat(modelWithArgs.getStrength()).isEqualTo(1);
    }

    @Test
    public void should_set_strength_when_setStrength() {
	modelWithArgs.setStrength(2);
	assertThat(modelWithArgs.getStrength()).isEqualTo(2);
    }

    @Test
    public void should_return_intelligence_when_getIntelligence() {
	assertThat(modelWithArgs.getIntelligence()).isEqualTo(1);
    }

    @Test
    public void should_set_intelligence_when_setIntelligence() {
	modelWithArgs.setIntelligence(2);
	assertThat(modelWithArgs.getIntelligence()).isEqualTo(2);
    }

    @Test
    public void should_return_speed_when_getSpeed() {
	assertThat(modelWithArgs.getSpeed()).isEqualTo(1);
    }

    @Test
    public void should_set_speed_when_setSpeed() {
	modelWithArgs.setSpeed(2);
	assertThat(modelWithArgs.getSpeed()).isEqualTo(2);
    }

    @Test
    public void should_return_endurance_when_getEndurance() {
	assertThat(modelWithArgs.getEndurance()).isEqualTo(1);
    }

    @Test
    public void should_set_endurance_when_setEndurance() {
	modelWithArgs.setEndurance(2);
	assertThat(modelWithArgs.getEndurance()).isEqualTo(2);
    }

    @Test
    public void should_return_rank_when_getRank() {
	assertThat(modelWithArgs.getRank()).isEqualTo(1);
    }

    @Test
    public void should_set_rank_when_setRank() {
	modelWithArgs.setRank(2);
	assertThat(modelWithArgs.getRank()).isEqualTo(2);
    }

    @Test
    public void should_return_courage_when_getCourage() {
	assertThat(modelWithArgs.getCourage()).isEqualTo(1);
    }

    @Test
    public void should_set_courage_when_setCourage() {
	modelWithArgs.setCourage(2);
	assertThat(modelWithArgs.getCourage()).isEqualTo(2);
    }

    @Test
    public void should_return_firepower_when_getFirepower() {
	assertThat(modelWithArgs.getFirepower()).isEqualTo(1);
    }

    @Test
    public void should_set_firepower_when_setFirepower() {
	modelWithArgs.setFirepower(2);
	assertThat(modelWithArgs.getFirepower()).isEqualTo(2);
    }

    @Test
    public void should_return_skill_when_getSkill() {
	assertThat(modelWithArgs.getSkill()).isEqualTo(1);
    }

    @Test
    public void should_set_skill_when_setSkill() {
	modelWithArgs.setSkill(2);
	assertThat(modelWithArgs.getSkill()).isEqualTo(2);
    }

    @Test
    public void should_return_true_when_use_equals() {
	assertTrue(modelWithArgs.equals(modelWithArgs));
    }

    @Test
    public void should_return_string_when_use_toString() {
	assertNotNull(modelWithArgs.toString());
    }

    @Test
    public void should_return_NotNull_when_validateBuilder() {
	assertNotNull(modelWithBuilder);
    }

    @Test
    public void should_return_not_null_when_use_hash_for_no_args_constructor() {
	assertNotNull(modelWithoutArgs.hashCode());
    }

}
