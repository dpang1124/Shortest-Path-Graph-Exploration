package cse250.pa2.tests;

import cse250.pa2.Edge;
import cse250.pa2.Intersection;
import cse250.pa2.MapUtils;
import cse250.pa2.StreetGraph;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapUtilsTests {

    @Test
    public void mytests(){
        StreetGraph graph = new StreetGraph();
        Intersection i1 = new Intersection("street1", 0.0, 0.0);
        Intersection i2 = new Intersection("street2", 1.0, 0.0);
        Intersection i3 = new Intersection("street3", 1.0, 1.0);
        Intersection i4 = new Intersection("street4", -2.0, 2.0);
        Intersection i5 = new Intersection("street5", 2.0, 2.0);
        Intersection i6 = new Intersection("street6", 1.0, 3.0);
        Intersection i7 = new Intersection("street7", 2.0, 3.0);
        Intersection i8 = new Intersection("street8", -5.0, 2.0);

        Edge e1to1 = new Edge(i1, i1, "1-1");
        Edge e1to2 = new Edge(i1, i2, "1-2");
        Edge e1to4 = new Edge(i1, i4, "1-4");
        Edge e1to4dupe = new Edge(i1, i4, "1-4(2)");
        Edge e2to3 = new Edge(i2, i3, "2-3");
        Edge e3to4 = new Edge(i3, i4, "3-4");
        Edge e3to6 = new Edge(i3, i6, "3-6");
        Edge e4to8 = new Edge(i4, i8, "4-8");
        Edge e5to4 = new Edge(i5, i4, "5-4");
        Edge e4to6 = new Edge(i4, i6, "4-6");
        Edge e5to8 = new Edge(i5, i8, "5-8");
        Edge e6to7 = new Edge(i6, i7, "6-7");
        Edge e8to5 = new Edge(i8, i5, "8-5");

        Edge e6to5 = new Edge(i6, i5, "6-5");



        graph.intersections.put("street1", i1);
        graph.intersections.put("street2", i2);
        graph.intersections.put("street3", i3);
        graph.intersections.put("street4", i4);
        graph.intersections.put("street5", i5);
        graph.intersections.put("street6", i6);
        graph.intersections.put("street7", i7);
        graph.intersections.put("street8", i8);


        graph.edges.add(e1to2);
        graph.edges.add(e1to4);
        graph.edges.add(e2to3);
        graph.edges.add(e3to4);
        graph.edges.add(e3to6);
        graph.edges.add(e4to8);
        graph.edges.add(e5to4);
        graph.edges.add(e4to6);
        graph.edges.add(e5to8);
        graph.edges.add(e6to7);
        graph.edges.add(e8to5);
        graph.edges.add(e1to4dupe);
        graph.edges.add(e6to5);
        graph.edges.add(e1to1);


        //test the first function
        var maptest = MapUtils.computeOutgoingEdges(graph);
        var list = new ArrayList<Edge>();
        list.add(e8to5);
        //assertEquals(maptest.get("street8"), list);

        var list2 = new ArrayList<Edge>();
        list2.add(e1to2);
        list2.add(e1to4);

        //assertEquals(maptest.get("street1"), list2);



        //test the second function
        var path1 = new ArrayList<Edge>();
        path1.add(e1to4);
        path1.add(e4to8);
        path1.add(e8to5);

        var path2 = new ArrayList<Edge>();
        path2.add(e1to2);
        path2.add(e2to3);

        var path3 = new ArrayList<Edge>();
        path3.add((e1to4));
        path3.add((e4to6));
        path3.add((e6to7));

        var path4 = new ArrayList<Edge>();

        var path5 = new ArrayList<Edge>();
        path5.add(e1to1);

       assertEquals(MapUtils.pathWithFewestIntersections(graph, maptest, "street1", "street5"), path1);
       assertEquals(MapUtils.pathWithFewestIntersections(graph, maptest, "street1", "street3"), path2);
        assertEquals(MapUtils.pathWithFewestIntersections(graph, maptest, "street1", "street7"), path3);
        assertEquals(MapUtils.pathWithFewestIntersections(graph, maptest, "street7", "street1"), path4);



        /*
        //test the third function
        var path3 = new ArrayList<Edge>();
        path3.add(e1to2);
        path3.add(e2to3);
        path3.add(e3to6);
        path3.add(e6to5);
        assertEquals(MapUtils.pathWithShortestDistance(graph, maptest, "street1", "street5"), path3);

         */
    }

/*
    @Test
    public void loadDataCorrectly() {

        StreetGraph graph = StreetGraph.load("data/buffalo_map.xml");
        assertEquals(282843, graph.intersections.size());
        assertEquals(38636, graph.edges.size());

    }

 */



    /**
     * A simple test that creates a three vertex graph shaped like a right triangle
     */

   /* @Test
    public void testSimpleGraph() {
        StreetGraph graph = new StreetGraph();
        Intersection i1 = new Intersection("Bottom Left", 0.0, 0.0);
        Intersection i2 = new Intersection("Bottom Right", 1.0, 0.0);
        Intersection i3 = new Intersection("Top Right", 1.0, 1.0);

        Edge e1 = new Edge(i1, i2, "Straight Right");
        Edge e2 = new Edge(i2, i3, "Straight Up");
        Edge e3 = new Edge(i1, i3, "Diagonal");

        graph.intersections.put("Bottom Left", i1);
        graph.intersections.put("Bottom Right", i2);
        graph.intersections.put("Top Right", i3);

        graph.edges.add(e1);
        graph.edges.add(e2);
        graph.edges.add(e3);

        Map<String, List<Edge>> adjLists = MapUtils.computeOutgoingEdges(graph);
        List<Edge> path1 = MapUtils.pathWithFewestIntersections(graph, adjLists, "Bottom Left", "Top Right");
        List<Edge> path2 = MapUtils.pathWithShortestDistance(graph, adjLists, "Bottom Left", "Top Right");

        assertTrue(adjLists.containsKey("Bottom Left"));
        assertEquals(2, adjLists.get("Bottom Left").size());

        assertEquals(1, path1.size());
        assertEquals(e3, path1.get(0));

        assertEquals(1, path2.size());
        //assertEquals(e3, path2.get(0));
    }*/


}


