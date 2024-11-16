package View.Prepare;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Controller.PrepareController;
import Data.AppConstants;
import Model.Pokemon;

public class PokeSelection extends JPanel {
    private PrepareController controller;
    private SpringLayout layout;

    public PokeSelection(PrepareController controller) {
        super();
        this.controller = controller;

        setOpaque(false);
        setPreferredSize(new Dimension((int) (AppConstants.SCREEN_WIDTH * 0.65) - 16, AppConstants.SCREEN_HEIGHT - 8));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                controller.getPokeList().requestFocusInWindow();
            }
        });
    }

    public void init() {
        layout = new SpringLayout();
        setLayout(layout);

        Filter filter = controller.getFilter();
        add(filter);
        layout.putConstraint(SpringLayout.NORTH, filter, 8, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, filter, 4, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, filter, -8, SpringLayout.EAST, this);

        PokeSelected pokeSelected = controller.getPokeSelected();
        add(pokeSelected);
        layout.putConstraint(SpringLayout.NORTH, pokeSelected, 4, SpringLayout.SOUTH, filter);
        layout.putConstraint(SpringLayout.SOUTH, pokeSelected, -8, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, pokeSelected, -8, SpringLayout.EAST, this);

        PokeList pokeList = controller.getPokeList();
        add(pokeList);
        layout.putConstraint(SpringLayout.NORTH, pokeList, 4, SpringLayout.SOUTH, filter);
        layout.putConstraint(SpringLayout.SOUTH, pokeList, -8, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, pokeList, 4, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, pokeList, -8, SpringLayout.WEST, pokeSelected);
    }

    void changePokemon(Pokemon pokemon) {
        controller.changePokemon(pokemon);
    }

    void hideFilterPopup(JPanel popup) {
        controller.hideFilterPopup(popup);
    }

    void showFilterPopup(JPanel popup) {
        controller.showFilterPopup(popup);
    }
}