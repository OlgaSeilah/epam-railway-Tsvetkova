package entity;

public class Station {
    private int id;
    private String stationName;

    public Station() {

    }

    public Station(String stationName) {

    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "Station name: " + this.getStationName();
    }
}
