package battleship.service;

import battleship.entity.Field;
import battleship.entity.Ship;

import java.util.Objects;

public class FieldService {

    //METHOD INIT FIELD
    public static Field initField() {
        Field field = new Field();
        field.setGrid(initGrid());
        field.setShips(new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2),
        });
        field.setShipCount(5);
        return field;
    }

    static String[][] initGrid() {
        String[][] grid = new String[11][11];
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
        return grid;
    }

    //METHOD PRINT FIELD
    public static void printField(Field field) {
        String[][] grid = field.getGrid();
        for (String[] row : grid) {
            for (String symbol : row) {
                System.out.print(symbol + " ");
            }
            System.out.print("\n");
        }
    }

    //METHOD PUT ALL SHIPS ON THE FIELD
    public static Field prepareField() {
        Field field = initField();
        printField(field);
        Ship[] ships = field.getShips();
        for (int i = 0; i < ships.length; i++) {
            if (!ships[i].isStatus()) {   //looking for ship with status 'false'
                Ship ship = ships[i];
                ShipService.printPutMessage(ship);
                while (!ship.isStatus()) {     //is status false
                    ship = ShipService.createShip(ship);//put the coordinates
                    if (checkSpace(ship, field)) { //if ship location is checked, do initialize the current ship
                        ship.setStatus(true);
                        field.setGrid(putShip(ship, field));
                        ships[i] = ship;
                        printField(field);
                    }
                }
            }
        }
        return field;
    }

    //CHECK ALL SPACE AROUND SHIP
    static boolean checkSpace(Ship ship, Field field) {
        if (checkCells(createSpace(ship, 0), field)
                && checkCells(createSpace(ship, -1), field)
                && checkCells(createSpace(ship, 1), field)) {
            return true;
        } else {
            ShipService.printErrorToClose();
            return false;
        }
    }

    //CHECK IF THE CHARACTERS MATCHES '~'
    static boolean checkCells(int[][] line, Field field) {
        String[][] grid = field.getGrid();
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
    static int[][] createSpace(Ship ship, int lineIndex) {  //indexes: center0; left-1; right+1
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
    static String[][] putShip (Ship ship, Field field){
        String[][] grid = field.getGrid();
        int[][] coordinates = ship.getCoordinates();
        for (int i = 0; i < coordinates[0].length; i++) {
            int x = coordinates[0][i];
            int y = coordinates[1][i];
            grid[y][x] = "O";
        }
        field.setGrid(grid);
        return grid;
    }

    //METHOD CALCULATE DESTROYED AND DAMAGED SHIPS
    public static Field checkShipsDamage(Field field, int x, int y){
        Ship[] ships = field.getShips();
        int shipCount = field.getShipCount();
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
                                field.setAllShipsSink(true);
                                System.out.println("You sank the last ship. You won. Congratulations!\n");
                            } else {
                                System.out.println("You sank a ship! Specify a new target:\n");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                    }
                }
            }
        }
        field.setShipCount(shipCount);
        field.setShips(ships);
        return field;
    }



}
