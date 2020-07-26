package com.guilherme.aequilibrium.transformers.model;

import com.guilherme.aequilibrium.transformers.exception.TeamNotFoundException;

public enum Team {

    AUTOBOTS("A", "Autobots"),
    DECEPTICONS("D", "Decepticons");

    public final String acronym;
    public final String name;

    private Team(String acronym, String name) {
	this.acronym = acronym;
	this.name = name;
    }

    public static Team getByAcronym(String acronym) {
	if (acronym.equalsIgnoreCase(AUTOBOTS.acronym)) {
	    return AUTOBOTS;
	} else if (acronym.equalsIgnoreCase(DECEPTICONS.acronym)) {
	    return DECEPTICONS;
	}

	throw new TeamNotFoundException(acronym);
    }
    
    public static Team getByName(String name) {
	if (name.equalsIgnoreCase(AUTOBOTS.name)) {
	    return AUTOBOTS;
	} else if (name.equalsIgnoreCase(DECEPTICONS.name)) {
	    return DECEPTICONS;
	}

	throw new TeamNotFoundException(name);
    }

}
