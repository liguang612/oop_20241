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
import View.Game.PlayerAction;
import View.Game.Story;

public class GameController {
    private Game game;

    // PALETTE LAYER
    private BattleLayer battle;
    private BattleGround ground;
    private Story story;
    private PlayerAction playerActions;

    private Integer gameLayerLevel = JLayeredPane.DEFAULT_LAYER;

    // POKE
    private List<Pokemon> ourPokemons;
    private Pokemon ally, enemy;

    // LAYOUT
    private SpringLayout layout = new SpringLayout();

    // PROGRESS
    private GameState state;

    public GameController(List<Pokemon> pokemons) {
        // Data
        ourPokemons = pokemons;

        // UI
        game = new Game(this);

        initMatch();

        story = new Story(this);

        ground = new BattleGround(this);

        playerActions = new PlayerAction(this);
        playerActions.setVisible(false);

        battle = new BattleLayer(this);

        addGameLayer(battle);

        // State
        state = GameState.init;
    }

    // UI
    public void addGameLayer(Component comp) {
        ((JLayeredPane) game.getContentPane()).add(comp, gameLayerLevel += 100);
    }

    private void initMatch() {
        Random rand = new Random();

        ally = ourPokemons.get(rand.nextInt(ourPokemons.size()));
        enemy = AppConstants.ALL_OF_POKEMONS.get(rand.nextInt(AppConstants.ALL_OF_POKEMONS.size()));
    }

    // MESSAGE
    public void sendMessage(GameState state) {
        this.state = state;
        story.receiveMsg(genMessage(state));
    }

    public void sendMessage(String msg) {
        story.receiveMsg(msg);
    }

    private String genMessage(GameState state) {
        switch (state) {
            case init:
                return ally.getName() + " .vs " + enemy.getName();
            case action:
                return "What will " + ally.getName() + " do?";
            case skills:
                return "Choose skills";
            default:
                return "";
        }
    }

    // STATE
    public GameState back() {
        switch (state) {
            case skills:
            case run:
            case change:
                state = GameState.action;

                playerActions.setPokemon(null);
                sendMessage(state);

                break;
            default:
                break;
        }

        return state;
    }

    public GameState next() {
        switch (state) {
            case init:
                state = GameState.action;
                sendMessage(state);

                playerActions.setVisible(true);
                playerActions.requestFocusInWindow();

                break;
            case action:
                if (playerActions.option == 0) {
                    state = GameState.skills;
                    playerActions.setPokemon(ally);
                } else if (playerActions.option == 1) {
                    state = GameState.run;
                } else if (playerActions.option == 2) {
                    state = GameState.change;
                }

                sendMessage(state);

                break;
            default:
                break;
        }
        return state;
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

    public PlayerAction getPlayerActions() {
        return playerActions;
    }
}
