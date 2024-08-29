package cse250.pa2;

public class Edge {
    public Intersection from;
    public Intersection to;
    public String streetName;

    public Edge(Intersection from, Intersection to, String streetName) {
        this.from = from;
        this.to = to;
        this.streetName = streetName;
    }

    public double getDistance() {
        return from.distanceTo(to);
    }
}