/**
 * @author hkim944
 * @version 1.0
 */

public class Coordinates {

    private final double latitude;
    private final double longitude;

    /**
     * Creates a Coordinates with a latitude and longitude
     * @param latitude Latitude of a coordinate
     * @param longitude Longitude of a coordinate
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Compare objects whether they have same values
     * @param other An object that gets compared
     * @return Whether they have same values or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coordinates)) {
            return false;
        }
        Coordinates that = (Coordinates) other;
        return this.latitude == that.latitude
                && this.longitude == that.longitude;
    }

    /**
     * @return Sentence in format "latitude: (latitude), longitude: (longitude)"
     */
    @Override
    public String toString() {
        return "latitude: " + String.format("%.2f ", this.latitude)
            + ", longtitude: " + String.format("%.2f ", this.longitude);
    }

    /**
     * @return Latitude of a coordinate
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return Longitude of a coordinate
     */
    public double getLongitude() {
        return longitude;
    }
}