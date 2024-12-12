package View.Game;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Controller.GameController;
import Data.AppConstants;
import View.Game.Widget.PlayerAction;

public class BattleLayer extends JPanel {
    private SpringLayout layout = new SpringLayout();

    public BattleLayer(GameController controller) {
        super();

        setFocusable(true);
        setLayout(layout);
        setOpaque(false);
        setSize(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);

        Story story = controller.getStory();
        add(story);
        layout.putConstraint(SpringLayout.NORTH, story, -260, SpringLayout.SOUTH, story);
        layout.putConstraint(SpringLayout.SOUTH, story, -40, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, story, -20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, story, 4, SpringLayout.EAST, this);

        BattleGround ground = controller.getGround();
        add(ground);
        layout.putConstraint(SpringLayout.NORTH, ground, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, ground, 0, SpringLayout.NORTH, story);
        layout.putConstraint(SpringLayout.WEST, ground, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, ground, 0, SpringLayout.EAST, this);

        PlayerAction actions = controller.getPlayerActions();
        add(actions);
        layout.putConstraint(SpringLayout.NORTH, actions, 0, SpringLayout.NORTH, story);
        layout.putConstraint(SpringLayout.SOUTH, actions, 0, SpringLayout.SOUTH, story);
        layout.putConstraint(SpringLayout.EAST, actions, -20, SpringLayout.EAST, story);
        layout.putConstraint(SpringLayout.WEST, actions, 1093, SpringLayout.WEST, story);
        setComponentZOrder(actions, 0);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                controller.getStory().requestFocusInWindow();
            }
        });

        revalidate();
        repaint();
    }
}
