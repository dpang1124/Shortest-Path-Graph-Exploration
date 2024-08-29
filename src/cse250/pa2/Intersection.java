package cse250.pa2;

public class Intersection {
    public String id;
    public double latitude;
    public double longitude;

    public Intersection(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distanceTo(Intersection other) {
        double lat1 = this.latitude * (Math.PI / 180);
        double lon1 = this.longitude * (Math.PI / 180);
        double lat2 = other.latitude * (Math.PI / 180);
        double lon2 = other.longitude * (Math.PI / 180);

        double distanceInRadians = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin((lat1 - lat2) / 2), 2)
                                + Math.cos(lat1) * Math.cos(lat2)
                                * Math.pow(Math.sin((lon1 - lon2) / 2), 2)
                )
        );

        return distanceInRadians * 6371.009;
    }
}

