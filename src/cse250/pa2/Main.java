package cse250.pa2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StreetGraph graph = StreetGraph.load("data/buffalo_map.xml");

        System.out.println("Loaded " + graph.intersections.size() + " intersections");
        System.out.println("Loaded " + graph.edges.size() + " edges");

        Map<String, List<Edge>> outgoing = MapUtils.computeOutgoingEdges(graph);

        System.out.println(outgoing.size() + " intersections with outgoing edges");

        List<Edge> route1 = MapUtils.pathWithFewestIntersections(graph, outgoing, "111408692", "111370576");
        System.out.println(MapUtils.nameOfIntersection(route1.get(0).from.id, outgoing));
        System.out.println(route1.stream().skip(1).map(edge -> edge.to.id).map(id -> MapUtils.nameOfIntersection(id, outgoing)).collect(Collectors.joining("\n")));

        System.out.println("-------");

        List<Edge> route2 = MapUtils.pathWithFewestIntersections(graph, outgoing, "111408692", "111571000");
        System.out.println(MapUtils.nameOfIntersection(route2.get(0).from.id, outgoing));
        System.out.println(route2.stream().skip(1).map(edge -> edge.to.id).map(id -> MapUtils.nameOfIntersection(id, outgoing)).collect(Collectors.joining("\n")));

        System.out.println("-------");

        List<Edge> route3 = MapUtils.pathWithShortestDistance(graph, outgoing, "111408692", "111571000");
        System.out.println(MapUtils.nameOfIntersection(route3.get(0).from.id, outgoing));
        System.out.println(route3.stream().skip(1).map(edge -> edge.to.id).map(id -> MapUtils.nameOfIntersection(id, outgoing)).collect(Collectors.joining("\n")));
    }
}
