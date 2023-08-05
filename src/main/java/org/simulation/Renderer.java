package org.simulation;

import java.util.HashMap;

public class Renderer {

    public void render(Map map){

        for (int y = 1; y <= map.getMaxCoordinate(Axes.Y); y++) {
            for (int x = 1; x <= map.getMaxCoordinate(Axes.X); x++){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
