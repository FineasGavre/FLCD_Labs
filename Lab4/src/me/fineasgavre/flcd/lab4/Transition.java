package me.fineasgavre.flcd.lab4;

import java.util.ArrayList;
import java.util.List;

public class Transition {
    private String source;
    private String route;
    private final List<String> destinations;

    Transition(String source, String route) {
        this.source = source;
        this.route = route;
        this.destinations = new ArrayList<>();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void addTarget(String target) {
        this.destinations.add(target);
    }

    @Override
    public String toString() {
        return "Transition{" +
                "(" + source + ", " +
                route + ") -> " +
                destinations +
                '}';
    }
}
