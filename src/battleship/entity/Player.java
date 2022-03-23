package battleship.entity;

public class Player {

    private Field privateField;
    private Field publicField;
    private int playerNumber;

    Player (int playerNumber){
        this.playerNumber = playerNumber;
    }

    public Player() {
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

    public void setPrivateField(Field privateField) {
        this.privateField = privateField;
    }

    public void setPublicField(Field publicField) {
        this.publicField = publicField;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
