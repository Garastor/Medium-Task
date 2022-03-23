package battleship.service;

import battleship.entity.Field;
import battleship.entity.Player;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameService {

    Player player1;
    Player player2;
    boolean endGame = false;

    Player prepareToGame (int playerNumber){
        Player player;
        System.out.printf("Player %d, place your ships on the game field\n", playerNumber);
        player = PlayerSrvice.initPlayer(playerNumber);
        System.out.println("Press Enter and pass the move to another player");
        return player;
    }

    public void startGame (){
        Scanner sc = new Scanner(System.in);
        player1=prepareToGame(1);
        if(Objects.equals(sc.nextLine(), "")){
            player2 = prepareToGame(2);
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

    //METHOD IS CALCULATING SHOTS (MISSED AND SUCCESS)
    void move(Player attackPlayer, Player defencePlayer) {
        boolean endMove = false;
        Field publicField = defencePlayer.getPublicField();
        Field privateField = defencePlayer.getPrivateField();
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[A-J]+10|[A-J]+[1-9]", Pattern.CASE_INSENSITIVE);
        System.out.println(" ");
        FieldService.printField(publicField);
        System.out.println("---------------------");
        FieldService.printField(attackPlayer.getPrivateField());
        System.out.printf("\nPlayer %d, it's your turn:\n\n", attackPlayer.getPlayerNumber());
        String shot = sc.next();
        System.out.println(" ");
        Matcher shotMatcher = pattern.matcher(shot);
        while (!endMove) {
            if (shotMatcher.matches()) {
                int x = ShipService.getX(shot);
                int y = ShipService.getY(shot);

                if (privateField.getGrid()[y][x] == "O") {
                    privateField.getGrid()[y][x] = "X";
                    publicField.getGrid()[y][x] = "X";
                    defencePlayer.setPrivateField(FieldService.checkShipsDamage(privateField, x, y));
                } else if (privateField.getGrid()[y][x] == "X") {
                    System.out.println("You hit a ship!");
                } else {
                    privateField.getGrid()[y][x] = "M";
                    publicField.getGrid()[y][x] = "M";
                    System.out.println("You missed!");
                }
            } else {
                System.out.println("Error! You entered wrong coordinates! Try again:\n");
            }
            defencePlayer.setPrivateField(privateField);
            endMove = true;
        }
        if(defencePlayer.getPrivateField().isAllShipsSink()){
            endGame = true;
        }
    }

}
