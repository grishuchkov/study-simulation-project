package org.simulation;

import org.simulation.entities.Entity;

import java.util.HashMap;

public class Map {
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private HashMap<Coordinates, Entity> entityStorage = new HashMap<>();



    public int getMaxCoordinate(Axes axis){
        if (axis.equals(Axes.X)){
            return MAX_X;
        }else {
            return MAX_Y;
        }
    }

    public void setEntity(Entity entity, Coordinates coordinates){
        entity.setCoordinates(coordinates);
        entityStorage.put(coordinates, entity);
    }

    public boolean isEmpty(Coordinates coordinates){
        return !entityStorage.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates){
        return entityStorage.get(coordinates);
    }
}
