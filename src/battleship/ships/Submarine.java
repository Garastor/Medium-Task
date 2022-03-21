package battleship.ships;


public class Submarine extends Ship {

    public Submarine () {
        this.size = 3;
        this.shipName = "Submarine";
        this.coordinates = new String[size];
    }

    @Override
    public void printPutMessage() {
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
    }
}
