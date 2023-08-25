package org.simulation.action;

import org.simulation.Map;
import org.simulation.entities.creature.Creature;

import java.util.List;

public class MoveAction extends Action {
    public MoveAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        List<Creature> entitiesList = map.getListEntitiesByClass(Creature.class);

        for (Creature creature : entitiesList) {
            creature.makeMove();
        }
    }
}
