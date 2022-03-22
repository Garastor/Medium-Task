package battleship.ships;

import java.beans.beancontext.BeanContext;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Ship {

    String shipName;
    int size;       //indicates the size of the occupying cells
    int damage = 0;     //indicates how many cells is left
    boolean status = false;  //indicates whether the ship is in the field or not
    boolean destroy = isDestroy(size, damage); //indicates whether ship destroy or not
    boolean horizontal; //indicates ship position (horizontal or not)
    int[][] coordinates; // row[0] - x axis; row[1] - y axis

    //CHECK FOR DESTROY
    boolean isDestroy(int size, int damage) {
        if (damage == size) {
            return true;
        } else return false;
    }

    //METHOD ACCEPT COORDINATES (like A1 A4), VERIFY IT AND TRY TO CREATE SHIP
    public void inputCoordinates() {
        boolean isVerified = false;
        while (!isVerified) {
            Scanner sc = new Scanner(System.in);                            //input coordinates
            String begin = sc.next();
            String end = sc.next();
            Pattern pattern = Pattern.compile("[A-J]+10|[A-J]+[1-9]", Pattern.CASE_INSENSITIVE);
            Matcher beginMatcher = pattern.matcher(begin);                  //verify coordinates
            Matcher endMatcher = pattern.matcher(end);
            if (beginMatcher.matches() && endMatcher.matches()) {
                if (!isSortedCoordinates(begin, end)){      // check if "begins" is less than "end" and provide it
                    String temp = begin;
                    begin = end;
                    end = temp;
                }
                if(createShip(begin, end)){
                    isVerified = true;
                }
            }
        }
    }

    //METHOD CREATING COORDINATES FROM FIRST POINT TO END POINT
    public boolean createShip(String begin, String end) {
        int symbolBegin = getY(begin);
        int numberBegin = getX(begin);
        int symbolEnd = getY(end);
        int numberEnd = getX(end);
        if ((numberBegin == numberEnd) && (symbolBegin == symbolEnd)) {     //coordinates is equal?
            printErrorWrongLength();
            return false;
        }
        else if (numberBegin == numberEnd) {                                //numbers is equals (x axis is static)?
            if (((symbolEnd - symbolBegin)+1) == size) {     // test length
                coordinates = setCoordinates('y', begin, end);  // length is OK
                horizontal = false;
                return true;
            } else {
                printErrorWrongLength();                     // error length
                return false;
            }
        }
        else if (symbolBegin == symbolEnd) {                                //symbols is equals (y axis is static)?
            if (((numberEnd - numberBegin)+1) == size) {     // test length
                coordinates = setCoordinates('x', begin, end);  // length is OK
                horizontal = true;
                return true;
            } else {
                printErrorWrongLength();                     // error length
                return false;
            }
        } else {
            printErrorLocation();                                           //coordinates is wrong
            return false;
        }
    }

    //METHOD RETURN Y-COORDINATE (symbols) after converting char to int
    public int getY(String coordinate) {
        return (coordinate.charAt(0) - 64);                          //convert symbol to int ('A'=65, 'B'=66,...)
    }

    //METHOD RETURN X-COORDINATE (numbers)
    public int getX(String coordinate) {
        if(coordinate.length()>2){                                   //return number if it's 10
            return 10;
        } else {
            return Integer.parseInt(String.valueOf(coordinate.charAt(1)));//return number if it's [1-9]
        }
    }

    //METHOD SET ALL COORDINATES FROM FIRST xy TO LAST xy;
    public int [][] setCoordinates (char variableAxis, String begin, String end){
        int [][] coordinates = new int[2][size];
        int aIndex = 1;
        int bIndex = 0;
        int valueFrom = getY(begin);
        int valueTo = getY(end);
        int staticAxis = getX(begin);
        if (variableAxis == 'x'){
            aIndex = 0;
            bIndex = 1;
            valueFrom = getX(begin);
            valueTo = getX(end);
            staticAxis = getY(begin);
        }
        int count = 0;
        for (int i = valueFrom; i<= valueTo; i++){
            coordinates[aIndex][count] = i;
            coordinates[bIndex][count] = staticAxis;
            count++;
        }
        return coordinates;
    }

    //METHOD CHECK IF "BEGIN" COORDINATE IS LESS THAN "END" COORDINATE
    boolean isSortedCoordinates (String begin, String end){
        if(getX(begin) == getX(end)){ //if numbers is equal
            if(getY(begin) > getY(end)){
                return false;
            }
        }
        if (getY(begin) == getY(end)){ ////if symbols is equal
            if(getX(begin)>getX(end)){
                return false;
            }
        }
        return true;
    }

    //PRINT MESSAGE FOR ENTERING COORDINATES
    public void printPutMessage() {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", this.shipName, this.size);
    }

    //PRINT ERROR MESSAGE: ship too close to another one
    public void printErrorToClose() {
        System.out.println("Error! You placed it too close to another one. Try again:");
    }

    //PRINT ERROR MESSAGE: wrong ship location
    public void printErrorLocation() {
        System.out.println("Error! Wrong ship location! Try again:");
    }

    //PRINT ERROR MESSAGE: wrong length of ship
    public void printErrorWrongLength() {
        System.out.printf("Error! Wrong length of the %s! Try again:", this.shipName);
    }

    //GETTERS AND SETTERS
    public int getSize() {
        return size;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public boolean isHorizontal () {
        return horizontal;
    }
}
