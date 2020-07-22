package com.guilherme.aequilibrium.transformers.handler;

public class TransformersNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3827079341764485186L;

    public TransformersNotFoundException(String message) {
	super(message);
    }

    public TransformersNotFoundException(Long id) {
	super("Transformer id: " + id + " not found");
    }

}
