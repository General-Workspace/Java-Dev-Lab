package generics_challenge;

import generics_challenge.abstract_classes.Line;

public class River extends Line {

    private final String name;

    public River(String name, String... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}
