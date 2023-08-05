package org.simulation;

import org.simulation.entities.Entity;

import java.util.HashMap;

public class Map {
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private HashMap<Coordinates, Entity> entities = new HashMap<>();



    public int getMaxCoordinate(Axes axis){
        if (axis.equals(Axes.X)){
            return MAX_X;
        }else {
            return MAX_Y;
        }
    }
}
