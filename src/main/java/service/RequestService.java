package service;
import dao.RequestDAO;
import dao.StationsDAO;
import dao.exception.SuchStationDoesNotExistException;
import entity.Request;

import java.sql.SQLException;
import java.util.List;

public class RequestService {
    final RequestDAO requestDAO;
    final StationsDAO stationsDAO;

    public RequestService(RequestDAO requestDAO, StationsDAO stationsDAO) {
        this.requestDAO = requestDAO;
        this.stationsDAO = stationsDAO;
    }

    public boolean createRequest(Request request) throws SuchStationDoesNotExistException, SQLException {
            if (stationsDAO.read(request.getStartStation()) != null && stationsDAO.read(request.getDestinationStation()) != null){
                    requestDAO.create(request);
                    return true;
            } else {
                throw new SuchStationDoesNotExistException();
            }
    }

    public List<String> getThreeMostPopularStations() {
        try {
            return requestDAO.readThreeMostPopularStations();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } return null;
    }

}
