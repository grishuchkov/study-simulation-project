package org.simulation.entities.creature;

import org.simulation.Coordinates;
import org.simulation.entities.Entity;

public abstract class Creature extends Entity {

    private int speed;
    private int hp;

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    abstract void makeMove();

}
