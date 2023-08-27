package org.simulation.entities.creature;

import org.simulation.Coordinates;
import org.simulation.Map;

import java.util.List;
import java.util.Random;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(Coordinates coordinates, Map map) {
        super(coordinates, map);
        attackPower = setRandomAttackPower();
    }

    private int setRandomAttackPower() {
        Random random = new Random();
        return random.nextInt(90) + 10;
    }

    @Override
    public void makeMove() {
        List<Coordinates> path = pathSearch.findPath(this.getCoordinates(), map, Herbivore.class);
        System.out.println("Predator: " + path);
    }
}
