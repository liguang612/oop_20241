package View.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Controller.MenuController;
import Controller.PrepareController;
import Data.AppColor;
import Data.AppConstants;
import View.Share.RoundPanel;

public class MainMenu extends JPanel {
    private MenuController controller;

    private ControlPanel controlPanel;
    private Settings settingsPanel;
    private SpringLayout layout;

    public MainMenu(MenuController controller) {
        super();
        this.controller = controller;

        setLayout(layout = new SpringLayout());

        controlPanel = new ControlPanel();
        add(controlPanel);

        settingsPanel = controller.getSettings();

        layout.putConstraint(SpringLayout.EAST, controlPanel, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, controlPanel, -10, SpringLayout.SOUTH, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image background = AppConstants.IMG_GAME_BACKGROUND.getImage(), logo = AppConstants.IMG_LOGO.getImage();

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, this);
        g2d.drawImage(logo, AppConstants.SCREEN_WIDTH / 2 - logo.getWidth(this) / 2, 0, this);
    }

    class ControlPanel extends RoundPanel implements KeyListener {
        private JLabel pointer, newGame, settings, exit;
        private GridBagConstraints gbc;
        private int option = 0;
        private RoundPanel mainPanel;

        ControlPanel() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            SpringLayout layout = new SpringLayout();
            setFocusable(true);
            setLayout(layout);

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new GridBagLayout());

            pointer = new JLabel(AppConstants.IMG_CURSOR);
            pointer.setSize(pointer.getPreferredSize().width, 32);

            gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;

            newGame = new JLabel("New Game", JLabel.LEFT);
            newGame.setForeground(Color.WHITE);
            newGame.setFont(newGame.getFont());
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 14;
            mainPanel.add(newGame, gbc);

            settings = new JLabel("Settings", JLabel.LEFT);
            settings.setForeground(newGame.getForeground());
            settings.setFont(newGame.getFont());
            gbc.gridy = 1;
            // mainPanel.add(settings, gbc);

            exit = new JLabel("Exit", JLabel.LEFT);
            exit.setForeground(newGame.getForeground());
            exit.setFont(newGame.getFont());
            gbc.gridy = 1;
            mainPanel.add(exit, gbc);

            gbc.gridx = 0;
            gbc.gridy = option;
            gbc.weightx = 1;
            mainPanel.add(pointer, gbc);

            setPreferredSize(
                    new Dimension(mainPanel.getPreferredSize().width + 40, mainPanel.getPreferredSize().height + 40));

            add(mainPanel);
            layout.putConstraint(SpringLayout.NORTH, mainPanel, 20, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.SOUTH, mainPanel, -20, SpringLayout.SOUTH, this);
            layout.putConstraint(SpringLayout.WEST, mainPanel, 20, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.EAST, mainPanel, -20, SpringLayout.EAST, this);

            addKeyListener(this);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                mainPanel.remove(pointer);

                gbc.gridy = (++option) % 2;
                mainPanel.add(pointer, gbc);

                revalidate();
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                mainPanel.remove(pointer);

                option = (option + 1) % 2;
                gbc.gridy = option;
                mainPanel.add(pointer, gbc);

                revalidate();
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                switch (option) {
                    case 0:
                        newGame();
                        break;
                    // case 1:
                    // settings();
                    // break;

                    case 1:
                        exit();
                        break;

                    default:
                        break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        void newGame() {
            new PrepareController();

            controller.getMenu().setVisible(false);
        }

        void settings() {
            JPanel parent = (JPanel) getParent();
            parent.remove(controlPanel);
            parent.add(settingsPanel);

            layout.putConstraint(SpringLayout.NORTH, settings, 10, SpringLayout.NORTH, parent);
            layout.putConstraint(SpringLayout.SOUTH, settings, -10, SpringLayout.SOUTH, parent);
            layout.putConstraint(SpringLayout.WEST, settings, 10, SpringLayout.WEST, parent);
            layout.putConstraint(SpringLayout.EAST, settings, -10, SpringLayout.EAST, parent);

            parent.revalidate();
            parent.repaint();

            settingsPanel.requestFocusInWindow();
        }

        void exit() {
            System.exit(0);
        }
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public Settings getSettingsPanel() {
        return settingsPanel;
    }

    public SpringLayout getLayout() {
        return layout;
    }
}