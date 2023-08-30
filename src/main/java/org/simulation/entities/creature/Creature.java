package org.simulation.entities.creature;

import org.simulation.AStarPathSearcher;
import org.simulation.Coordinates;
import org.simulation.Map;
import org.simulation.PathSearcher;
import org.simulation.entities.Entity;

import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {

    private int hp;
    protected int speed;

    private final PathSearcher pathSearch = new AStarPathSearcher();
    protected final Random random = new Random();

    public Creature(Coordinates coordinates, Map map) {
        super(coordinates, map);
        this.hp = 1;
    }


    public void makeMove() {
        List<Coordinates> pathToTarget = pathSearch.findPath(this, map);
        int pathSize = pathToTarget.size();
        Entity thisEntity = map.getEntity(this.getCoordinates());

        if (pathSize == 0) {
            map.replaceEntity(thisEntity, map.getNearbyEmptyCoordinates(thisEntity.getCoordinates()));
            return;
        }

        int maxIndexOfPathList = pathSize - 1;

        if (pathSize == 1) {
            doSomething(pathToTarget.get(maxIndexOfPathList));
            return;
        }

        if (this.getSpeed() >= pathSize) {
            int indexOfPenultimatePathElement = pathSize - 2;
            Coordinates coordinatesToMove = pathToTarget.get(indexOfPenultimatePathElement);
            map.replaceEntity(thisEntity, coordinatesToMove);
            doSomething(pathToTarget.get(maxIndexOfPathList));
        } else {
            Coordinates coordinatesToMove = pathToTarget.get(this.getSpeed() - 1);
            map.replaceEntity(thisEntity, coordinatesToMove);
        }
    }

    public abstract void doSomething(Coordinates targetCoordinates);

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }
}
