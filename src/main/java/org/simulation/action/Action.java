package org.simulation.action;

import org.simulation.Map;

public abstract class Action {
    protected Map map;

    public Action(Map map) {
        this.map = map;
    }

    public abstract void perform();
}
