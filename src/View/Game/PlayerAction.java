package View.Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import Data.AppColor;
import Data.AppConstants;
import Model.Pokemon;
import View.Share.RoundPanel;

public class PlayerAction extends RoundPanel {
    private RoundPanel mainPanel;

    private Pokemon pokemon;

    public PlayerAction() {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

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
    }

    void viewBasedOnPoke() {
        mainPanel.removeAll();
        if (pokemon == null) {
            mainPanel.add(new JLabel("1. Fight"));
            mainPanel.add(new JLabel("2. Run"));
            mainPanel.add(new JLabel("3. Change"));
        }
    }
}