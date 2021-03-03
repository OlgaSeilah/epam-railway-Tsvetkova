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

    public Request(String passengerLogin) {
        this.passengerLogin = passengerLogin;

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

    public void setPassengerLogin(String passengerLogin) {

    }

    public void setStartStation(String startStation) {
        this.startStation = this.startStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = this.destinationStation;
    }

    @Override
    public String toString() {
        return "User login: " + this.getPassengerLogin() + "\nStart station: " + this.getStartStation() +
                "\n Destination station: " + this.getDestinationStation();
    }

}
