package View.Game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Controller.GameController;
import Data.AppConstants;
import Utils.Pair;

public class BattleGround extends JPanel implements ActionListener {
    private SpringLayout layout;

    private JLabel eGround, pGround;

    private Timer timer;
    private int posX = 0;

    public BattleGround(GameController controller) {
        super();

        setLayout(layout = new SpringLayout());
        setOpaque(false);

        Pair<ImageIcon, ImageIcon> grounds = AppConstants.getRandomGround();
        eGround = new JLabel();
        eGround.setOpaque(false);
        add(eGround);
        layout.putConstraint(SpringLayout.NORTH, eGround, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, eGround, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, eGround, posX - AppConstants.SCREEN_WIDTH, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, eGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, eGround);

        pGround = new JLabel();
        pGround.setOpaque(false);
        add(pGround);
        layout.putConstraint(SpringLayout.NORTH, pGround, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, pGround, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, pGround, AppConstants.SCREEN_WIDTH - posX, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, pGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, pGround);

        timer = new Timer(15, this);
        timer.start();

        SwingUtilities.invokeLater(() -> {
            Image eii = grounds.first().getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            Image pii = grounds.second().getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

            eGround.setIcon(new ImageIcon(eii));
            pGround.setIcon(new ImageIcon(pii));
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pWidth = AppConstants.SCREEN_WIDTH;

        layout.putConstraint(SpringLayout.WEST, eGround, posX - pWidth, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, pGround, pWidth - posX, SpringLayout.WEST, this);

        revalidate();
        repaint();

        System.out.println(posX);

        if (posX >= pWidth)
            timer.stop();

        posX += 20;
        if (posX >= pWidth)
            posX = pWidth;
    }
}
