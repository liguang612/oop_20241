package Controller;

import View.Game.Game;

public class GameController {
    private Game game;

    public GameController() {
        game = new Game();
    }

    public Game getGame() {
        return game;
    }
}
