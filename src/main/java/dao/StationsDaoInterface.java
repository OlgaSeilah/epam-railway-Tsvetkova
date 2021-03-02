package dao;

import entity.Station;

import java.sql.SQLException;
import java.util.List;

public interface StationsDaoInterface  extends CRUDInterface<Station, String> {
    List<String> readAllStationNames() throws SQLException;
}
