package View.Game;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Controller.GameController;
import Data.AppConstants;

public class BattleLayer extends JPanel {
    private SpringLayout layout = new SpringLayout();

    public BattleLayer(GameController controller) {
        super();

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

        revalidate();
        repaint();
    }
}
