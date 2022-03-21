package battleship.ships;

public abstract class Ship {

    String shipName;
    int size;       //indicates the size of the occupying cells
    int damage = 0;     //indicates how many cells is left
    boolean status = false;  //indicates whether the ship is in the field or not
    boolean destroy = isDestroy(size, damage); //indicates whether ship destroy or not
    String[] coordinates;

    //CHECK FOR DESTROY
    boolean isDestroy (int size, int damage){
        if(damage == size) {
            return true;
        } else return false;
    }

    //PRINT MESSAGE FOR ENTERING COORDINATES
    public void printPutMessage(){}

    //PRINT ERROR MESSAGE: ship too close to another one
    public void printErrorToClose (){
        System.out.println("Error! You placed it too close to another one. Try again:");
    }

    //PRINT ERROR MESSAGE: wrong ship location
    public void printErrorLocation (){
        System.out.println("Error! Wrong ship location! Try again:");
    }

    //PRINT ERROR MESSAGE: wrong length of ship
    public void printErrorWrongLength () {
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

    public String[] getCoordinates() {
        return coordinates;
    }

    // !!!! need to add verification of input coordinates !!!!
    public void setCoordinates(String begin, String end) {
        coordinates[0] = begin;
        coordinates[coordinates.length-1] = end;
    }
}
