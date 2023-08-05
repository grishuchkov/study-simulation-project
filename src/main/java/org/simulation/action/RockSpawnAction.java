package org.simulation.action;

import org.simulation.Axes;
import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.statics.Rock;

public class RockSpawnAction extends SpawnAction<Rock>{
    public RockSpawnAction(Map map) {
        super(map);
        int size = map.getSize();
        if(size > 90){
            rate = size / 15.0;
        }else {
            rate = 3;
        }
    }

    @Override
    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}
