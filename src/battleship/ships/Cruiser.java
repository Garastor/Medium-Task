package battleship.ships;

public class Cruiser extends Ship {

    public Cruiser (){
        this.size = 3;
        this.shipName = "Cruiser";
        this.coordinates = new String[size];
    }

    @Override
    public void printPutMessage() {
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
    }
}
