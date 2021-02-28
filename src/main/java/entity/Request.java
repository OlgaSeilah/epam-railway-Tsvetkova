package entity;

public class Request {
    private int passengerId;
    private String passengerLogin;
    private String startStation;
    private String destinationStation;


    public Request(String passengerLogin, String startStation, String destinationStation) {
        this.passengerLogin = passengerLogin;
        this.startStation = startStation;
        this.destinationStation = destinationStation;
    }

    public int getPassengerId() {
        return passengerId;
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
}
