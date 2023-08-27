package org.simulation;

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

    public SearchNode(Coordinates coordinates, int f, int g) {
        this.coordinates = coordinates;
        this.f = f;
        this.g = g;
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

}
