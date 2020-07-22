package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	return null;
    }

    @Override
    public TransformerDTO updateTransformer(TransformerDTO transformer) {
	log.info("update Transformer");
	return null;
    }

    @Override
    public void deleteTransformer(Long id) {
	log.info("delet Transformer id: " + id);
	// TODO Auto-generated method stub

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

}
