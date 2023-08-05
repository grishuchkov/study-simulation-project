package org.simulation;

import org.simulation.action.Action;
import org.simulation.action.RockSpawnAction;

public class Simulation {
    public static void main(String[] args) {
        Map worldMap = new Map();
        Renderer consoleRenderer = new Renderer(worldMap);


        Action rockSpawnAction = new RockSpawnAction(worldMap);
        rockSpawnAction.perform();

        consoleRenderer.render();
    }
}
