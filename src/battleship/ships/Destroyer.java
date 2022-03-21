package battleship.ships;

public class Destroyer extends Ship {

    public Destroyer(){
        this.size = 2;
        this.shipName = "Destroyer";
        this.coordinates = new String[size];
    }

    @Override
    public void printPutMessage() {
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
    }
}
