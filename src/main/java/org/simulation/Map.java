package org.simulation;

import org.simulation.entities.Entity;
import org.simulation.entities.creature.Creature;
import org.simulation.entities.statics.Rock;
import org.simulation.entities.statics.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {
    private final int MAX_Y = 10;
    private final int MAX_X = (int) (MAX_Y * 1.5);
    private final HashMap<Coordinates, Entity> entityStorage = new HashMap<>();

    public int getMaxX() {
        return MAX_X;
    }

    public int getMaxY() {
        return MAX_Y;
    }

    public Coordinates getEmptyRandomCoordinates() {
        while (true) {
            Coordinates coordinates = getRandomCoordinates();
            if (coordinateIsEmpty(coordinates)) {
                return coordinates;
            }
        }
    }

    public boolean coordinateIsEmpty(Coordinates coordinates) {
        return !entityStorage.containsKey(coordinates);
    }

    private boolean coordinateIsOutsideOfMap(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x - 1 < 1 | y - 1 < 1) {
            return true;
        }
        if (x + 1 > MAX_X | y + 1 > MAX_Y) {
            return true;
        }
        return false;
    }

    public Coordinates getNearbyEmptyCoordinates(Coordinates startCoordinates) {
        Random random = new Random();
        int x = startCoordinates.getX();
        int y = startCoordinates.getY();

        List<Coordinates> offsetCoordinates = new ArrayList<>() {{
            add(new Coordinates(x - 1, y));
            add(new Coordinates(x + 1, y));
            add(new Coordinates(x, y - 1));
            add(new Coordinates(x, y + 1));
        }};


        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(offsetCoordinates.size());
            Coordinates coordinates = offsetCoordinates.get(randomIndex);
            if (coordinateIsEmpty(coordinates) & !coordinateIsOutsideOfMap(coordinates)) {
                return coordinates;
            }
        }

        return startCoordinates;
    }

    private Coordinates getRandomCoordinates() {
        Random random = new Random();
        int x = random.nextInt(MAX_X) + 1;
        int y = random.nextInt(MAX_Y) + 1;
        return new Coordinates(x, y);
    }

    public int getSize() {
        return MAX_X * MAX_Y;
    }

    public <T> List<T> getListEntitiesByClass(Class<T> type) {
        List<T> result = new ArrayList<>();
        List<Entity> entities = entityStorage.values().stream().toList();

        for (Entity entity : entities) {
            if (type.isAssignableFrom(entity.getClass())) {
                result.add((T) entity);
            }
        }
        return result;
    }

    public void replaceEntity(Entity entity, Coordinates to) {
        entityStorage.remove(entity.getCoordinates());
        setEntity(entity, to);
    }

    public boolean isStaticEntity(Coordinates coordinates) {
        Entity entity = entityStorage.get(coordinates);
        if (coordinateIsEmpty(coordinates)) {
            return false;
        }
        return (Rock.class.isAssignableFrom(entity.getClass()) | Tree.class.isAssignableFrom(entity.getClass()));
    }

    public void setEntity(Entity entity, Coordinates coordinates) {
        entity.setCoordinates(coordinates);
        entityStorage.put(coordinates, entity);
    }


    public Entity getEntity(Coordinates coordinates) {
        return entityStorage.get(coordinates);
    }

    public Creature getCreature(Coordinates coordinates) {
        return (Creature) getEntity(coordinates);
    }
}
