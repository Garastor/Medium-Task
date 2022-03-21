package battleship;

import battleship.ships.*;

import java.util.Objects;

public class Field {

    //INIT FIELD
    String [][] grid = new String [11][11];

    //INIT LIST OF SHIPS
    Ship[] ships = {
            new AircraftCarrier(),
            new Battleship(),
            new Cruiser(),
            new Submarine(),
            new Destroyer()
    };

    //CONSTRUCTOR
    Field() {
        initField();
    }

    //METHOD INIT ALL CELLS OF FIELD
    void initField (){
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
    public void printField (){
        for (String[] row: grid){
            for (String symbol: row){
                System.out.print(symbol+ " ");
            }
            System.out.print("\n");
        }
    }

    //METHOD PUT ALL SHIPS ON THE FIELD
    public void prepareField (){
        printField();
        for (int i=0; i<ships.length; i++){
            if(!ships[i].isStatus()){   //looking for ship with status 'false'
                Ship ship = ships[i];
                ship.printPutMessage();
                while (!ship.isStatus()) {     //is status false
                    ship.inputCoordinates();   //put the coordinates
                    if (checkShipLocation(ship)) { //if ship location is checked, do initialize the current ship
                        ship.setStatus(true);
                        ships[i] = ship;
                        printField();
                    }
                }
            }
        }
    }

    public boolean checkShipLocation(Ship ship){ // !!!!!not finished. need continue this method
        return true;
    }

    boolean isCellEmpty (int x, int y) {
        return Objects.equals(grid[x][y], "~");
    }

    boolean checkLines (Ship ship){
        int[][] shipCoordinates = ship.getCoordinates();
        int[][] centerLine = new int[2][ship.getSize()+2];
        int[][] leftLine = new int[2][ship.getSize()+2];
        int[][] rightLine = new int[2][ship.getSize()+2];


        return true;
    }

    //METHOD CREATE LINE-AREA AROUND SHIP FOR CHECK IT SPACE
    //index is shifted static line to the left or to the right relatively from central position
    int[][] getArea (Ship ship, int lineIndex){  //indexes: center0; left-1; right+1
        int[][] shipCoordinates = ship.getCoordinates();
        int [][] area = new int[2][shipCoordinates[0].length+2];
        int rowA = 1;                   //Numbers is equal
        int rowB = 0;

        if(ship.isHorizontal()) {       //Symbols is equal
            rowA = 0;
            rowB = 1;
        }

        if((shipCoordinates[rowA][0]-1) < 1 ){         //check out of bounds - min
            area[rowA][0] = shipCoordinates[rowA][0];  //variable axis - first element
        } else {
            area[rowA][0] = shipCoordinates[rowA][0]-1;
        }
        if((shipCoordinates[rowA][ship.getSize()-1]+1) > 10){                   ////check out of bounds - max
            area[rowA][area.length-1] = shipCoordinates[rowA][ship.getSize()-1];//variable axis - last element
        } else {
            area[rowA][area.length-1] = shipCoordinates[rowA][ship.getSize()-1]+1;
        }

        for(int i=0; i==ship.getSize()+1; i++){
            area[rowB][i] = shipCoordinates[rowB][0]+lineIndex;    //static axis - all elements
            while (i<ship.getSize())
                area[rowA][i+1] = shipCoordinates[rowA][i];        //variable axis - all left elements
        }
        return area;
    }
}
