package ru.solution;

import java.util.Objects;

public class GasStation implements Comparable<GasStation> {
    private int startPoint;
    private int endPoint;
    private int distance;

    public GasStation(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        distance = endPoint - startPoint;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GasStation that = (GasStation) o;
        return startPoint == that.startPoint
                && endPoint == that.endPoint
                && distance == that.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, distance);
    }

    @Override
    public int compareTo(GasStation o) {
        return Integer.compare(this.distance, o.distance);
    }
}
