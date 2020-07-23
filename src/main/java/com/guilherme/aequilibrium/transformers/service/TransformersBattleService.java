package com.guilherme.aequilibrium.transformers.service;

import java.util.List;

import com.guilherme.aequilibrium.transformers.model.dto.TransformersBattleResponseDTO;

public interface TransformersBattleService {

    TransformersBattleResponseDTO getTransfomersWinnerFromTheBattle(List<Long> transformersIds);

}
