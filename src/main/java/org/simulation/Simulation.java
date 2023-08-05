package org.simulation;

public class Simulation {
    public static void main(String[] args) {
        Map worldMap = new Map();
        Renderer consoleRenderer = new Renderer(worldMap);


        consoleRenderer.render();
    }
}
