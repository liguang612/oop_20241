package View.Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import Controller.GameController;
import Data.AppColor;
import Data.AppConstants;
import View.Share.RoundPanel;

public class Story extends RoundPanel implements ActionListener {
    private RoundPanel mainPanel;

    private JLabel msgLabel;
    private String msg;
    private int idx;

    private Timer timer;

    public Story(GameController controller) {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);

        SpringLayout layout = controller.getLayout();
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

        timer = new Timer(75, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (idx < msg.length()) {
            msgLabel.setText(msgLabel.getText() + msg.charAt(idx));
            idx++;
        } else {
            timer.stop();
        }
    }

    public void receiveMsg(String msg) {
        this.msg = msg;
        msgLabel.setText("");
        idx = 0;

        timer.start();
    }
}
