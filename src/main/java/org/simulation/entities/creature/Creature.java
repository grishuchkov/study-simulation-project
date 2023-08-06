package org.simulation.entities.creature;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.Entity;

public abstract class Creature extends Entity {

    protected int speed = 1;
    protected int hp = 100;

    public Creature(Coordinates coordinates, Map map) {
        super(coordinates, map);
    }

    abstract void makeMove();

    public int getHp() {
        return hp;
    }
}
