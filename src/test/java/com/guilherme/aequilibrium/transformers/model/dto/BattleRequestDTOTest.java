package com.guilherme.aequilibrium.transformers.model.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class BattleRequestDTOTest {

    private BattleRequestDTO modelWithArgs;
    private BattleRequestDTO modelWithoutArgs;
    private BattleRequestDTO modelWithBuilder;

    @Before
    public void setup() {
	modelWithArgs = new BattleRequestDTO(Arrays.asList(1L));
	modelWithoutArgs = new BattleRequestDTO();
	modelWithBuilder = BattleRequestDTO.builder().transformersIds(Arrays.asList(1L)).build();
    }

    @Test
    public void should_return_transformersIds_when_getTransformersIds() {
	assertThat(modelWithArgs.getTransformersIds()).isNotEmpty();
    }

    @Test
    public void should_set_transformersIds_when_getTransformersIds() {
	modelWithArgs.setTransformersIds(Arrays.asList(1L, 2L));
	assertThat(modelWithArgs.getTransformersIds()).size().isEqualTo(2);
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
