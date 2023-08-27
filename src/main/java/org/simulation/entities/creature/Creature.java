package org.simulation.entities.creature;

import org.simulation.AStarPathSearch;
import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.Entity;

public abstract class Creature extends Entity {


    public Creature(Coordinates coordinates, Map map) {
        super(coordinates, map);
    }

    protected final AStarPathSearch pathSearch = new AStarPathSearch();

    public abstract void makeMove();
}
