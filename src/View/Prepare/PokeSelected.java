package View.Prepare;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Data.AppColor;
import Data.AppConstants;
import Model.Pokemon;
import View.Share.AnimatedPanel;
import View.Share.RoundPanel;

@SuppressWarnings("unused")

class PokeSelected extends JPanel {
    private JLabel countLabel;
    private PokeSelection parent;
    private RoundPanel mainSelected, mainTotal, selected, total;
    private SpringLayout layout;

    private int count = 0, totalIV = 0;
    private List<Pokemon> pokemons = new ArrayList<>();

    PokeSelected(PokeSelection parent) {
        this.parent = parent;

        setOpaque(false);
        setLayout(layout = new SpringLayout());
        setPreferredSize(new Dimension((int) (AppConstants.SCREEN_HEIGHT * 0.186),
                (int) (AppConstants.SCREEN_HEIGHT * 0.89)));

        total = new RoundPanel(AppConstants.BORDER_RADIUS, 4, AppColor.black, AppColor.red01);
        total.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        total.setLayout(new GridLayout(1, 1));
        total.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().width));

        add(total);
        layout.putConstraint(SpringLayout.SOUTH, total, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, total, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, total, 0, SpringLayout.EAST, this);

        selected = new RoundPanel(AppConstants.BORDER_RADIUS, 4, AppColor.black, AppColor.red01);
        selected.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        selected.setLayout(new GridLayout(1, 1));

        add(selected);
        layout.putConstraint(SpringLayout.NORTH, selected, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, selected, 0, SpringLayout.NORTH, total);
        layout.putConstraint(SpringLayout.WEST, selected, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, selected, 0, SpringLayout.EAST, this);

        mainSelected = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
        mainSelected.setLayout(new GridLayout(6, 1));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        mainSelected.add(new AnimatedPanel(AppConstants.IMG_UNKNOWN_POKE));
        selected.add(mainSelected);

        mainTotal = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
        mainTotal.setLayout(new BoxLayout(mainTotal, BoxLayout.Y_AXIS));
        total.add(mainTotal);

        countLabel = new JLabel("" + totalIV + "/10", JLabel.CENTER);

        Box box = Box.createVerticalBox();
        box.setAlignmentX(Box.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(countLabel);
        box.add(new JLabel("Total", JLabel.CENTER));
        box.add(Box.createVerticalGlue());
        mainTotal.add(box);
    }

    protected boolean selectPoke(int index) {
        Pokemon pokemon = AppConstants.ALL_OF_POKEMONS.get(index);
        if (pokemon.getIVs() + totalIV > 10)
            return false;

        ((AnimatedPanel) mainSelected.getComponent(pokemons.size())).setImg(pokemon.getAvatar());

        totalIV += pokemon.getIVs();
        countLabel.setText("" + totalIV + "/10");

        pokemons.add(pokemon);

        mainSelected.revalidate();
        mainSelected.repaint();

        return true;
    }

    protected void unselectPoke(int index) {
        Pokemon pokemon = AppConstants.ALL_OF_POKEMONS.get(index);
        int idx = pokemons.indexOf(pokemon);

        AnimatedPanel ap = ((AnimatedPanel) mainSelected.getComponent(idx));
        ap.setImg(AppConstants.IMG_UNKNOWN_POKE);

        mainSelected.remove(idx);
        mainSelected.add(ap);

        totalIV -= pokemon.getIVs();
        countLabel.setText("" + totalIV + "/10");

        pokemons.remove(idx);
    }

    public int getTotalIV() {
        return totalIV;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
