package org.simulation.action.spawn;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entities.Grass;

public class GrassSpawnAction extends SpawnAction<Grass> {
    public GrassSpawnAction(Map map) {
        super(map);
        setRate(5);
    }

    @Override
    protected Grass spawnEntity(Coordinates coordinates) {
        return new Grass(coordinates, map);
    }
}
