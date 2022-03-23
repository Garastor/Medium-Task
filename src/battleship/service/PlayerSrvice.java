package battleship.service;

import battleship.entity.Field;
import battleship.entity.Player;

public class PlayerSrvice {

    public static Player initPlayer (int playerNumber){
        Player player = new Player();
        Field publicField = FieldService.initField();
        player.setPublicField(publicField);
        Field privateField = FieldService.prepareField();
        player.setPrivateField(privateField);
        player.setPlayerNumber(playerNumber);
        return player;
    }
}
