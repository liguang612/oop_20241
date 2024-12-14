package View.Game.Widget;

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
import View.Game.ChangePokemon;
import View.Share.RoundPanel;

public class PlayerAction extends RoundPanel implements KeyListener {
    // Controller
    private GameController controller;

    // UI
    private RoundPanel mainPanel;

    // Model
    private Pokemon pokemon;

    // Data
    /// Mode mapping: 0 -> actions, 1 -> skills, 2 -> Yes/No
    public int mode = 0, option = -1;

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

        initView();

        addKeyListener(this);
    }

    // UI
    void initView() {
        mainPanel.removeAll();
        if (mode == 0) {
            mainPanel.add(new JLabel("Fight"));
            mainPanel.add(new JLabel("Run"));
            mainPanel.add(new JLabel("Change"));
        } else if (mode == 1) {
            JLabel sL;
            for (Skill skill : pokemon.getSkills()) {
                sL = new JLabel(skill.getName());
                sL.setForeground(skill.getType().getColor());

                mainPanel.add(sL);
            }
            sL = new JLabel("Skip");
            sL.setForeground(AppColor.white);

            mainPanel.add(sL);
        } else if (mode == 2) {
            mainPanel.add(new JLabel("Yes"));
            mainPanel.add(new JLabel("No"));
        } else if (mode == 3) {
            mainPanel.add(controller.getChangePoke());
        }

        revalidate();
        repaint();
    }

    // Logical
    private void movePointer(int step) {
        if (mode == 3) {
            controller.getChangePoke().next();
            return;
        }

        if (option >= 0) {
            JLabel old = (JLabel) mainPanel.getComponent(option);
            old.setIcon(null);
        }

        // Identify the number of options for ...
        int numOfOptions = 1;
        switch (mode) {
            case 0:
                numOfOptions = 3;
                break;
            case 1:
                numOfOptions = pokemon.getSkills().length + 1;
                break;
            case 2:
                numOfOptions = 2;
                break;
            default:
                break;
        }

        // ... calculating pointer
        option = (option + step) % numOfOptions;

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

    public void setMode(int mode) {
        this.mode = mode;
        initView();
    }

    public void setMode(Pokemon pokemon) {
        this.pokemon = pokemon;
        mode = 1;
        option = -1;
        initView();
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