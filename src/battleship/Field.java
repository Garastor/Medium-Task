package battleship;

import battleship.ships.*;

import java.util.Objects;

public class Field {

    String[][] grid = new String[11][11];
    boolean allShipsSink = false;

    Ship[] ships = {
            new AircraftCarrier(),
            new Battleship(),
            new Submarine(),
            new Cruiser(),
            new Destroyer()
    };

    int shipCount = ships.length;

    Field() {
        initField();
    }

    //METHOD INIT ALL CELLS OF FIELD
    void initField() {
        for (int x = 0; x < 11; x++) {      //init '~'
            for (int y = 0; y < 11; y++) {
                grid[x][y] = "~";
            }
        }
        for (int y = 1; y < 11; y++) {      //init numbers [1-10]
            grid[0][y] = String.valueOf(y);
        }
        int symbols = 65;
        for (int x = 1; x < 11; x++) {        //init alphabet [A-J]
            char symbol = (char) symbols;
            grid[x][0] = String.valueOf(symbol);
            symbols++;
        }
        grid[0][0] = "";
    }

    //METHOD PRINT FIELD
    public void printField() {
        for (String[] row : grid) {
            for (String symbol : row) {
                System.out.print(symbol + " ");
            }
            System.out.print("\n");
        }
    }

    //METHOD PUT ALL SHIPS ON THE FIELD
    public boolean prepareField() {
        printField();
        boolean done;
        for (int i = 0; i < ships.length; i++) {
            if (!ships[i].isStatus()) {   //looking for ship with status 'false'
                Ship ship = ships[i];
                ship.printPutMessage();
                while (!ship.isStatus()) {     //is status false
                    ship.inputCoordinates();   //put the coordinates
                    if (checkShipLocation(ship)) { //if ship location is checked, do initialize the current ship
                        ship.setStatus(true);
                        putShip(ship);
                        ships[i] = ship;
                        printField();
                    }
                }
            }
        }
        return true;
    }

    //CHECK ALL SPACE AROUND SHIP
    boolean checkShipLocation(Ship ship) {
        if (isCellsEmpty(getArea(ship, 0), ship)
                && isCellsEmpty(getArea(ship, -1), ship)
                && isCellsEmpty(getArea(ship, 1), ship)) {
            return true;
        } else {
            ship.printErrorToClose();
            return false;
        }
    }

    //CHECK IF THE CHARACTERS MATCHES '~'
    boolean isCellsEmpty(int[][] line, Ship ship) {
        boolean checked = true;
        for (int i = 0; i < line[0].length; i++) {
            int x = line[0][i];
            int y = line[1][i];
            if((x>0 && x<11) && (y>0 && y<11)) {
                if (!Objects.equals(grid[y][x], "~")) {
                    checked = false;
                }
            }
        }
        return checked;
    }

    //METHOD CREATE SPACE AROUND SHIP
    //'index' shifting static line to the left or to the right relatively from central position
    int[][] getArea(Ship ship, int lineIndex) {  //indexes: center0; left-1; right+1
        int[][] shipCoordinates = ship.getCoordinates();
        int sizeArea = shipCoordinates[0].length + 2;
        int sizeShip = ship.getSize();
        int[][] area = new int[2][sizeArea];
        int a,b;
        if(ship.isHorizontal()){
            a=0;
            b=1;
        } else {
            a=1;
            b=0;
        }
        for (int i = 0; i < sizeArea; i++) {
            area[a][0] = shipCoordinates[a][0] - 1;
            area[a][sizeArea - 1] = shipCoordinates[a][sizeShip - 1] + 1;
            area[b][i] = shipCoordinates[b][0]+lineIndex;
            if (i<sizeShip) {
                area[a][i + 1] = shipCoordinates[a][i];
            }
        }
        return area;
    }

    //METHOD PUT SHIP ON THE FIELD
    void putShip (Ship ship){
        int[][] coordinates = ship.getCoordinates();
        for (int i = 0; i < coordinates[0].length; i++) {
            int x = coordinates[0][i];
            int y = coordinates[1][i];
            grid[y][x] = "O";
        }
    }

    //METHOD CALCULATE DESTROYED AND DAMAGED SHIPS
     boolean checkShip(int x, int y){
        for (int i=0; i<ships.length; i++){
            Ship ship = ships[i];
            if(!ship.isDestroy()) {                           //find not destroyed ships
                int[][] coordinates = ship.getCoordinates();
                for (int a = 0; a < coordinates[0].length; a++) {
                    if (coordinates[0][a] == x && coordinates[1][a] == y) {
                        int hitPoints = ship.getHitPoints()-1;
                        ship.setHitPoints(hitPoints);
                        if (ship.getHitPoints() == 0){
                            ship.setDestroy(true);
                            shipCount--;
                            ships[i] = ship;
                            if(shipCount == 0){
                                allShipsSink = true;
                                System.out.println("You sank the last ship. You won. Congratulations!\n");
                            } else {
                                System.out.println("You sank a ship! Specify a new target:\n");
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
     }

    public String[][] getGrid() {
        return grid;
    }

    public boolean isAllShipsSink() {
        return allShipsSink;
    }

}
