package View.Game;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Data.AppConstants;

public class PokeSelection extends JPanel {
    private SpringLayout layout;
    private Filter filter;
    private BuildTeam parent;
    private PokeList pokeList;
    private PokeSelected pokeSelected;

    public PokeSelection(BuildTeam parent) {
        super();
        this.parent = parent;

        setOpaque(false);
        setPreferredSize(new Dimension((int) (AppConstants.SCREEN_WIDTH * 0.65) - 16, AppConstants.SCREEN_HEIGHT - 8));

        layout = new SpringLayout();
        setLayout(layout);

        filter = new Filter(this);
        add(filter);
        layout.putConstraint(SpringLayout.NORTH, filter, 8, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, filter, 4, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, filter, -8, SpringLayout.EAST, this);

        pokeSelected = new PokeSelected(this);
        add(pokeSelected);
        layout.putConstraint(SpringLayout.NORTH, pokeSelected, 4, SpringLayout.SOUTH, filter);
        layout.putConstraint(SpringLayout.SOUTH, pokeSelected, -8, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, pokeSelected, -8, SpringLayout.EAST, this);

        pokeList = new PokeList(this);
        add(pokeList);
        layout.putConstraint(SpringLayout.NORTH, pokeList, 4, SpringLayout.SOUTH, filter);
        layout.putConstraint(SpringLayout.SOUTH, pokeList, -8, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, pokeList, 4, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, pokeList, -8, SpringLayout.WEST, pokeSelected);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                pokeList.requestFocusInWindow();
            }
        });
    }

    protected void hideFilterPopup(JPanel popup) {
        parent.hideFilterPopup(popup);
    }

    protected void showFilterPopup(JPanel popup) {
        parent.showFilterPopup(popup);
    }

    public Filter getFilter() {
        return filter;
    }

    public PokeList getPokeList() {
        return pokeList;
    }

    public PokeSelected getPokeSelected() {
        return pokeSelected;
    }
}