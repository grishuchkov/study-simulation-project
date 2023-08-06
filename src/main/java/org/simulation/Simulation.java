package org.simulation;

import org.simulation.action.Action;
import org.simulation.action.spawn.PredatorSpawnAction;
import org.simulation.action.spawn.RockSpawnAction;

import java.util.LinkedList;
import java.util.List;

public class Simulation {

    public void start(){
        Map worldMap = new Map();
        Renderer consoleRenderer = new Renderer(worldMap);

        initAction(worldMap);
        consoleRenderer.render();
    }


    private void initAction(Map map){
        List<Action> initActions = new LinkedList<>();

        initActions.add(new RockSpawnAction(map));
        initActions.add(new PredatorSpawnAction(map));

        for (int i = 0; i < initActions.size(); i++) {
            initActions.get(i).perform();
        }
    }
}
