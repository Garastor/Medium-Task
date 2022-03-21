package battleship.ships;

public class Battleship extends Ship{

    public Battleship (){
        this.size = 4;
        this.shipName = "Battleship";
        this.coordinates = new int [2][size];
    }
}
