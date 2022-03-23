package battleship.entity;

public class Ship {

    private String name;
    private int size;                       //indicates the number of the occupying cells
    private boolean status;         //indicates whether the ship is placed on the field or not
    private boolean horizontal;             //indicates ship position (horizontal or not)
    private int[][] coordinates;            //row[0] - x axis;    row[1] - y axis
    private int hitPoints;           //if all hitPoints disappear, the ship will sink
    private boolean destroy;        //indicates whether ship destroy or not

    public Ship (String name, int size){
        this.name = name;
        this.size = size;
    }

    //GETTERS AND SETTERS
    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
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

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public String getName() {
        return name;
    }
}
