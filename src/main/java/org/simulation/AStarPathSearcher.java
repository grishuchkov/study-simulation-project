package org.simulation;

import org.simulation.entities.Entity;
import org.simulation.entities.Grass;
import org.simulation.entities.creature.Herbivore;
import org.simulation.entities.creature.Predator;

import java.util.*;


public class AStarPathSearcher implements PathSearcher {
    @Override
    public List<Coordinates> findPath(Entity startEntity, Map map) {

        Optional<List<? extends Entity>> targetListOptional = Optional.ofNullable(setTargetListForEntityByClass(map, startEntity.getClass()));
        List<Coordinates> finalRoute = new ArrayList<>();

        if (targetListOptional.isEmpty()) {
            return finalRoute;
        }

        Coordinates startCoordinates = startEntity.getCoordinates();

        List<? extends Entity> targetEntitiesList = targetListOptional.get();

        if (!targetEntitiesList.isEmpty()) {
            Coordinates targetEntityCoordinates = findNearestTarget(startCoordinates, targetEntitiesList);
            finalRoute = aStarSearchAlgorithm(startCoordinates, targetEntityCoordinates, map);
        }

        return finalRoute;
    }

    private Coordinates findNearestTarget(Coordinates startCoordinates, List<? extends Entity> targetEntities) {

        int minDistance = Integer.MAX_VALUE;
        int minDistanceEntityIndex = 0;


        for (int i = 0; i < targetEntities.size(); i++) {
            int distance = getChebyshevDistanceToTarget(startCoordinates, targetEntities.get(i).getCoordinates());

            if (distance <= minDistance) {
                minDistance = distance;
                minDistanceEntityIndex = i;
            }
        }

        return targetEntities.get(minDistanceEntityIndex).getCoordinates();
    }

    private List<Coordinates> aStarSearchAlgorithm(Coordinates start, Coordinates target, Map map) {

        HashMap<Coordinates, SearchNode> closed = new HashMap<>();

        List<SearchNode> open = new ArrayList<>();
        SearchNode startNode = new SearchNode(start);
        SearchNode targetNode = new SearchNode(target);

        startNode.setCoordinatesBefore(start);
        startNode.setG(0);
        startNode.setF(startNode.getG() + getChebyshevDistanceToTarget(start, target));
        open.add(startNode);

        int brakeIndex = 0;
        while (!open.isEmpty() & brakeIndex < 100000) {
            brakeIndex++;
            SearchNode current = getNodeWithMinF(open);

            if (current.getCoordinates().equals(targetNode.getCoordinates())) {
                closed.put(current.getCoordinates(), current);
                break;
            }

            open.remove(current);

            if (!closed.containsKey(current.getCoordinates())) {
                closed.put(current.getCoordinates(), current);
            }

            List<SearchNode> neighbours = getNeighbors(current, map);
            removeUnsuitableNeighbours(neighbours, map, startNode);

            for (SearchNode neighbor : neighbours) {

                int tmpG = current.getG() + getChebyshevDistanceToTarget(current.getCoordinates(), neighbor.getCoordinates());

                if (!open.contains(neighbor) || tmpG < neighbor.getG()) {
                    neighbor.setG(tmpG);
                    neighbor.setF(neighbor.getG() + getChebyshevDistanceToTarget(neighbor.getCoordinates(), targetNode.getCoordinates()));
                    neighbor.setCoordinatesBefore(current.getCoordinates());
                }
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                }
            }
        }

        return restoreRoute(start, target, closed);
    }

    private static List<Coordinates> restoreRoute(Coordinates start, Coordinates target, HashMap<Coordinates, SearchNode> closed) {
        List<Coordinates> route = new ArrayList<>();

        SearchNode node = closed.get(target);
        while (node != null && !node.getCoordinates().equals(start)) {
            route.add(node.getCoordinates());
            node = closed.get(node.getCoordinatesBefore());
        }
        Collections.reverse(route);

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

    private static void removeUnsuitableNeighbours(List<SearchNode> neighbors, Map map, SearchNode startNode) {
        Iterator<SearchNode> searchNodeIterator = neighbors.iterator();

        while (searchNodeIterator.hasNext()) {
            SearchNode neighboursNode = searchNodeIterator.next();

            if (map.isStaticEntity(neighboursNode.getCoordinates()) | isUnsuitableNeighbours(startNode, neighboursNode, map)) {
                searchNodeIterator.remove();
            }
        }
    }

    private static boolean isUnsuitableNeighbours(SearchNode startEntity, SearchNode neighboursNode, Map map) {
        Coordinates neighboursCoordinates = neighboursNode.getCoordinates();

        if (map.coordinateIsEmpty(neighboursCoordinates)) {
            return false;
        }

        Class<? extends Entity> startEntityClass = map.getEntity(startEntity.getCoordinates()).getClass();
        Class<? extends Entity> neighboursClass = map.getEntity(neighboursCoordinates).getClass();

        if (Herbivore.class.isAssignableFrom(startEntityClass) &
                (Predator.class.isAssignableFrom(neighboursClass) | Herbivore.class.isAssignableFrom(neighboursClass))) {
            return true;
        }
        if (Predator.class.isAssignableFrom(startEntityClass) &
                (Grass.class.isAssignableFrom(neighboursClass) | Predator.class.isAssignableFrom(neighboursClass))) {
            return true;
        }
        return false;
    }


    //todo BinarySearch. getNodeWithMinF() compare with findNearestTarget(). May be possible to unite.
    private SearchNode getNodeWithMinF(List<SearchNode> nodes) {
        int minF = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getF() <= minF) {
                minF = nodes.get(i).getF();
                index = i;
            }
        }

        return nodes.get(index);
    }

    private int getChebyshevDistanceToTarget(Coordinates from, Coordinates to) {
        return Math.max(Math.abs(to.getX() - from.getX()), Math.abs(to.getY() - from.getY()));
    }

    private static List<? extends Entity> setTargetListForEntityByClass(Map map, Class<? extends Entity> aimClass) {
        List<? extends Entity> list = null;

        if (Herbivore.class.isAssignableFrom(aimClass)) {
            list = map.getListEntitiesByClass(Grass.class);
        }
        if (Predator.class.isAssignableFrom(aimClass)) {
            list = map.getListEntitiesByClass(Herbivore.class);
        }

        return list;
    }
}
