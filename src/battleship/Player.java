package battleship;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    Field privateField = new Field();
    Field publicField = new Field();
    int playerNumber;

    Player (int playerNumber){
        this.playerNumber = playerNumber;
    }

    public Field getPrivateField() {
        return privateField;
    }

    public Field getPublicField() {
        return publicField;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
