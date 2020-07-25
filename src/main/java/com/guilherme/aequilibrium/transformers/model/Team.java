package com.guilherme.aequilibrium.transformers.model;

import com.guilherme.aequilibrium.transformers.handler.TeamNotFoundException;

public enum Team {

    AUTOBOTS("A", "Autobots"),
    DECEPTICONS("D", "Decepticons");// 15

    public final String acronym;
    public final String name;

    private Team(String acronym, String name) {
	this.acronym = acronym;
	this.name = name;
    }

    public static Team getValue(String acronym) {
	if (acronym.equalsIgnoreCase(AUTOBOTS.acronym)) {
	    return AUTOBOTS;
	} else if (acronym.equalsIgnoreCase(DECEPTICONS.acronym)) {
	    return DECEPTICONS;
	}

	throw new TeamNotFoundException(acronym);
    }

}
