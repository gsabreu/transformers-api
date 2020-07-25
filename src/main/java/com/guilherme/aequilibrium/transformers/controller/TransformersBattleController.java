package com.guilherme.aequilibrium.transformers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

@RestController
@RequestMapping(path = "/battle")
public class TransformersBattleController {

    @Autowired
    private TransformersBattleService transformersBattleService;

    @PostMapping
    public ResponseEntity<TransformersBattleResponseDTO> transformersBattle(
	    @RequestBody(required = true) List<Long> transformersIds) {
	TransformersBattleResponseDTO response = transformersBattleService.getTeamWinner(transformersIds);
	return response != null ? new ResponseEntity<>(response, HttpStatus.OK)
		: new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
