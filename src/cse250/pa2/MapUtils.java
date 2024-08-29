package cse250.pa2;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.LinkedList;
public class MapUtils {
    /**
     * Compute the outgoing links from each intersection in the graph.
     * @param    graph     The intersections and links for a map
     * @return             A lookup table of all outgoing edges for each intersection
     *
     * This function should run in O(graph.edges.size)
     */
    public static Map<String, List<Edge>> computeOutgoingEdges(StreetGraph graph) {
        var answer = new HashMap<String, List<Edge>>();
        for(Edge iter : graph.edges){


            if(answer.containsKey(iter.from.id)){
                answer.get(iter.from.id).add(iter);
            }
            else{
                var edgelist = new ArrayList<Edge>();
                edgelist.add(iter);
                answer.put(iter.from.id, edgelist);
            }
        }

        return answer;
    }
    /**
     * Compute the path between two intersections that passes through the fewest intersections.
     * @param    graph         The intersections and links for a map
     * @param    outgoingEdges A lookup table of all outgoing edges for each intersection
     * @param    from          The ID of the intersection to route from
     * @param    to            The ID of the intersection to route to
     * @return                 The shortest path needed to get from [[from]] to [[to]]
     *
     * This function should run in O(graph.edges.size + graph.intersections.size)
     */
    public static List<Edge> pathWithFewestIntersections(
            StreetGraph graph,
            Map<String, List<Edge>> outgoingEdges,
            String from,
            String to) {





        Queue<String> todos = new LinkedList<>();


        //implement a hashmap that tracks visisted nodes
        var checkedges = new HashMap<Edge, Integer>();
        var checkints = new HashMap<String, Integer>();
        var parents = new HashMap<String, String>();
        var parentedges = new HashMap<String, Edge>();

        var answer = new ArrayList<Edge>(outgoingEdges.size());
        todos.add(from);
        while(!todos.isEmpty()) {
            String current = todos.remove();



            if (outgoingEdges.get(current) == null) {

            } else {
                for (Edge iter : outgoingEdges.get(current)) {

                    if (checkedges.get(iter) == null) { //checks if this edge has been explored
                        String opposite = iter.to.id;

                        if(checkints.get(opposite) == null) { //checks if the to of the edge has been explored

                            todos.add(opposite);
                            checkints.put(opposite, 1); //marks the to of the edge as visited
                            parents.put(opposite, current); //puts into parent node
                            parentedges.put(opposite, iter); //puts edges of parent node to
                            checkedges.put(iter, 2); //marks iter as visited/spanning


                        } else {


                            checkedges.put(iter, 3); //marks iter as visited/back
                        }
                    }
                }

            }
        }




        var hold = parents.get(to);
            while (true) {
                answer.add(0, parentedges.get(hold));
                hold = parents.get(hold);
                if(hold==from){break;}
            }

        answer.add(parentedges.get(to));
        for(int i=0; i<answer.size(); i++){
            System.out.println("streetname: " + answer.get(i).streetName);
        }

        System.out.println("OOGABOOGAWOOGAA!!!!");
        System.out.println("THE ANSWER ISSSSS: " + answer);
        System.out.println("THE SIZE OF ANSWER FOR SOME REASON IS: " + answer.size());



        return answer;
    }

    /**
     * Compute the path between two intersections that has the shortest total distance.
     * @param    graph         The intersections and streets for a map
     * @param    outgoingEdges A lookup table of all outgoing edges for each intersection
     * @param    from          The ID of the intersection to route from
     * @param    to            The ID of the intersection to route to
     * @return                 The shortest path needed to get from [[from]] to [[to]]
     *
     * This function should run in O(graph.edges.size * log(graph.intersections.size))
     */
    public static List<Edge> pathWithShortestDistance(
            StreetGraph graph,
            Map<String, List<Edge>> outgoingEdges,
            String from,
            String to) {
        //TODO:
        return new ArrayList<>();
    }

    /**
     * Generate a human-readable name for an intersection
     */
    public static String nameOfIntersection(String id, Map<String, List<Edge>> outgoingEdges) {
        List<String> streetNames = outgoingEdges.get(id).stream()
                .map(edge -> edge.streetName)
                .distinct()
                .collect(Collectors.toList());

        switch (streetNames.size()) {
            case 0:
                return id + " @ Empty Intersection";
            case 1:
                return id + " @ " + streetNames.get(0);
            case 2:
                return id + " @ " + streetNames.get(0) + " and " + streetNames.get(1);
            default:
                String allButLast = String.join(", ", streetNames.subList(0, streetNames.size() - 1));
                return id + " @ " + allButLast + ", and " + streetNames.get(streetNames.size() - 1);
        }
    }
}
