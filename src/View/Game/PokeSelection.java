package View.Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SpringLayout;

import Data.AppColor;
import Data.AppConstants;
import View.Share.AnimatedPanel;
import View.Share.ChoiceList;
import View.Share.RoundPanel;

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

    class Filter extends RoundPanel implements FocusListener, KeyListener {
        private int mode = 0;
        private JLabel gen, type, caught, unlocks, misc, sort;
        private JPopupMenu popup;
        private ChoiceList caughtList, genList, miscList, sortList, typeList, unlocksList;
        private PokeSelection parent;
        private RoundPanel mainPanel;

        public Filter(PokeSelection parent) {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            this.parent = parent;

            setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            setFocusable(true);
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

            genList = new ChoiceList(this, "All", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");

            type = new JLabel("Type", JLabel.CENTER);
            type.setForeground(gen.getBackground());
            type.setFont(gen.getFont());
            mainPanel.add(type);

            typeList = new ChoiceList(this, "All", "Normal", "Fight", "Flying", "Poison", "Ground", "Rock", "Bug",
                    "Ghost", "Steel", "Fire", "Water", "Grass", "Electr", "Psychc", "Ice", "Dragon", "Dark", "Fairy");

            caught = new JLabel("Caught", JLabel.CENTER);
            caught.setForeground(gen.getBackground());
            caught.setFont(gen.getFont());
            mainPanel.add(caught);

            caughtList = new ChoiceList(this, "All", "RED_BALL", "BLUE_BALL", "YELLOW_BALL", "Not shiny", "Uncaught");

            unlocks = new JLabel("Unlocks", JLabel.CENTER);
            unlocks.setForeground(gen.getForeground());
            unlocks.setFont(gen.getFont());
            mainPanel.add(unlocks);

            unlocksList = new ChoiceList(this, "Passive", "Cost Reduction");

            misc = new JLabel("Misc", JLabel.CENTER);
            misc.setForeground(gen.getForeground());
            misc.setFont(gen.getFont());
            mainPanel.add(misc);

            miscList = new ChoiceList(this, "Favourite", "Ribbon", "Hidden Ability", "Egg", "Pokerús");

            sort = new JLabel("Sort", JLabel.CENTER);
            sort.setForeground(gen.getForeground());
            sort.setFont(gen.getFont());
            mainPanel.add(sort);

            sortList = new ChoiceList(this, "No.", "Cost", "Candy Count", "IVs", "Name");

            popup = new JPopupMenu();

            addFocusListener(this);
            addKeyListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
            switch (mode) {
                case 0:
                    parent.hideFilterPopup(genList);
                    break;
                case 1:
                    parent.hideFilterPopup(typeList);
                    break;
                case 2:
                    parent.hideFilterPopup(caughtList);
                    break;
                case 3:
                    parent.hideFilterPopup(unlocksList);
                    break;
                case 4:
                    parent.hideFilterPopup(miscList);
                    break;
                case 5:
                    parent.hideFilterPopup(sortList);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            ((JLabel) mainPanel.getComponent(mode)).setIcon(null);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                ((JLabel) mainPanel.getComponent(mode--)).setIcon(null);

                mode = (mode + 6) % 6;
                ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                ((JLabel) mainPanel.getComponent(mode++)).setIcon(null);

                mode %= 6;
                ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                pokeList.requestFocusInWindow();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                switch (mode) {
                    case 0:
                        parent.showFilterPopup(genList);
                        break;
                    case 1:
                        parent.showFilterPopup(typeList);
                        break;
                    case 2:
                        parent.showFilterPopup(caughtList);
                        break;
                    case 3:
                        parent.showFilterPopup(unlocksList);
                        break;
                    case 4:
                        parent.showFilterPopup(miscList);
                        break;
                    case 5:
                        parent.showFilterPopup(sortList);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    class PokeList extends RoundPanel implements FocusListener, KeyListener {
        private PokeSelection parent;

        private int curX = 0, curY = 0;
        private ImageIcon cursor;

        PokeList(PokeSelection parent) {
            super(AppConstants.BORDER_RADIUS, 8, AppColor.gray01, AppColor.gray02);

            this.parent = parent;

            setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
            setFocusable(true);
            setLayout(new GridLayout(0, 9));

            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));
            add(new JLabel("PK"));

            addFocusListener(this);
            addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            if (cursor != null) {
                Component comp = getComponent(curY * 9 + curX);
                g2d.drawImage(cursor.getImage(), comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), this);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                int abs = curY * 9 + curX;

                abs++;
                curX = abs % 9;
                curY = abs / 9;

                revalidate();
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                int abs = curY * 9 + curX;

                abs--;
                if (abs < 0)
                    return;
                curX = abs % 9;
                curY = abs / 9;

                revalidate();
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int abs = curY * 9 + curX;

                abs += 9;
                curX = abs % 9;
                curY = abs / 9;

                revalidate();
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                int abs = curY * 9 + curX;

                abs -= 9;
                if (abs < 0) {
                    filter.requestFocusInWindow();

                    return;
                }
                curX = abs % 9;
                curY = abs / 9;

                revalidate();
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void focusGained(FocusEvent e) {
            cursor = AppConstants.IMG_SELECT_CURSOR;

            revalidate();
            repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            cursor = null;

            revalidate();
            repaint();
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
            mainSelected.setLayout(new GridLayout(6, 1));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
            mainSelected
                    .add(new AnimatedPanel(new ImageIcon(getClass().getResource("../../assets/img/unknown_poke.png"))));
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

    public PokeList getPokeList() {
        return pokeList;
    }

    public PokeSelected getPokeSelected() {
        return pokeSelected;
    }
}