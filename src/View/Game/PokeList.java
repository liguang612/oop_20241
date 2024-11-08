package View.Game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Data.AppColor;
import Data.AppConstants;
import Model.Pokemon;
import View.Share.AnimatedPanel;
import View.Share.RoundPanel;

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

        for (Pokemon pokemon : AppConstants.ALL_OF_POKEMONS) {
            add(new PokeLabel(pokemon));
        }
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

            // Start animation (stop will be in keyPressed)
            if (comp instanceof AnimatedPanel) {
                ((AnimatedPanel) comp).start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int abs = curY * 9 + curX;
        Component comp = getComponent(curY * 9 + curX);

        // Stop animation, start will be in paintComponent
        if (comp instanceof AnimatedPanel) {
            ((AnimatedPanel) comp).stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            abs++;
            curX = abs % 9;
            curY = abs / 9;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            abs--;
            if (abs < 0)
                return;
            curX = abs % 9;
            curY = abs / 9;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            abs += 9;
            curX = abs % 9;
            curY = abs / 9;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            abs -= 9;
            if (abs < 0) {
                parent.getFilter().requestFocusInWindow();

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

    class PokeLabel extends JPanel {
        PokeLabel(Pokemon pokemon) {
            super();

            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            setOpaque(false);

            AnimatedPanel anmPanel = new AnimatedPanel(pokemon.getAvatar());
            add(anmPanel);
            layout.putConstraint(SpringLayout.NORTH, anmPanel, 0, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.SOUTH, anmPanel, 0, SpringLayout.SOUTH, this);
            layout.putConstraint(SpringLayout.WEST, anmPanel, 0, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.EAST, anmPanel, 0, SpringLayout.EAST, this);

            JLabel ivLabel = new JLabel("" + pokemon.getIVs());
            ivLabel.setFont(ivLabel.getFont().deriveFont(24f));
            layout.putConstraint(SpringLayout.NORTH, ivLabel, 15, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, ivLabel, 15, SpringLayout.WEST, this);
            add(ivLabel);
        }
    }
}