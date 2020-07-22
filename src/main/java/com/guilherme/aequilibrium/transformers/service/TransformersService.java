package com.guilherme.aequilibrium.transformers.service;

import java.util.List;

import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;

public interface TransformersService {

    TransformerDTO createTransformer(TransformerDTO transformer);

    TransformerDTO updateTransformer(TransformerDTO transformer);

    void deleteTransformer(Long id);

    List<TransformerDTO> getTransformers();

}
