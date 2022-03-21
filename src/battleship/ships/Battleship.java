package battleship.ships;

public class Battleship extends Ship {

    public Battleship (){
        this.size = 4;
        this.shipName = "Battleship";
        this.coordinates = new String[size];
    }

    @Override
    public void printPutMessage() {
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
    }
}
