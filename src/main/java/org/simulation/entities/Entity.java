package org.simulation.entities;

import org.simulation.Coordinates;

public abstract class Entity {
    private Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
