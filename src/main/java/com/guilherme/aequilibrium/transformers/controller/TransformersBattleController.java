package com.guilherme.aequilibrium.transformers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.aequilibrium.transformers.model.dto.BattleRequestDTO;
import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;
import com.guilherme.aequilibrium.transformers.service.TransformersBattleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/battle")
public class TransformersBattleController {

    @Autowired
    private TransformersBattleService transformersBattleService;

    @Operation(summary = "Starts Tranformers battle by Transformers id list")
    @PostMapping
    public ResponseEntity<TransformersBattleResponseDTO> transformersBattle(
	    @RequestBody(required = true) BattleRequestDTO battleRequestDTO) {
	TransformersBattleResponseDTO response = transformersBattleService.getTeamWinner(battleRequestDTO.getTransformersIds());
	return response != null ? new ResponseEntity<>(response, HttpStatus.OK)
		: new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
