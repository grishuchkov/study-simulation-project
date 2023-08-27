package org.simulation;

import org.simulation.entities.Entity;

public class ConsoleRenderer implements Renderer{

    Map map;
    public ConsoleRenderer(Map map) {
        this.map = map;
    }

    public void render(){
        for (int y = 1; y <= map.getMaxY(); y++) {
            for (int x = 1; x <= map.getMaxX(); x++){
                Coordinates coordinates = new Coordinates(x,y);
                String entitySprite = "⬜";
                if(!map.coordinateIsEmpty(coordinates)){
                    entitySprite = setEntitySprite(map.getEntity(coordinates));
                }
                System.out.print(entitySprite);
            }
            System.out.println();
        }
        System.out.println("----");
    }

    private String setEntitySprite(Entity entity){
        String type = entity.getClass().getSimpleName();
        switch (type){
            case "Rock":
                return "\uD83E\uDEA8";
            case "Grass":
                return "\uD83C\uDF31";
            case "Tree":
                return "\uD83C\uDF33";
            case "Herbivore":
                return "\uD83D\uDC24";
            case "Predator":
                return "\uD83E\uDD8A";
            default:
                return "⬜";
        }
    }
}
