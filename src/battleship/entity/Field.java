package battleship.entity;

public class Field {

    private String[][] grid;
    private boolean allShipsSink;
    private Ship[] ships;
    private int shipCount;

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public boolean isAllShipsSink() {
        return allShipsSink;
    }

    public void setAllShipsSink(boolean allShipsSink) {
        this.allShipsSink = allShipsSink;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }
}
