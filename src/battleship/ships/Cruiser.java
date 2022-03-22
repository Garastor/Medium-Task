package battleship.ships;

public class Cruiser extends Ship{

    public Cruiser (){
        this.size = 3;
        this.shipName = "Cruiser";
        this.coordinates = new int [2][size];
    }
}
