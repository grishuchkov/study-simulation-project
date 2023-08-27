package org.simulation;

import org.simulation.entities.Entity;
import org.simulation.entities.Grass;
import org.simulation.entities.creature.Herbivore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class AStarPathSearch {
    public List<Coordinates> findPath(Coordinates startCoordinates, Map map, Class<? extends Entity> aimClass) {
        Optional<List<? extends Entity>> targetListOptional = Optional.ofNullable(setTargetList(map, aimClass));

        if (!targetListOptional.isPresent()) {
            //todo return result if NULL
        }

        List<? extends Entity> targetEntitiesList = targetListOptional.get();
        Coordinates targetEntityCoordinates = findNearestTarget(startCoordinates, targetEntitiesList);

        return aStarSearchAlgorithm(startCoordinates, targetEntityCoordinates, map);
    }

    //todo Doesn't work. Bug with ways. FIX!!!
    private List<Coordinates> aStarSearchAlgorithm(Coordinates start, Coordinates target, Map map) {

        List<SearchNode> closed = new ArrayList<>();
        List<SearchNode> open = new ArrayList<>();
        List<Coordinates> route = new ArrayList<>();
        List<Coordinates> twoLastCoordinates = new ArrayList<>();

        SearchNode startNode = new SearchNode(start);
        SearchNode targetNode = new SearchNode(target);
        startNode.setG(0);
        startNode.setF(startNode.getG() + getManhattanDistanceToTarget(start, target));
        open.add(startNode);

        while (!open.isEmpty()) {
            SearchNode current = getNodeWithMinF(open);

            if (current.getCoordinates().equals(targetNode.getCoordinates())) {
                twoLastCoordinates.add(current.getCoordinatesBefore());
                twoLastCoordinates.add(current.getCoordinates());
                break;
            }

            open.remove(current);

            if (!closed.contains(current)) {
                closed.add(current);
            }

            List<SearchNode> neighbours = getNeighbors(current, map);
            removeStaticNeighbours(neighbours, map);

            for (SearchNode neighbor : neighbours) {
                int tmpG = current.getG() + getManhattanDistanceToTarget(current.getCoordinates(), neighbor.getCoordinates());

                if (!open.contains(neighbor) || tmpG < neighbor.getG()) {
                    neighbor.setCoordinatesBefore(current.getCoordinates());
                    neighbor.setG(tmpG);
                    neighbor.setF(neighbor.getG() + getManhattanDistanceToTarget(neighbor.getCoordinates(), targetNode.getCoordinates()));
                }
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                }
            }
        }

        for (int i = 1; i < closed.size(); i++) {
            route.add(closed.get(i).getCoordinatesBefore());
        }
        route.addAll(twoLastCoordinates);

        return route;
    }

    private List<SearchNode> getNeighbors(SearchNode node, Map map) {
        List<SearchNode> neighbors = new ArrayList<>();

        if (node.getX() - 1 > 0) {
            neighbors.add(new SearchNode(new Coordinates(node.getX() - 1, node.getY())));
        }
        if (node.getX() + 1 <= map.getMaxX()) {
            neighbors.add(new SearchNode(new Coordinates(node.getX() + 1, node.getY())));
        }
        if (node.getY() - 1 > 0) {
            neighbors.add(new SearchNode(new Coordinates(node.getX(), node.getY() - 1)));
        }
        if (node.getY() + 1 <= map.getMaxY()) {
            neighbors.add(new SearchNode(new Coordinates(node.getX(), node.getY() + 1)));
        }

        return neighbors;
    }

    private static void removeStaticNeighbours(List<SearchNode> neighbors, Map map) {
        Iterator<SearchNode> searchNodeIterator = neighbors.iterator();

        while (searchNodeIterator.hasNext()) {
            SearchNode searchNode = searchNodeIterator.next();

            if (map.isStaticEntity(searchNode.getCoordinates())) {
                searchNodeIterator.remove();
            }
        }
    }

    //todo BinarySearch and compare getNodeWithMinF() with findNearestTarget(). May be possible to unite.
    private SearchNode getNodeWithMinF(List<SearchNode> nodes) {
        int minF = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getF() < minF) {
                minF = nodes.get(i).getF();
                index = i;
            }
        }

        return nodes.get(index);
    }

    private Coordinates findNearestTarget(Coordinates startCoordinates, List<? extends Entity> targetEntities) {

        int minDistance = Integer.MAX_VALUE;
        int minDistanceEntityIndex = 0;


        for (int i = 0; i < targetEntities.size(); i++) {
            int distance = getManhattanDistanceToTarget(startCoordinates, targetEntities.get(i).getCoordinates());

            if (distance <= minDistance) {
                minDistance = distance;
                minDistanceEntityIndex = i;
            }
        }

        return targetEntities.get(minDistanceEntityIndex).getCoordinates();
    }

    private int getEuclideanDistanceToTarget(Coordinates from, Coordinates to) {
        return (int) (Math.sqrt(Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getY() - from.getY(), 2)) * 10);
    }

    private int getManhattanDistanceToTarget(Coordinates from, Coordinates to) {
        return Math.abs(to.getX() - from.getX()) + Math.abs(to.getY() - from.getY());
    }

    private static List<? extends Entity> setTargetList(Map map, Class<? extends Entity> aimClass) {
        List<? extends Entity> list = null;

        if (Grass.class.isAssignableFrom(aimClass)) {
            list = map.getListEntitiesByClass(Grass.class);
        }
        if (Herbivore.class.isAssignableFrom(aimClass)) {
            list = map.getListEntitiesByClass(Herbivore.class);
        }

        return list;
    }
}
