package battleship;

import battleship.ships.*;

import java.util.Scanner;

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

    //CONSTRUCTOR INIT ALL CELLS OF FIELD
    Field () {
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
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<ships.length; i++){
            if(!ships[i].isStatus()){   //looking for ship with status 'false'
                Ship ship = ships[i];
                ship.printPutMessage();
                while (!ship.isStatus()) { //trying to put the coordinates and check it.
                    String begin = sc.next();
                    String end = sc.nextLine();
                    ship.setCoordinates(begin, end);
                    if (this.checkCoordinates(ship)) { //if coordinates is checked, initializing current ship
                        ship.setStatus(true);
                        ships[i] = ship;
                        printField();
                    }
                }
            }
        }
    }

    public boolean checkCoordinates (Ship ship){ // !!!!!not finished. need continue this method
        return true;
    }

}
