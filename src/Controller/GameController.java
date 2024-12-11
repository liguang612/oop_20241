package Controller;

import java.awt.Component;

import javax.swing.JLayeredPane;

import View.Game.BattleGround;
import View.Game.BattleLayer;
import View.Game.Game;
import View.Game.Story;

public class GameController {
    private Game game;

    // PALETTE LAYER
    private BattleLayer battle;
    private BattleGround ground;
    private Story story;

    private Integer gameLayerLevel = JLayeredPane.DEFAULT_LAYER;

    public GameController() {
        game = new Game(this);

        story = new Story(this);
        ground = new BattleGround(this);
        battle = new BattleLayer(this);

        addGameLayer(battle);
    }

    public void addGameLayer(Component comp) {
        ((JLayeredPane) game.getContentPane()).add(comp, gameLayerLevel += 100);
    }

    public Game getGame() {
        return game;
    }

    public BattleLayer getBattle() {
        return battle;
    }

    public Story getStory() {
        return story;
    }

    public BattleGround getGround() {
        return ground;
    }

    public Integer getGameLayerLevel() {
        return gameLayerLevel;
    }
}
