package com.guilherme.aequilibrium.transformers.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.guilherme.aequilibrium.transformers.exception.TeamNotFoundException;
import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TransformersController.class)
public class TransformersControllerTest {

    public static final String PATH = "/transformers";

    public static final String NAME = "Optimus Prime";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransformersService transformersService;

    @Before
    public void setup() {
	when(transformersService
		.createTransformer(TransformerDTO.builder().name(NAME).team(Team.AUTOBOTS.acronym).build()))
			.thenReturn(TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).build());

	when(transformersService.createTransformer(TransformerDTO.builder().team(Team.AUTOBOTS.acronym).build()))
		.thenReturn(TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).build());

	when(transformersService.createTransformer(TransformerDTO.builder().name(NAME).team("E").build()))
		.thenThrow(TeamNotFoundException.class);

	when(transformersService.updateTransformer(
		TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).courage(5).build())).thenReturn(
			TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).courage(5).build());

	when(transformersService.getTransformers())
		.thenReturn(Arrays.asList(TransformerDTO.builder().id(1L).name(NAME).build()));

	doThrow(EmptyResultDataAccessException.class).when(transformersService).deleteTransformer(2L);

    }

    @Test
    public void should_return_sucessfull_dto_when_createTransformer() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow
		.writeValueAsString(TransformerDTO.builder().name(NAME).team(Team.AUTOBOTS.acronym).build());

	this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is2xxSuccessful());

    }

    @Test
    public void should_return_bad_request_dto_when_createTransformer() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow.writeValueAsString(TransformerDTO.builder().team(Team.AUTOBOTS.acronym).build());

	this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is4xxClientError());

    }

    @Test
    public void should_return_not_found_dto_when_createTransformer() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow.writeValueAsString(TransformerDTO.builder().name(NAME).team("E").build());

	this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is4xxClientError());

    }

    @Test
    public void should_return_sucessfull_dto_when_updateTransformer() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow.writeValueAsString(
		TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).courage(5).build());

	this.mockMvc.perform(put(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is2xxSuccessful());

    }

    @Test
    public void should_return_sucessfull_dto_when_deleteTransformer() throws Exception {

	this.mockMvc.perform(delete(PATH + "/1").contentType(APPLICATION_JSON_UTF8)).andDo(print())
		.andExpect(status().is2xxSuccessful());

	verify(transformersService, times(1)).deleteTransformer((1L));

    }

    @Test
    public void should_return_method_not_allowed_dto_when_deleteTransformer() throws Exception {

	this.mockMvc.perform(delete(PATH).contentType(APPLICATION_JSON_UTF8)).andDo(print())
		.andExpect(status().is4xxClientError());
    }

    @Test
    public void should_return_not_found_dto_when_deleteTransformer() throws Exception {

	this.mockMvc.perform(delete(PATH + "/2").contentType(APPLICATION_JSON_UTF8)).andDo(print())
		.andExpect(status().is4xxClientError());

    }

    @Test
    public void should_return_sucessfull_dto_when_getTransformers() throws Exception {
	this.mockMvc.perform(get(PATH).contentType(APPLICATION_JSON_UTF8)).andDo(print())
		.andExpect(status().is2xxSuccessful());

    }

    @Test
    public void should_return_non_content_dto_when_getTransformers() throws Exception {

	when(transformersService.getTransformers()).thenReturn(Arrays.asList());
	this.mockMvc.perform(get(PATH).contentType(APPLICATION_JSON_UTF8)).andDo(print())
		.andExpect(status().is2xxSuccessful());

    }

}
