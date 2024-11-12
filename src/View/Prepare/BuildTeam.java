package View.Prepare;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Data.AppConstants;
import Model.Pokemon;

public class BuildTeam extends JLayeredPane {
    private JPanel mainPanel;
    private SpringLayout layout;
    private PokeDetail pokeDetail;
    private PokeSelection pokeSelection;

    public BuildTeam() {
        super();

        JLabel background = new JLabel(AppConstants.IMG_BACKGROUND1);
        background.setBounds(0, 0, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);
        add(background, JLayeredPane.DEFAULT_LAYER);

        mainPanel = new JPanel(layout = new SpringLayout());
        mainPanel.setBounds(0, 0, AppConstants.SCREEN_WIDTH - 12, AppConstants.SCREEN_HEIGHT - 40);
        mainPanel.setFocusable(true);
        mainPanel.setOpaque(false);

        pokeSelection = new PokeSelection(this);
        mainPanel.add(pokeSelection);
        layout.putConstraint(SpringLayout.NORTH, pokeSelection, 0, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, pokeSelection, 0, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.EAST, pokeSelection, 0, SpringLayout.EAST, mainPanel);

        pokeDetail = new PokeDetail(AppConstants.BULBASAUR);
        mainPanel.add(pokeDetail);
        layout.putConstraint(SpringLayout.NORTH, pokeDetail, 8, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, pokeDetail, -150, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, pokeDetail, 6, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.EAST, pokeDetail, -8, SpringLayout.WEST, pokeSelection);

        add(mainPanel, JLayeredPane.PALETTE_LAYER);

        SwingUtilities.invokeLater(() -> {
            pokeSelection.requestFocusInWindow();
        });
    }

    protected void changePokemon(Pokemon pokemon) {
        pokeDetail.setPokemon(pokemon);
    }

    public void hideFilterPopup(JPanel popup) {
        mainPanel.remove(popup);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showFilterPopup(JPanel popup) {
        mainPanel.add(popup);
        layout.putConstraint(SpringLayout.NORTH, popup, 8, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.EAST, popup, 0, SpringLayout.WEST, pokeSelection);

        mainPanel.revalidate();
        mainPanel.repaint();

        popup.requestFocus();
    }
}
