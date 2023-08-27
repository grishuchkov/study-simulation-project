package org.simulation.action.spawn.statics;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.action.spawn.SpawnAction;
import org.simulation.entities.statics.Tree;

public class TreeSpawnAction extends SpawnAction<Tree> {
    public TreeSpawnAction(Map map) {
        super(map);
        setRate(15);
    }

    @Override
    protected Tree spawnEntity(Coordinates coordinates) {
        return new Tree(coordinates, map);
    }
}
