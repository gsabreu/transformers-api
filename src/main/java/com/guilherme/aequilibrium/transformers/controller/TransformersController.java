package com.guilherme.aequilibrium.transformers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteTransformer(@PathVariable Long id) {
	try {
	    transformersService.deleteTransformer(id);
	} catch (IllegalArgumentException e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	} catch (EmptyResultDataAccessException e) {
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @GetMapping
    public List<TransformerDTO> getTransformers() {

	return transformersService.getTransformers();
    }

}
