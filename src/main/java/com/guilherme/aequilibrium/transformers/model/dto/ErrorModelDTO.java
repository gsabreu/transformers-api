package com.guilherme.aequilibrium.transformers.model.dto;

import lombok.Data;

@Data
public class ErrorModelDTO {

    Integer code;
    String message;

    public ErrorModelDTO(Integer code, String message) {
	super();
	this.code = code;
	this.message = message;
    }

}