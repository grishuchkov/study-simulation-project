package org.simulation;

import org.simulation.entities.Entity;

import java.util.List;

public interface PathSearcher {

    public List<Coordinates> findPath(Entity startEntity, Map map);
}
