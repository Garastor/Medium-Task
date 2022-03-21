package battleship;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();

        field.prepareField();

        for (int [] row : field.ships[0].getCoordinates()){
            for (int  c: row){
                System.out.print(c+" ");
            }
            System.out.println("");
        }

    }
}
