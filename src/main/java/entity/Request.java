package entity;

public class Request {
    private int requestId;
    private String passengerLogin;
    private String startStation;
    private String destinationStation;


    public Request(String passengerLogin, String startStation, String destinationStation) {
        this.passengerLogin = passengerLogin;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
    }

    public Request(String passengerLogin) {
        this.passengerLogin = passengerLogin;

    }

    public Request(int requestId) {
        this.requestId = requestId;
    }

    public Request(int requestId, String passengerLogin, String startStation, String destinationStation) {
        this.requestId = requestId;
        this.passengerLogin = passengerLogin;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
    }


    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }


    public  String getPassengerLogin(){
        return passengerLogin;
    }

    public void setPassengerLogin() {
        this.passengerLogin = passengerLogin;
    }

    public String getStartStation() {
        return startStation;
    }


    public String getDestinationStation() {
        return destinationStation;
    }

    public void setPassengerLogin(String passengerLogin) {
        this.passengerLogin = passengerLogin;

    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    @Override
    public String toString() {
        return "Request id: " + this.getRequestId() +  "\nUser login: " + this.getPassengerLogin() +
                "\nStart station: " + this.getStartStation() +
                "\n Destination station: " + this.getDestinationStation();
    }

}
