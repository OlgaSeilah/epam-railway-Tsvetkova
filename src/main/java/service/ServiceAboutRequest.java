package service;
import dao.RequestDAO;
import dao.StationsDAO;
import entity.Request;

import java.sql.SQLException;
import java.util.List;

public class ServiceAboutRequest {
    final RequestDAO requestDAO;
    final StationsDAO stationsDAO;

    public ServiceAboutRequest(RequestDAO requestDAO, StationsDAO stationsDAO) {
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

    public List<String> getThreeMostPopularStations()    {

    }
}
