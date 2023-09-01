package org.simulation.action.spawn.creature;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.spawn.SpawnAction;
import org.simulation.entity.creature.Herbivore;

public class HerbivoreSpawnAction extends SpawnAction<Herbivore> {
    public HerbivoreSpawnAction(Map map) {
        super(map);
        setRate(4);
    }

    @Override
    protected Herbivore spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates, map);
    }
}
