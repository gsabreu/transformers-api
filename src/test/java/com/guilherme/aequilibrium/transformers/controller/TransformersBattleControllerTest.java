package com.guilherme.aequilibrium.transformers.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TransformersBattleController.class)
public class TransformersBattleControllerTest {

    public static final String PATH = "/battle";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransformersBattleService transformersBattleService;

    @Before
    public void setup() {
	when(transformersBattleService.getTeamWinner(Arrays.asList(1L, 2L))).thenReturn(null);
	when(transformersBattleService.getTeamWinner(Arrays.asList(3L, 4L))).thenReturn(TransformersBattleResponseDTO.builder().numberOfBattles(1).winnerTeam(Team.AUTOBOTS.acronym).build());
    }

    @Test
    public void should_return_non_content_if_ids_dont_exist_when_transformersBattle() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow.writeValueAsString(Arrays.asList(1L, 2L));

	this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void should_return_sucess_when_transformersBattle() throws Exception {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	String requestJson = ow.writeValueAsString(Arrays.asList(3L, 4L));

	this.mockMvc.perform(post(PATH).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andDo(print())
		.andExpect(status().is2xxSuccessful());
    }

}
