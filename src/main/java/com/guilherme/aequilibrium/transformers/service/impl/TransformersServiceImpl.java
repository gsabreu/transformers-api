package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.aequilibrium.transformers.model.Team;
import com.guilherme.aequilibrium.transformers.model.TransformerEntity;
import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;
import com.guilherme.aequilibrium.transformers.repository.TransformersRepository;
import com.guilherme.aequilibrium.transformers.service.TransformersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransformersServiceImpl implements TransformersService {

    @Autowired
    private TransformersRepository transformersRepository;

    @Override
    public TransformerDTO createTransformer(TransformerDTO transformer) {
	log.info("creating Transformer");
	this.validateTeam(transformer.getTeam());
	return this.convertTransformerDto(transformersRepository.save(this.convertEntity(transformer)));
    }

    @Override
    public TransformerDTO updateTransformer(TransformerDTO transformer) {
	log.info("update Transformer");
	return this.convertTransformerDto(transformersRepository.save(this.convertEntity(transformer)));
    }

    @Override
    public void deleteTransformer(Long id) {
	log.info("delete Transformer id: " + id);
	transformersRepository.deleteById(id);
    }

    @Override
    public List<TransformerDTO> getTransformers() {
	log.info("Listing Transformers");

	List<TransformerEntity> entity = transformersRepository.findAll();
	return entity.stream().map(this::convertTransformerDto).collect(Collectors.toList());
    }

    private TransformerDTO convertTransformerDto(TransformerEntity entity) {
	return new ObjectMapper().convertValue(entity, TransformerDTO.class);
    }

    private TransformerEntity convertEntity(TransformerDTO dto) {
	return new ObjectMapper().convertValue(dto, TransformerEntity.class);
    }

    private void validateTeam(String team) {
	Team.getByAcronym(team);
    }

}
