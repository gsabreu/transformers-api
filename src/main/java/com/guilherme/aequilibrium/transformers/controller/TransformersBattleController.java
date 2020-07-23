package com.guilherme.aequilibrium.transformers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

@RestController
@RequestMapping(path = "/battle")
public class TransformersBattleController {

    @PostMapping
    public ResponseEntity<TransformersBattleResponseDTO> transformersBattle(
	    @RequestBody(required = true) List<Long> transformersIds) {
	return null;
    }

}
