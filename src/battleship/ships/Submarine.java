package battleship.ships;

public class Submarine extends Ship{

    public Submarine () {
        this.size = 3;
        this.shipName = "Submarine";
        this.coordinates = new int [2][size];
    }
}
