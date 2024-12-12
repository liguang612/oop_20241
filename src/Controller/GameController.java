package Controller;

import java.awt.Component;
import java.util.List;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;

import Data.AppConstants;
import Data.AppConstants.GameState;
import Model.Pokemon;
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

    // POKE
    private List<Pokemon> ourPokemons;
    private Pokemon ally, enemy;

    // LAYOUT
    private SpringLayout layout = new SpringLayout();

    // PROGRESS
    private GameState state;

    public GameController(List<Pokemon> pokemons) {
        ourPokemons = pokemons;

        game = new Game(this);

        initMatch();

        story = new Story(this);
        ground = new BattleGround(this);
        battle = new BattleLayer(this);

        addGameLayer(battle);
    }

    public void addGameLayer(Component comp) {
        ((JLayeredPane) game.getContentPane()).add(comp, gameLayerLevel += 100);
    }

    private void initMatch() {
        Random rand = new Random();

        ally = ourPokemons.get(rand.nextInt(ourPokemons.size()));
        enemy = AppConstants.ALL_OF_POKEMONS.get(rand.nextInt(AppConstants.ALL_OF_POKEMONS.size()));
    }

    public void sendMessage(GameState state) {
        this.state = state;
        story.receiveMsg(genMessage(state));
    }

    private String genMessage(GameState state) {
        switch (state) {
            case init:
                return ally.getName() + " .vs " + enemy.getName();
            case prepare:
                return "What will " + ally.getName() + " do?";
            default:
                return "";
        }
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

    public List<Pokemon> getOurPokemons() {
        return ourPokemons;
    }

    public Pokemon getAlly() {
        return ally;
    }

    public Pokemon getEnemy() {
        return enemy;
    }

    public SpringLayout getLayout() {
        return layout;
    }

    public GameState getState() {
        return state;
    }
}
