package battleship.ships;

public class Destroyer extends Ship{

    public Destroyer(){
        this.size = 2;
        this.shipName = "Destroyer";
        this.coordinates = new int [2][size];
        this.hitPoints = 2;
    }
}
