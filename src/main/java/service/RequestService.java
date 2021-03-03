package service;
import dao.RequestDAO;
import dao.StationsDAO;
import entity.Request;

import java.sql.SQLException;
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
            System.out.println("не получили названия станций здесь");
            return false;
        } else if (!request.getStartStation().equals(createRequest.getStartStation())) {
//            throw new SuchStationDoesNotExistException();
            System.out.println("Не найдена стартовая станция");
            return false;
        } else if (!request.getDestinationStation().equals(createRequest.getDestinationStation())) {
            throw new SuchStationDoesNotExistException();
//            System.out.println("не найдена станция назначения");
//            return false;
        }  else return true;


//            if (stationsDAO.read(request.getStartStation()) != null && stationsDAO.read(request.getDestinationStation()) != null){
//                    requestDAO.create(request);
//                    return true;
//            } else {
//                throw new SuchStationDoesNotExistException();
//            }
    }

    public List<String> getThreeMostPopularStations() {
        try {
            return requestDAO.readThreeMostPopularStations();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } return null;
    }

    public boolean deleteRequest(Request delRequest) {
        try {
            requestDAO.delete(delRequest);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } return false;
    }

    public HashMap<String, List<Integer>> getListOfRequestsInOneUser(Request request) {
        return requestDAO.getListOfEqualsRequestsHavingByOnePass(request);
    }

}
