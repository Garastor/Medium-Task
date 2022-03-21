package battleship.ships;

public class AircraftCarrier extends Ship {

    public AircraftCarrier (){
        this.size = 5;
        this.shipName = "AircraftCarrier";
        this.coordinates = new String[size];
    }

    @Override
    public void printPutMessage(){
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
    }

}
