package org.simulation.entities.creature;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.Grass;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, Map map) {
        super(coordinates, map);
    }


    @Override
    public void makeMove() {

        List<Coordinates> path = pathSearch.findPath(this.getCoordinates(), map, Grass.class);
        System.out.println("Herbivore: " + path);
    }
}
