package View.Prepare;

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

import Controller.PrepareController;
import Data.AppColor;
import Data.AppConstants;
import Model.Pokemon;
import View.Share.AnimatedPanel;
import View.Share.RoundPanel;

public class PokeList extends RoundPanel implements FocusListener, KeyListener {
    private PrepareController controller;

    private int curX = 0, curY = 0;
    private ImageIcon cursor;

    public PokeList(PrepareController controller) {
        super(AppConstants.BORDER_RADIUS, 8, AppColor.gray01, AppColor.gray02);
        this.controller = controller;

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setFocusable(true);
        setLayout(new GridLayout(0, 8, 10, 0));

        for (Pokemon pokemon : AppConstants.ALL_OF_POKEMONS) {
            add(new PokeLabel(pokemon));
        }

        addFocusListener(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (cursor != null) {
            Component comp = getComponent(curY * 8 + curX);
            g2d.drawImage(cursor.getImage(), comp.getX(), comp.getY(), comp.getWidth(),
                    comp.getHeight(), this);

            // Start animation in new item, stop will be in keyPressed
            if (comp instanceof PokeLabel) {
                ((PokeLabel) comp).start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int abs = curY * 8 + curX;
        Component comp = getComponent(curY * 8 + curX);

        // Stop animation in old item, start will be in painComponent
        if (comp instanceof PokeLabel) {
            ((PokeLabel) comp).stop();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (abs + 1 < AppConstants.ALL_OF_POKEMONS.size()) {
                abs++;
            }
            curX = abs % 8;
            curY = abs / 8;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            abs--;
            if (abs < 0)
                return;
            curX = abs % 8;
            curY = abs / 8;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (AppConstants.ALL_OF_POKEMONS.size() > abs + 8) {
                abs += 8;
            }
            curX = abs % 8;
            curY = abs / 8;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            abs -= 8;
            if (abs < 0) {
                controller.getFilter().requestFocusInWindow();

                return;
            }
            curX = abs % 8;
            curY = abs / 8;

            revalidate();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            PokeLabel pl = ((PokeLabel) comp);

            if (pl.switchSelect()) {
                if (!controller.getPokeSelected().selectPoke(abs))
                    pl.switchSelect();
            } else
                controller.getPokeSelected().unselectPoke(abs);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            controller.goToGame();
        }

        controller.changePokemon(AppConstants.ALL_OF_POKEMONS.get(curY * 8 + curX));
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
        AnimatedPanel anmPanel;
        boolean isSelected;

        PokeLabel(Pokemon pokemon) {
            super();

            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            setOpaque(false);

            anmPanel = new AnimatedPanel(pokemon.getAvatar());
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

            anmPanel.stop();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isSelected) {
                Graphics2D g2d = (Graphics2D) g;

                g2d.drawImage(AppConstants.IMG_SELECT_CURSOR_HIGHLIGHT.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        }

        boolean switchSelect() {
            isSelected = !isSelected;

            revalidate();
            repaint();

            return isSelected;
        }

        void start() {
            anmPanel.start();
        }

        void stop() {
            anmPanel.stop();
        }
    }
}