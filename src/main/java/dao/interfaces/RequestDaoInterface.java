package dao.interfaces;

import entity.Request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RequestDaoInterface extends CRUDInterface<Request, Integer> {
    List<String> readThreeMostPopularStations() throws SQLException;
    HashMap<String, ArrayList<Integer>> getListOfEqualsRequestsHavingByOnePass(Request request);
}
