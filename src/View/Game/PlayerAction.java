package View.Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import Controller.GameController;
import Data.AppColor;
import Data.AppConstants;
import Data.AppConstants.GameState;
import Model.Pokemon;
import Model.Skill;
import View.Share.RoundPanel;

public class PlayerAction extends RoundPanel implements KeyListener {
    private GameController controller;

    private RoundPanel mainPanel;

    private Pokemon pokemon;

    public int option = -1;

    public PlayerAction(GameController controller) {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
        this.controller = controller;

        SpringLayout layout = new SpringLayout();
        setFocusable(true);
        setLayout(layout);

        mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10));

        add(mainPanel);
        layout.putConstraint(SpringLayout.NORTH, mainPanel, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, mainPanel, -20, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, mainPanel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, mainPanel, -20, SpringLayout.EAST, this);

        viewBasedOnPoke();

        addKeyListener(this);
    }

    void viewBasedOnPoke() {
        mainPanel.removeAll();
        if (pokemon == null) {
            mainPanel.add(new JLabel("Fight"));
            mainPanel.add(new JLabel("Run"));
            mainPanel.add(new JLabel("Change"));
        } else {
            for (Skill skill : pokemon.getSkills()) {
                JLabel sL = new JLabel(skill.getName());
                sL.setForeground(skill.getType().getColor());

                mainPanel.add(sL);
            }
        }
    }

    private void movePointer(int step) {
        if (option >= 0) {
            JLabel old = (JLabel) mainPanel.getComponent(option);
            old.setIcon(null);
        }

        option = (option + step) % 3;

        JLabel _new = (JLabel) mainPanel.getComponent(option);
        _new.setIcon(AppConstants.IMG_CURSOR);

        if (controller.getState() == GameState.action) {
            switch (option) {
                case 0:
                    controller.sendMessage("Ready to fight!");
                    break;
                case 1:
                    controller.sendMessage("Skip this challenge safety");
                    break;
                case 2:
                    controller.sendMessage("Change to the other pokemon in your team");
                default:
                    break;
            }
        }
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        option = -1;
        viewBasedOnPoke();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            movePointer(2);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movePointer(1);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            controller.next();
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            controller.back();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}