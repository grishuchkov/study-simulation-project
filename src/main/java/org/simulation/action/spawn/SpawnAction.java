package org.simulation.action.spawn;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.Action;
import org.simulation.entities.Entity;

public abstract class SpawnAction<T extends Entity> extends Action {
    protected int rate;

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

    protected void setRate(int requiredNumberEntities){
        int size = map.getSize();
        if(size > 10 && requiredNumberEntities <= size){
            rate = requiredNumberEntities;
        }else {
            rate = 1;
        }
    }
    protected abstract T spawnEntity(Coordinates coordinates);
}
