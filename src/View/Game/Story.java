package View.Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import Controller.GameController;
import Data.AppColor;
import Data.AppConstants;
import Data.AppConstants.GameState;
import View.Share.RoundPanel;

public class Story extends RoundPanel implements ActionListener, KeyListener {
    private GameController controller;
    private RoundPanel mainPanel;

    private JLabel msgLabel;
    private String msg;
    private int idx;

    private Timer timer;

    public Story(GameController controller) {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
        this.controller = controller;

        SpringLayout layout = controller.getLayout();
        setFocusable(true);
        setLayout(layout);

        mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        add(mainPanel);
        layout.putConstraint(SpringLayout.NORTH, mainPanel, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, mainPanel, -20, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, mainPanel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, mainPanel, -20, SpringLayout.EAST, this);

        msgLabel = new JLabel();
        mainPanel.add(msgLabel);

        timer = new Timer(60, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (idx < msg.length()) {
            msgLabel.setText(msgLabel.getText() + msg.charAt(idx));
            idx++;
        } else {
            timer.stop();
            addKeyListener(this);
        }
    }

    public void receiveMsg(String msg) {
        this.msg = msg;
        msgLabel.setText("");
        idx = 0;

        removeKeyListener(this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Get next state
        GameState nextState = controller.next();

        // Key event handler
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            controller.sendMessage(nextState);
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            if (nextState != GameState.action)
                return;
            controller.sendMessage(GameState.skills);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            if (controller.getState() != GameState.action)
                return;
            controller.sendMessage(GameState.run);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            if (controller.getState() != GameState.action)
                return;
            controller.sendMessage(GameState.change);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
