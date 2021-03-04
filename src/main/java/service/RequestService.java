package service;

import dao.RequestDAO;
import dao.StationsDAO;
import entity.Request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestService {
    final RequestDAO requestDAO;
    final StationsDAO stationsDAO;

    public RequestService(RequestDAO requestDAO, StationsDAO stationsDAO) {
        this.requestDAO = requestDAO;
        this.stationsDAO = stationsDAO;
    }
    Request request=null;

    public boolean createRequest(Request createRequest) throws SuchStationDoesNotExistException, SQLException {

        try {
            request = requestDAO.create(createRequest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (request == null) {
            return false;
        } else if (!request.getStartStation().equals(createRequest.getStartStation())) {
            System.out.println("Не найдена стартовая станция");
            return false;
        } else if (!request.getDestinationStation().equals(createRequest.getDestinationStation())) {
            System.out.println("не найдена станция назначения");
        }  else return true;
        return false;
    }

    public Request read(int requestId) {
        try {
            return requestDAO.read(requestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changeRequest(Request requestForEdit ) {
        try {
            requestDAO.update(requestForEdit);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRequest(Request delRequest) {
        try {
            requestDAO.delete(delRequest);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } return false;
    }

    public List<String> getThreeMostPopularStations() {
        try {
            return requestDAO.readThreeMostPopularStations();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } return null;
    }

    public HashMap<String, ArrayList<Integer>> getListOfRequestsInOneUser(Request request) {
        return requestDAO.getListOfEqualsRequestsHavingByOnePass(request);
    }

}
