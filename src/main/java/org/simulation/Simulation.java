package org.simulation;

import org.simulation.action.Action;
import org.simulation.action.MoveAction;
import org.simulation.action.spawn.GrassSpawnAction;
import org.simulation.action.spawn.creature.HerbivoreSpawnAction;
import org.simulation.action.spawn.creature.PredatorSpawnAction;
import org.simulation.action.spawn.statics.RockSpawnAction;
import org.simulation.action.spawn.statics.TreeSpawnAction;
import org.simulation.entities.creature.Herbivore;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final Map worldMap = new Map();
    private final Renderer consoleRenderer = new ConsoleRenderer(worldMap);
    private final MoveAction moveAction = new MoveAction(worldMap);
    private boolean onPause = false;

    private void init() {
        initAction(worldMap);
        consoleRenderer.render();
    }

    public void nextTurn() {
        moveAction.perform();
        consoleRenderer.render();
    }

    public void startSimulation() {
        init();
        while (!onPause & !gameOff()) {
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

    private boolean gameOff() {
        List<Herbivore> herbivores = worldMap.getListEntitiesByClass(Herbivore.class);
        if (herbivores.isEmpty()) {
            System.out.println("Simulation is done");
            return true;
        }
        return false;
    }

    private void initAction(Map map) {
        List<Action> initActions = new LinkedList<>();

        initActions.add(new RockSpawnAction(map));
        initActions.add(new TreeSpawnAction(map));
        initActions.add(new GrassSpawnAction(map));
        initActions.add(new PredatorSpawnAction(map));
        initActions.add(new HerbivoreSpawnAction(map));

        for (Action action : initActions) {
            action.perform();
        }
    }
}
