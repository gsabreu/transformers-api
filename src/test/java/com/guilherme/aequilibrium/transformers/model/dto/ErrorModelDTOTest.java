package com.guilherme.aequilibrium.transformers.model.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.guilherme.aequilibrium.transformers.model.dto.ErrorModelDTO;

public class ErrorModelDTOTest {

    private ErrorModelDTO modelWithArgs;

    @Before
    public void setup() {
	modelWithArgs = new ErrorModelDTO(404, "Not found");

    }

    @Test
    public void should_return_code_when_getCode() {
	assertThat(modelWithArgs.getCode()).isEqualTo(404);
    }

    @Test
    public void should_return_message_when_getMessage() {
	assertThat(modelWithArgs.getMessage()).isEqualTo("Not found");
    }

    @Test
    public void should_set_code_when_setCode() {
	modelWithArgs.setCode(200);
	assertThat(modelWithArgs.getCode()).isEqualTo(200);
    }

    @Test
    public void should_set_message_when_setMessage() {
	modelWithArgs.setMessage("Ok");
	assertThat(modelWithArgs.getMessage()).isEqualTo("Ok");
    }

    @Test
    public void should_return_true_when_use_equals() {
	assertTrue(modelWithArgs.equals(modelWithArgs));
    }

    @Test
    public void should_return_not_null_when_use_hash() {
	assertNotNull(modelWithArgs.hashCode());
    }

    @Test
    public void should_return_string_when_use_toString() {
	assertNotNull(modelWithArgs.toString());
    }

}
