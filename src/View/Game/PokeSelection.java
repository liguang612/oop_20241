package View.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Data.AppColor;
import Data.AppConstants;
import View.Share.RoundPanel;

public class PokeSelection extends JPanel {
    private SpringLayout layout;
    private Filter filter;
    private PokeList pokeList;
    private PokeSelected pokeSelected;

    public PokeSelection() {
        super();

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
    }

    class Filter extends RoundPanel {
        private int mode = -1;
        private JLabel gen, type, caught, unlocks, misc, sort;
        private PokeSelection parent;
        private RoundPanel mainPanel;

        public Filter(PokeSelection parent) {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            this.parent = parent;

            setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension((int) (parent.getPreferredSize().width),
                    (int) (parent.getPreferredSize().height * 0.105)));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setLayout(new GridLayout(1, 6));
            add(mainPanel);

            gen = new JLabel("Gen", JLabel.CENTER);
            gen.setForeground(Color.WHITE);
            gen.setFont(gen.getFont().deriveFont(32.0f));
            mainPanel.add(gen);

            type = new JLabel("Type", JLabel.CENTER);
            type.setForeground(gen.getBackground());
            type.setFont(gen.getFont());
            mainPanel.add(type);

            caught = new JLabel("Caught", JLabel.CENTER);
            caught.setForeground(gen.getBackground());
            caught.setFont(gen.getFont());
            mainPanel.add(caught);

            unlocks = new JLabel("Unlocks", JLabel.CENTER);
            unlocks.setForeground(gen.getForeground());
            unlocks.setFont(gen.getFont());
            mainPanel.add(unlocks);

            misc = new JLabel("Misc", JLabel.CENTER);
            misc.setForeground(gen.getForeground());
            misc.setFont(gen.getFont());
            mainPanel.add(misc);

            sort = new JLabel("Sort", JLabel.CENTER);
            sort.setForeground(gen.getForeground());
            sort.setFont(gen.getFont());
            mainPanel.add(sort);
        }
    }

    class PokeList extends RoundPanel {
        private PokeSelection parent;

        PokeList(PokeSelection parent) {
            super(AppConstants.BORDER_RADIUS, 8, AppColor.gray01, AppColor.gray02);

            this.parent = parent;

            setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            setLayout(new GridLayout(8, 9));

            add(new JLabel("LABEL"));
        }
    }

    class PokeSelected extends JPanel {
        private int count = 0;
        private JLabel countLabel;
        private PokeSelection parent;
        private RoundPanel mainSelected, mainTotal, selected, total;
        private SpringLayout layout;

        PokeSelected(PokeSelection parent) {
            this.parent = parent;

            setOpaque(false);
            setLayout(layout = new SpringLayout());
            setPreferredSize(new Dimension((int) (AppConstants.SCREEN_HEIGHT * 0.186),
                    (int) (AppConstants.SCREEN_HEIGHT * 0.89)));

            total = new RoundPanel(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
            total.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            total.setLayout(new GridLayout(1, 1));
            total.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().width));

            add(total);
            layout.putConstraint(SpringLayout.SOUTH, total, 0, SpringLayout.SOUTH, this);
            layout.putConstraint(SpringLayout.WEST, total, 0, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.EAST, total, 0, SpringLayout.EAST, this);

            selected = new RoundPanel(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
            selected.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            selected.setLayout(new GridLayout(1, 1));

            add(selected);
            layout.putConstraint(SpringLayout.NORTH, selected, 0, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.SOUTH, selected, 0, SpringLayout.NORTH, total);
            layout.putConstraint(SpringLayout.WEST, selected, 0, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.EAST, selected, 0, SpringLayout.EAST, this);

            mainSelected = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            selected.add(mainSelected);

            mainTotal = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainTotal.setLayout(new BoxLayout(mainTotal, BoxLayout.Y_AXIS));
            total.add(mainTotal);

            countLabel = new JLabel("" + count + "/10", JLabel.CENTER);

            Box box = Box.createVerticalBox();
            box.setAlignmentX(Box.CENTER_ALIGNMENT);
            box.add(Box.createVerticalGlue());
            box.add(countLabel);
            box.add(new JLabel("Total", JLabel.CENTER));
            box.add(Box.createVerticalGlue());
            mainTotal.add(box);
        }
    }
}