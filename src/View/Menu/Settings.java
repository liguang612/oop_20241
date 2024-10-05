package View.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Data.AppColor;
import Data.AppConstants;
import View.Share.RoundPanel;

public class Settings extends JPanel implements KeyListener {
    private NavigationBar navigationBar;
    private General general;
    private Display display;
    private Audio audio;
    private Gamepad gamepad;
    private Keyboard keyboard;

    public Settings() {
        super();

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        setLayout(new BorderLayout());
        setFocusable(true);
        setOpaque(false);
        setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 20, AppConstants.SCREEN_HEIGHT - 20));

        navigationBar = new NavigationBar();
        add(navigationBar, BorderLayout.NORTH);

        add(new Footer(), BorderLayout.SOUTH);

        general = new General();
        add(general, BorderLayout.CENTER);

        display = new Display();

        audio = new Audio();

        gamepad = new Gamepad();

        keyboard = new Keyboard();

        addKeyListener(this);
    }

    void changeSettings() {
        remove(2);

        switch (navigationBar.getTab()) {
            case 0:
                add(general, BorderLayout.CENTER);
                break;
            case 1:
                add(display, BorderLayout.CENTER);
                break;
            case 2:
                add(audio, BorderLayout.CENTER);
                break;
            case 3:
                add(gamepad, BorderLayout.CENTER);
                break;
            case 4:
                add(keyboard, BorderLayout.CENTER);
                break;

            default:
                break;
        }

        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F) {
            navigationBar.prevTab();
            changeSettings();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            navigationBar.nextTab();
            changeSettings();
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            MainMenu parent = (MainMenu) getParent();
            parent.remove(parent.getSettingsPanel());
            parent.add(parent.getControlPanel());

            parent.getLayout().putConstraint(SpringLayout.EAST, parent.getControlPanel(), -10, SpringLayout.EAST,
                    parent);
            parent.getLayout().putConstraint(SpringLayout.SOUTH, parent.getControlPanel(), -10, SpringLayout.SOUTH,
                    parent);

            parent.revalidate();
            parent.repaint();

            parent.getControlPanel().requestFocusInWindow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    class NavigationBar extends RoundPanel {
        private int tab = 0;
        private JLabel general, display, audio, gamepad, keyboard;
        private JPanel tabPanel;
        private RoundPanel mainPanel;

        NavigationBar() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 20) / 8));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            general = new JLabel("General");
            general.setForeground(AppColor.red02);
            general.setFont(general.getFont().deriveFont(48f));

            display = new JLabel("Display");
            display.setForeground(AppColor.yellow01);
            display.setFont(general.getFont());

            audio = new JLabel("Audio");
            audio.setForeground(display.getForeground());
            audio.setFont(general.getFont());

            gamepad = new JLabel("Gamepad");
            gamepad.setForeground(display.getForeground());
            gamepad.setFont(general.getFont());

            keyboard = new JLabel("Keyboard");
            keyboard.setForeground(display.getForeground());
            keyboard.setFont(general.getFont());

            tabPanel = new JPanel();
            tabPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
            tabPanel.setOpaque(false);

            tabPanel.add(general);
            tabPanel.add(display);
            tabPanel.add(audio);
            tabPanel.add(gamepad);
            tabPanel.add(keyboard);

            mainPanel.add(tabPanel, BorderLayout.CENTER);
            mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("../../assets/img/button_f.png"))),
                    BorderLayout.WEST);
            mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("../../assets/img/button_r.jpg"))),
                    BorderLayout.EAST);

            add(mainPanel);
        }

        void nextTab() {
            tabPanel.getComponent(tab).setForeground(AppColor.yellow01);
            tab = (tab + 1) % 5;
            tabPanel.getComponent(tab).setForeground(AppColor.red02);

            revalidate();
            repaint();
        }

        void prevTab() {
            tabPanel.getComponent(tab).setForeground(AppColor.yellow01);
            tab = (tab + 4) % 5;
            tabPanel.getComponent(tab).setForeground(AppColor.red02);

            revalidate();
            repaint();
        }

        int getTab() {
            return tab;
        }
    }

    class General extends RoundPanel {
        private RoundPanel mainPanel;

        General() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 50) * 3 / 4));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel("GENERAL_SETTINGS"), BorderLayout.EAST);
            mainPanel.getComponent(0).setForeground(Color.WHITE);

            add(mainPanel);
        }
    }

    class Display extends RoundPanel {
        private RoundPanel mainPanel;

        Display() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 50) * 3 / 4));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel("DISPLAY_SETTINGS"), BorderLayout.EAST);
            mainPanel.getComponent(0).setForeground(Color.WHITE);

            add(mainPanel);
        }
    }

    class Audio extends RoundPanel {
        private RoundPanel mainPanel;

        Audio() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 50) * 3 / 4));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel("AUDIO_SETTINGS"), BorderLayout.EAST);
            mainPanel.getComponent(0).setForeground(Color.WHITE);

            add(mainPanel);
        }
    }

    class Gamepad extends RoundPanel {
        private RoundPanel mainPanel;

        Gamepad() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 50) * 3 / 4));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel("GAMEPAD_SETTINGS"), BorderLayout.EAST);
            mainPanel.getComponent(0).setForeground(Color.WHITE);

            add(mainPanel);
        }
    }

    class Keyboard extends RoundPanel {
        private RoundPanel mainPanel;

        Keyboard() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 50) * 3 / 4));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel("KEYBOARD_SETTINGS"), BorderLayout.EAST);
            mainPanel.getComponent(0).setForeground(Color.WHITE);

            add(mainPanel);
        }
    }

    class Footer extends RoundPanel {
        private RoundPanel mainPanel;

        Footer() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, (AppConstants.SCREEN_HEIGHT - 20) / 8));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            mainPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

            JLabel back = new JLabel("Back"), action = new JLabel("Space");

            back.setFont(back.getFont().deriveFont(48f));
            back.setForeground(AppColor.yellow01);

            action.setFont(back.getFont());
            action.setForeground(back.getForeground());

            mainPanel.add(back);
            mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("../../assets/img/button_back.png"))));
            mainPanel.add(Box.createHorizontalStrut(20));
            mainPanel.add(action);
            mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("../../assets/img/button_space.png"))));

            add(mainPanel);
        }
    }
}
