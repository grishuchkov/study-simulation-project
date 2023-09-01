package org.simulation;

import org.simulation.action.Action;
import org.simulation.action.MoveAction;
import org.simulation.action.spawn.GrassSpawnAction;
import org.simulation.action.spawn.creature.HerbivoreSpawnAction;
import org.simulation.action.spawn.creature.PredatorSpawnAction;
import org.simulation.action.spawn.statics.RockSpawnAction;
import org.simulation.action.spawn.statics.TreeSpawnAction;
import org.simulation.renderer.ConsoleRenderer;
import org.simulation.renderer.Renderer;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final Map worldMap = new Map();
    private final Renderer consoleRenderer = new ConsoleRenderer(worldMap);
    private final MoveAction moveAction = new MoveAction(worldMap);
    private volatile boolean onPause = true;
    private int stepsOfSimulation = 0;

    public void setPause(boolean pause) {
        onPause = pause;
    }

    private void init() {
        initAction(worldMap);
    }

    public synchronized void nextTurn() {
        moveAction.perform();
        consoleRenderer.render();
        stepsOfSimulation += 1;
        System.out.println("Step: " + stepsOfSimulation);
    }

    public void startSimulation() throws InterruptedException {
        init();

        while (moveAction.hasNextStep()) {
            while (onPause) {
                Thread.sleep(1000);
            }
            nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Simulation is Done. Total steps of simulation: " + stepsOfSimulation);
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
