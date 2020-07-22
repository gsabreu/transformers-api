package com.guilherme.aequilibrium.transformers.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransformersServiceImpl implements TransformersService {

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
	return null;
    }

}
