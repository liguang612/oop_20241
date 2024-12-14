package Controller;

import java.awt.Component;
import java.util.List;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Data.AppConstants;
import Data.AppConstants.GameState;
import Model.Pokemon;
import Model.Skill;
import Utils.Utils;
import View.Game.BattleGround;
import View.Game.BattleLayer;
import View.Game.ChangePokemon;
import View.Game.Game;
import View.Game.Story;
import View.Game.Widget.PlayerAction;

public class GameController {
    private Game game;

    // PALETTE LAYER
    private BattleLayer battle;
    private BattleGround ground;
    private ChangePokemon changePoke;
    private Story story;
    private PlayerAction playerActions;

    private Integer gameLayerLevel = JLayeredPane.DEFAULT_LAYER;

    // MODEL
    private List<Pokemon> ourPokemons;
    private Pokemon ally, enemy;

    // LAYOUT
    private SpringLayout layout = new SpringLayout();

    // DATA
    private GameState state;
    private int level = 1;

    public GameController(List<Pokemon> pokemons) {
        // Data
        ourPokemons = pokemons;

        // UI
        game = new Game(this);

        initMatch();

        story = new Story(this);

        ground = new BattleGround(this);
        ground.setDirection(BattleGround.Direction.allMoveIn);

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
        if (ally == null)
            ally = ourPokemons.get(Utils.random(ourPokemons.size()));
        enemy = AppConstants.ALL_OF_POKEMONS.get(Utils.random(AppConstants.ALL_OF_POKEMONS.size())).clone();

        logic();
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
            case run:
                return "Are you sure to escape the battle?";
            case escape:
                return "You got away safely";
            default:
                return "";
        }
    }

    // STATE
    public GameState back() {
        switch (state) {
            case attack:
            case skills:
            case run:
            case change:
                state = GameState.action;

                playerActions.setMode(0);
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

                playerActions.setVisible(true);
                playerActions.setMode(0);
                playerActions.requestFocusInWindow();

                break;
            case action:
                if (playerActions.option == 0) {
                    state = GameState.skills;
                    playerActions.setMode(ally);
                } else if (playerActions.option == 1) {
                    state = GameState.run;
                    playerActions.setMode(2);
                } else if (playerActions.option == 2) {
                    state = GameState.change;
                    changePoke = new ChangePokemon(this);
                    playerActions.setMode(3);
                }

                break;
            case run:
                if (playerActions.option == 0) {
                    state = GameState.escape;

                    ground.setDirection(BattleGround.Direction.enemyMoveOut);
                    playerActions.setVisible(false);

                    level++;
                } else {
                    back();
                }

                break;
            case escape:
                state = GameState.init;

                initMatch();
                ground.setDirection(BattleGround.Direction.enemyMoveIn);
                level++;

                break;
            case change:
                ally = changePoke.getPokemon();
                ground.setDirection(null);
                back();

                break;
            case skills:
                state = GameState.attack;

                int cost, damage;
                Skill skill;

                // Select skill
                if (playerActions.option < 3) {
                    // Ally attacks enemy
                    skill = ally.getSkills()[playerActions.option];
                    cost = skill.getCost();
                    damage = skill.getDamage();

                    if (ally.getMana() >= cost) {
                        enemy.setHpLeft((int) (enemy.getHpLeft() - damage / Utils.log7(enemy.getArmor())));
                        ally.setMana(ally.getMana() - cost);

                        sendMessage("You have just used " + skill.getName() + "!");
                        return state;
                    } else {
                        sendMessage("You don't have enough mana to use this skill");
                        return state;
                    }
                }
                // Skip
                else {
                    sendMessage("You've skipped this turn!");
                    return state;
                }
            case attack:
                state = GameState.skills;

                // Enemy attacks ally
                skill = enemy.getSkills()[Utils.random(3)];
                // Attempt
                if (skill.getCost() > enemy.getMana()) {
                    for (Skill s : enemy.getSkills()) {
                        if (s.getCost() <= enemy.getMana()) {
                            skill = s;
                            break;
                        }
                    }
                }
                // Attempt failed
                if (skill.getCost() > enemy.getMana()) {
                    sendMessage("Enemy didn't do anything...");
                    return state;
                }
                cost = skill.getCost();
                damage = skill.getDamage();

                ally.setHpLeft((int) (ally.getHpLeft() - damage / Utils.log7(ally.getArmor())));
                enemy.setMana(enemy.getMana() - skill.getCost());

                sendMessage(enemy.getName() + " has just use " + skill.getName() + "!");

                return state;
            default:
                break;
        }

        sendMessage(state);
        return state;
    }

    // LOGIC
    void logic() {
        enemy.setHp((level - 1) / 3 * 20 + 40);
        enemy.setArmor((level - 1) / 3 * 10);
    }

    // GETTER
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

    public ChangePokemon getChangePoke() {
        return this.changePoke;
    }
}
