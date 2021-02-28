package entity;

public class Station {
    private int id;
    private String stationName;

    public Station(int id, String stationName) {
        this.id = id;
        this.stationName = stationName;
    }

    public Station() {

    }

    public Station(String stationName) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
