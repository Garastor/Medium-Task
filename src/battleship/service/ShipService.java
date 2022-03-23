package battleship.service;

import battleship.entity.Ship;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShipService {

    Ship ship;

    public ShipService (Ship ship) {
        this.ship = ship;
    }

    //METHOD ACCEPT COORDINATES (like A1 A4), VERIFY IT AND TRY TO CREATE SHIP
    public static Ship createShip(Ship ship) {
        boolean isVerified = false;
        while (!isVerified) {
            Scanner sc = new Scanner(System.in);            //input the coordinates
            String begin = sc.next();
            String end = sc.next();
            Pattern pattern = Pattern.compile("[A-J]+10|[A-J]+[1-9]", Pattern.CASE_INSENSITIVE);
            Matcher beginMatcher = pattern.matcher(begin);                  //verify coordinates
            Matcher endMatcher = pattern.matcher(end);
            if (beginMatcher.matches() && endMatcher.matches()) {
                if (!isSortedCoordinates(begin, end)) {      // check if "begin" is less than "end"
                    String temp = begin;
                    begin = end;
                    end = temp;
                }
                if (checkCoordinates(ship, begin, end)) {
                    isVerified = true;
                }
            }
        }
        return ship;
    }

    //METHOD CREATING COORDINATES FROM FIRST POINT TO END POINT
    public static boolean checkCoordinates(Ship ship, String begin, String end) {
        int symbolBegin = getY(begin);
        int numberBegin = getX(begin);
        int symbolEnd = getY(end);
        int numberEnd = getX(end);
        if ((numberBegin == numberEnd) && (symbolBegin == symbolEnd)) {     //coordinates is equal?
            printErrorWrongLength(ship);
            return false;
        } else if (numberBegin == numberEnd) {                                //numbers is equals (x axis is static)?
            if (((symbolEnd - symbolBegin) + 1) == ship.getSize()) {     // test length
                ship.setCoordinates(parseCoordinates('y', begin, end));  // length is OK
                ship.setHorizontal(false);
                return true;
            } else {
                printErrorWrongLength(ship);                     // error length
                return false;
            }
        } else if (symbolBegin == symbolEnd) {                                //symbols is equals (y axis is static)?
            if (((numberEnd - numberBegin) + 1) == ship.getSize()) {     // test length
                ship.setCoordinates(parseCoordinates('x', begin, end));  // length is OK
                ship.setHorizontal(true);
                return true;
            } else {
                printErrorWrongLength(ship);                     // error length
                return false;
            }
        } else {
            printErrorLocation();                                           //coordinates is wrong
            return false;
        }
    }

    //METHOD RETURN Y-COORDINATE (symbols) after converting char to int
    public static int getY(String coordinate) {
        return (coordinate.charAt(0) - 64);                          //convert symbol to int ('A'=65, 'B'=66,...)
    }

    //METHOD RETURN X-COORDINATE (numbers)
    public static int getX(String coordinate) {
        if (coordinate.length() > 2) {                                   //return number if it's 10
            return 10;
        }
        return Integer.parseInt(String.valueOf(coordinate.charAt(1)));//return number if it's [1-9]
    }

    //METHOD SET ALL COORDINATES FROM FIRST xy TO LAST xy;
    public static int[][] parseCoordinates(char variableAxis, String begin, String end) {
        int aIndex = 1;
        int bIndex = 0;
        int valueFrom = getY(begin);
        int valueTo = getY(end);
        int staticAxis = getX(begin);
        if (variableAxis == 'x') {
            aIndex = 0;
            bIndex = 1;
            valueFrom = getX(begin);
            valueTo = getX(end);
            staticAxis = getY(begin);
        }
        int size = valueTo-valueFrom+1;
        int[][] coordinates = new int[2][size];
        int count = 0;
        for (int i = valueFrom; i <= valueTo; i++) {
            coordinates[aIndex][count] = i;
            coordinates[bIndex][count] = staticAxis;
            count++;
        }
        return coordinates;
    }

    //METHOD CHECK IF "BEGIN" COORDINATE IS LESS THAN "END" COORDINATE
    static boolean isSortedCoordinates(String begin, String end) {
        if (getX(begin) == getX(end)) { //if numbers is equal
            if (getY(begin) > getY(end)) {
                return false;
            }
        }
        if (getY(begin) == getY(end)) { ////if symbols is equal
            if (getX(begin) > getX(end)) {
                return false;
            }
        }
        return true;
    }



    public static void printPutMessage(Ship ship) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getSize());
    }

    public static void printErrorToClose() {
        System.out.println("Error! You placed it too close to another one. Try again:");
    }

    public static void printErrorLocation() {
        System.out.println("Error! Wrong ship location! Try again:");
    }

    public static void printErrorWrongLength(Ship ship) {
        System.out.printf("Error! Wrong length of the %s! Try again:", ship.getName());
    }
}
