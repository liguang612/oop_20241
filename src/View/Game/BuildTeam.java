package View.Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Data.AppConstants;

public class BuildTeam extends JLayeredPane {
    private JPanel mainPanel;
    private SpringLayout layout;
    private PokeSelection pokeSelection;

    public BuildTeam() {
        super();

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("../../assets/img/game_background.png")));
        background.setBounds(0, 0, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        add(background, JLayeredPane.DEFAULT_LAYER);

        mainPanel = new JPanel(layout = new SpringLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, AppConstants.SCREEN_WIDTH - 12, AppConstants.SCREEN_HEIGHT - 40);

        pokeSelection = new PokeSelection();
        mainPanel.add(pokeSelection);
        layout.putConstraint(SpringLayout.NORTH, pokeSelection, 0, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, pokeSelection, 0, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.EAST, pokeSelection, 0, SpringLayout.EAST, mainPanel);

        add(mainPanel, JLayeredPane.PALETTE_LAYER);
    }
}
