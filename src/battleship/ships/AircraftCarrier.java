package battleship.ships;

import battleship.ships.Ship;

public class AircraftCarrier extends Ship {

    public AircraftCarrier (){
        this.size = 5;
        this.shipName = "Aircraft Carrier";
        this.coordinates = new int [2][size];
        this.hitPoints = 5;
    }
}
