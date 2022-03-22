package battleship;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayGame {

    Player player1 = new Player(1);
    Player player2 = new Player(2);
    boolean endGame = false;

    void startGame (){
        Scanner sc = new Scanner(System.in);
        prepareGame(player1);
        System.out.println("Press Enter and pass the move to another player");
        if(Objects.equals(sc.nextLine(), "")){
            prepareGame(player2);
            System.out.println("Press Enter and pass the move to another player");
        }
        while (!endGame) {
            if (Objects.equals(sc.nextLine(), "")) {
                move(player1, player2);
                System.out.println("Press Enter and pass the move to another player");
            }
            if (Objects.equals(sc.nextLine(), "")) {
                move(player2, player1);
                System.out.println("Press Enter and pass the move to another player");
            }
        }

    }

    //METHOD PREPARE PLAYER'S FIELD
    void prepareGame (Player player) {
        System.out.printf("Player %d, place your ships on the game field\n", player.getPlayerNumber());
        player.privateField.prepareField();
    }

    //METHOD IS CALCULATING SHOTS (MISSED AND SUCCESS)
    void move(Player attackPlayer, Player defencePlayer) {
        boolean endMove = false;
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[A-J]+10|[A-J]+[1-9]", Pattern.CASE_INSENSITIVE);

        System.out.println(" ");
        defencePlayer.getPublicField().printField();
        System.out.println("---------------------");
        attackPlayer.getPrivateField().printField();
        System.out.printf("\nPlayer %d, it's your turn:\n\n", attackPlayer.getPlayerNumber());
        String shot = sc.next();
        System.out.println(" ");
        Matcher shotMatcher = pattern.matcher(shot);
        while (!endMove) {
            if (shotMatcher.matches()) {
                int x = Integer.parseInt(String.valueOf(shot.charAt(1))); // number
                if (shot.toCharArray().length > 2) {
                    x = 10;
                }
                int y = (shot.charAt(0) - 64);                            //symbol

                if (defencePlayer.privateField.getGrid()[y][x] == "O") {
                    defencePlayer.privateField.getGrid()[y][x] = "X";
                    defencePlayer.publicField.getGrid()[y][x] = "X";
                    if (!defencePlayer.privateField.checkShip(x, y)) {
                        System.out.println("You hit a ship!");
                    }
                } else if (defencePlayer.privateField.getGrid()[y][x] == "X") {
                    defencePlayer.publicField.printField();
                    System.out.println("You hit a ship!");
                } else {
                    defencePlayer.privateField.getGrid()[y][x] = "M";
                    defencePlayer.publicField.getGrid()[y][x] = "M";
                    System.out.println("You missed!");
                }
            } else {
                System.out.println("Error! You entered wrong coordinates! Try again:\n");
            }
            endMove = true;
        }
        if(defencePlayer.privateField.isAllShipsSink()){
            endGame = true;
        }
    }

}
