package com.guilherme.aequilibrium.transformers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.aequilibrium.transformers.model.dto.TransformerDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersService;

@RestController
@RequestMapping(path = "/v1/transformers")
public class TransformersController {

    @Autowired
    private TransformersService transformersService;

    @PostMapping
    public TransformerDTO createTransformer(@RequestBody(required = true) TransformerDTO transformerDto) {

	return transformersService.createTransformer(transformerDto);
    }

    @PutMapping
    public void updateTransformer() {

    }

    @DeleteMapping
    public void deleteTransformer() {

    }

    @GetMapping
    public List<TransformerDTO> getTransformers() {

	return transformersService.getTransformers();
    }

}
