package com.guilherme.aequilibrium.transformers.exception;

public class TeamNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -838817905134500286L;

    public TeamNotFoundException(String team) {
	super("Team: " + team + " not found");
    }

}
