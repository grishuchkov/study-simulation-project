package org.simulation.action.spawn.creature;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.spawn.SpawnAction;
import org.simulation.entity.creature.Predator;

public class PredatorSpawnAction extends SpawnAction<Predator> {
    public PredatorSpawnAction(Map map) {
        super(map);
        setRate(3);
    }

    @Override
    protected Predator spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates, map);
    }
}
