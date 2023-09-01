package org.simulation.entity.creature;

import org.simulation.Coordinates;
import org.simulation.Map;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(Coordinates coordinates, Map map) {
        super(coordinates, map);
        this.attackPower = 1;
        this.speed = 1;
    }

    @Override
    public void doSomething(Coordinates targetCoordinates) {
        Creature creature = map.getCreature(targetCoordinates);

        if (beat(creature)) {
            map.replaceEntity(this, targetCoordinates);
        }
    }

    private boolean beat(Creature creature) {
        if (creature.getHp() - this.attackPower <= 0) {
            creature.setHp(0);
            return true;
        }
        creature.setHp(creature.getHp() - this.attackPower);
        return false;
    }

}
