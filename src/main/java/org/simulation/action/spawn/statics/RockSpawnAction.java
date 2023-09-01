package org.simulation.action.spawn.statics;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.spawn.SpawnAction;
import org.simulation.entity.statics.Rock;

public class RockSpawnAction extends SpawnAction<Rock> {
    public RockSpawnAction(Map map) {
        super(map);
        setRate(15);
    }

    @Override
    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates, map);
    }
}
