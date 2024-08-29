package cse250.pa2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreetGraph {
    public Map<String, Intersection> intersections = new HashMap<>();
    public List<Edge> edges = new ArrayList<>();

    private static class StreetGraphParser extends DefaultHandler {
        private StreetGraph graph;
        private Map<String, String> tags;
        private List<String> nodes;

        public StreetGraph loadFromFile(String file) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                saxParser.parse(file, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return graph;
        }

        @Override
        public void startDocument() throws SAXException {
            graph = new StreetGraph();
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case "node":
                    String id = attributes.getValue("id");
                    assert(!graph.intersections.containsKey(id));
                    Double lat = Double.parseDouble(attributes.getValue("lat"));
                    Double lon = Double.parseDouble(attributes.getValue("lon"));
                    Intersection intersection = new Intersection(id, lat, lon);
                    graph.intersections.put(id, intersection);
                    break;
                case "way":
                    tags = new HashMap<>();
                    nodes = new ArrayList<>();
                    break;
                case "nd":
                    if (nodes != null) nodes.add(attributes.getValue("ref"));
                    break;
                case "tag":
                    if (tags != null) tags.put(attributes.getValue("k"), attributes.getValue("v"));
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "way":
                    if (tags.containsKey("tiger:name_base") && tags.containsKey("tiger:name_type") && !nodes.isEmpty()) {
                        String name = tags.getOrDefault("name",
                                tags.get("tiger:name_base") + " " + tags.get("tiger:name_type"));

                        String from = nodes.get(0);
                        for (int k = 1; k < nodes.size(); k++) {
                            String to = nodes.get(k);
                            graph.edges.add(new Edge(graph.intersections.get(from), graph.intersections.get(to), name));
                            from = to;
                        }

                        if (!"yes".equals(tags.getOrDefault("oneway", "no"))) {
                            from = nodes.get(nodes.size() - 1);
                            for (int k = nodes.size() - 2; k >= 0; k--) {
                                String to = nodes.get(k);
                                graph.edges.add(new Edge(graph.intersections.get(from), graph.intersections.get(to), name));
                                from = to;
                            }
                        }
                    }
                    tags = null;
                    nodes = null;
                    break;
            }
        }
    }

    public static StreetGraph load(String file) {
        StreetGraphParser parser = new StreetGraphParser();
        return parser.loadFromFile(file);
    }
}
