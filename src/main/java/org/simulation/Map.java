package org.simulation;

import org.simulation.entities.Entity;

import java.util.HashMap;

public class Map {
    private HashMap<Coordinates, Entity> entities = new HashMap<>();

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    public void setEntities(HashMap<Coordinates, Entity> entities) {
        this.entities = entities;
    }
}
