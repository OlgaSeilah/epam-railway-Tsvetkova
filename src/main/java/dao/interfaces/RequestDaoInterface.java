package dao.interfaces;

import entity.Request;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface RequestDaoInterface extends CRUDInterface<Request, String> {
    List<String> readThreeMostPopularStations() throws SQLException;
    HashMap<String, List<Integer>> getListOfEqualsRequestsHavingByOnePass(Request request);
}
