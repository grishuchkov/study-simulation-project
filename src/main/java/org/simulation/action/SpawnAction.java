package org.simulation.action;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.Entity;

public abstract class SpawnAction<T extends Entity> extends Action{
    protected double rate;

    public SpawnAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        int currentRate = 0;
        while(currentRate < rate){
            Coordinates coordinates = map.getEmptyRandomCoordinates();
            map.setEntity(spawnEntity(coordinates), coordinates);
            currentRate++;
        }
    }

    protected abstract T spawnEntity(Coordinates coordinates);
}
