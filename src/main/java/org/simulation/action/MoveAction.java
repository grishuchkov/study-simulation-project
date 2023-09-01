package org.simulation.action;

import org.simulation.Map;
import org.simulation.entity.creature.Creature;
import org.simulation.entity.creature.Herbivore;

import java.util.List;

public class MoveAction extends Action {
    public MoveAction(Map map) {
        super(map);
    }


    private boolean hasNextStep = true;

    public boolean hasNextStep() {
        return hasNextStep;
    }

    @Override
    public void perform() {
        List<Creature> creaturesList = map.getListEntitiesByClass(Creature.class);
        List<Herbivore> herbivores = map.getListEntitiesByClass(Herbivore.class);

        if (herbivores.isEmpty()) {
            hasNextStep = false;
        }

        for (Creature creature : creaturesList) {
            creature.makeMove();
        }
    }
}
