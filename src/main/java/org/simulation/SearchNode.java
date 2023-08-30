package org.simulation;

import java.util.Objects;

public class SearchNode {
    private Coordinates coordinates;

    private Coordinates coordinatesBefore;

    private int f;
    private int g;

    public Coordinates getCoordinatesBefore() {
        return coordinatesBefore;
    }

    public void setCoordinatesBefore(Coordinates coordinatesBefore) {
        this.coordinatesBefore = coordinatesBefore;
    }

    public SearchNode(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchNode node = (SearchNode) o;

        if (f != node.f) return false;
        if (g != node.g) return false;
        if (!Objects.equals(coordinates, node.coordinates)) return false;
        return Objects.equals(coordinatesBefore, node.coordinatesBefore);
    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        result = 31 * result + (coordinatesBefore != null ? coordinatesBefore.hashCode() : 0);
        result = 31 * result + f;
        result = 31 * result + g;
        return result;
    }
}
