package View.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Data.AppColor;
import Data.AppConstants;
import View.Share.RoundPanel;

public class Settings extends JPanel {
    private NavigationBar navigationBar;

    public Settings() {
        super();

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 20, AppConstants.SCREEN_HEIGHT - 20));

        navigationBar = new NavigationBar();
        add(navigationBar, BorderLayout.NORTH);
    }

    class NavigationBar extends RoundPanel implements KeyListener {
        private int tab = 0;
        private JLabel general, display, audio, gamepad, keyboard;
        private JPanel tabPanel;
        private RoundPanel mainPanel;

        NavigationBar() {
            super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

            setPreferredSize(new Dimension(AppConstants.SCREEN_WIDTH - 30, AppConstants.SCREEN_HEIGHT / 7));

            mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
            mainPanel.setLayout(new BorderLayout(15, 15));

            mainPanel.add(new JLabel(getClass().getResource("../../assets/img/button_f.png").toString()),
                    BorderLayout.WEST);
            mainPanel.add(new JLabel(getClass().getResource("../../assets/img/button_r.jpg").toString()),
                    BorderLayout.EAST);

            tabPanel = new JPanel();
            tabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            general = new JLabel("General");
            general.setFont(general.getFont().deriveFont(24f));

            display = new JLabel("Display");
            display.setFont(general.getFont());

            audio = new JLabel("Audio");
            audio.setFont(general.getFont());

            gamepad = new JLabel("Gamepad");
            gamepad.setFont(general.getFont());

            keyboard = new JLabel("Keyboard");
            keyboard.setFont(general.getFont());

            tabPanel.add(general);
            tabPanel.add(display);
            tabPanel.add(audio);
            tabPanel.add(gamepad);
            tabPanel.add(keyboard);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_F) {
                tabPanel.getComponent(tab).setForeground(AppColor.yellow01);
                tabPanel.getComponent(++tab).setForeground(AppColor.red02);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
