package org.simulation;

import org.simulation.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {
    private final int MAX_X = 15;
    private final int MAX_Y = 10;
    private final HashMap<Coordinates, Entity> entityStorage = new HashMap<>();

    public int getMaxCoordinate(Axes axis) {
        if (axis.equals(Axes.X)) {
            return MAX_X;
        } else {
            return MAX_Y;
        }
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

    public Coordinates getEmptyRandomCoordinates() {
        while (true) {
            Coordinates coordinates = getRandomCoordinates();
            if (isEmpty(coordinates)) {
                return coordinates;
            }
        }
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

    public void setEntity(Entity entity, Coordinates coordinates) {
        entity.setCoordinates(coordinates);
        entityStorage.put(coordinates, entity);
    }

    public boolean isEmpty(Coordinates coordinates) {
        return !entityStorage.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entityStorage.get(coordinates);
    }
}
