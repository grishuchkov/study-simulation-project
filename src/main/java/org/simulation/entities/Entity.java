package org.simulation.entities;

import org.simulation.Coordinates;
import org.simulation.Map;

public abstract class Entity {
    private Coordinates coordinates;
    protected Map map;

    public Entity(Coordinates coordinates, Map map) {
        this.coordinates = coordinates;
        this.map = map;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
}
