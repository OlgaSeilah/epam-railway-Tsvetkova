package dao;

import entity.Request;
import entity.Station;

import java.sql.SQLException;
import java.util.List;

public interface RequestDaoInterface extends CRUDInterface<Request, String> {
    List<String> readThreeMostPopularStations() throws SQLException;
}
