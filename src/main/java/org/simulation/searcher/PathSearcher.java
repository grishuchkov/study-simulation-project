package org.simulation.searcher;

import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.entity.Entity;

import java.util.List;

public interface PathSearcher {

    List<Coordinates> findPath(Entity startEntity, Map map);
}
