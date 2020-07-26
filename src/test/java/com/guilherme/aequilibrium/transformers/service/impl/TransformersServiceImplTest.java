package com.guilherme.aequilibrium.transformers.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransformersServiceImplTest {

    private final static String NAME = "Optimus Prime";

    @InjectMocks
    private TransformersServiceImpl service;

    @Mock
    private TransformersRepository transformersRepository;

    @Before
    public void setup() {

	TransformerEntity transformerIdOne = TransformerEntity.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym)
		.build();

	when(transformersRepository.save(TransformerEntity.builder().name(NAME).team(Team.AUTOBOTS.acronym).build()))
		.thenReturn(TransformerEntity.builder().id(1L).name(NAME).build());

	when(transformersRepository.save(transformerIdOne)).thenReturn(transformerIdOne);

	when(transformersRepository.findAll()).thenReturn(Arrays.asList(transformerIdOne));
    }

    @Test
    public void should_return_new_transfomers_when_createTransformer() {
	TransformerDTO result = service
		.createTransformer(TransformerDTO.builder().name(NAME).team(Team.AUTOBOTS.acronym).build());

	assertNotNull(result);
	assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    public void should_return_updated_transfomers_when_updateTransformer() {
	TransformerDTO result = service
		.updateTransformer(TransformerDTO.builder().id(1L).name(NAME).team(Team.AUTOBOTS.acronym).build());

	assertNotNull(result);
	assertThat(result.getTeam()).isEqualTo(Team.AUTOBOTS.acronym);
    }

    @Test
    public void should_delete_when_deleteTransformer() {
	service.deleteTransformer(1L);

	verify(transformersRepository, times(1)).deleteById(1L);
    }

    @Test
    public void should_return_list_when_getTransformers() {
	List<TransformerDTO> result = service.getTransformers();
	assertThat(result).isNotEmpty();

    }

}
