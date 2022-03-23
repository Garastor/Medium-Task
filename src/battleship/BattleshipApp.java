package battleship;

import battleship.service.GameService;

public class BattleshipApp {

    public static void main(String[] args) {

        GameService game = new GameService();
        game.startGame();

    }
}
