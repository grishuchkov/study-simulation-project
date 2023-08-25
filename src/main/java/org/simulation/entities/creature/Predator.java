package org.simulation.entities.creature;

import org.simulation.Coordinates;
import org.simulation.Map;

import java.util.Random;

public class Predator extends Creature{
    private int attackPower;

    public Predator(Coordinates coordinates, Map map) {
        super(coordinates, map);
        attackPower = randomSetAttackPower();
    }

    private int randomSetAttackPower(){
        Random random = new Random();
        return random.nextInt(90) + 10;
    }

    @Override
    public void makeMove() {

    }
}
