package battleship;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();

        field.printField();

        field.prepareField();
        System.out.println(field.ships[0].isStatus());
    }
}
