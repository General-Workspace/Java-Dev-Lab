package generics_challenge.abstract_classes;

import generics_challenge.interfaces.Mappable;

import java.util.Arrays;

abstract public class Point implements Mappable {
    private final double[] location;

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as POINT (" + location() + ")");

    }

    private String location() {
        return Arrays.toString(location);
    }
}
