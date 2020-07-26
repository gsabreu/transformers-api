package com.guilherme.aequilibrium.transformers.model.dto;

import lombok.Data;

@Data
public class ExceptionModelDTO {

    Integer code;
    String message;

    public ExceptionModelDTO(Integer code, String message) {
	super();
	this.code = code;
	this.message = message;
    }

}