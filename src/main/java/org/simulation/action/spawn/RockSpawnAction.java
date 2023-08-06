package org.simulation.action.spawn;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.spawn.SpawnAction;
import org.simulation.entities.statics.Rock;

public class RockSpawnAction extends SpawnAction<Rock> {
    public RockSpawnAction(Map map) {
        super(map);
        setRate(4);
    }

    @Override
    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates, map);
    }
}
