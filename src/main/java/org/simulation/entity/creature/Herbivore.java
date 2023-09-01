package org.simulation.entity.creature;

import org.simulation.Coordinates;
import org.simulation.Map;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, Map map) {
        super(coordinates, map);
        this.speed = 1;
    }


    @Override
    public void doSomething(Coordinates targetCoordinates) {
        map.replaceEntity(this, targetCoordinates);
    }
}
