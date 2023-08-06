package org.simulation;

import org.simulation.action.Action;
import org.simulation.action.spawn.GrassSpawnAction;
import org.simulation.action.spawn.creature.HerbivoreSpawnAction;
import org.simulation.action.spawn.creature.PredatorSpawnAction;
import org.simulation.action.spawn.statics.RockSpawnAction;
import org.simulation.action.spawn.statics.TreeSpawnAction;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final Map worldMap = new Map();
    private final Renderer consoleRenderer = new Renderer(worldMap);
    public void test(){
        initAction(worldMap);
        consoleRenderer.render();
    }

    private void initAction(Map map){
        List<Action> initActions = new LinkedList<>();

        initActions.add(new RockSpawnAction(map));
        initActions.add(new TreeSpawnAction(map));
        initActions.add(new GrassSpawnAction(map));
        initActions.add(new PredatorSpawnAction(map));
        initActions.add(new HerbivoreSpawnAction(map));

        for (int i = 0; i < initActions.size(); i++) {
            initActions.get(i).perform();
        }
    }
}
