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

    private JLabel eGround, pGround, trainer;

    private Timer timer;
    private int posX = 0;

    public BattleGround(GameController controller) {
        super();

        setLayout(layout = new SpringLayout());
        setOpaque(false);

        trainer = new JLabel(AppConstants.IMG_TRAINER_MALE);
        add(trainer);
        layout.putConstraint(SpringLayout.NORTH, trainer, AppConstants.SCREEN_HEIGHT * 2 / 5, SpringLayout.NORTH, this);

        Pair<ImageIcon, ImageIcon> grounds = AppConstants.getRandomGround();
        eGround = new JLabel();
        add(eGround);
        layout.putConstraint(SpringLayout.NORTH, eGround, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, eGround, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, eGround, posX - AppConstants.SCREEN_WIDTH, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, eGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, eGround);

        layout.putConstraint(SpringLayout.WEST, trainer, 0, SpringLayout.WEST, eGround);

        pGround = new JLabel();
        add(pGround);
        layout.putConstraint(SpringLayout.NORTH, pGround, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, pGround, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, pGround, AppConstants.SCREEN_WIDTH - posX, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, pGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, pGround);

        timer = new Timer(15, this);
        timer.start();

        SwingUtilities.invokeLater(() -> {
            final double scale = getWidth() / grounds.first().getIconWidth();

            Image eii = grounds.first().getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            Image pii = grounds.second().getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            Image trii = AppConstants.IMG_TRAINER_MALE.getImage().getScaledInstance(
                    (int) (AppConstants.IMG_TRAINER_MALE.getIconWidth() * scale),
                    (int) (AppConstants.IMG_TRAINER_MALE.getIconHeight() * scale),
                    Image.SCALE_SMOOTH);

            eGround.setIcon(new ImageIcon(eii));
            pGround.setIcon(new ImageIcon(pii));
            trainer.setIcon(new ImageIcon(trii));
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pWidth = AppConstants.SCREEN_WIDTH;

        layout.putConstraint(SpringLayout.WEST, eGround, posX - pWidth, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, pGround, pWidth - posX, SpringLayout.WEST, this);

        revalidate();
        repaint();

        if (posX >= pWidth)
            timer.stop();

        posX += 20;
        if (posX >= pWidth)
            posX = pWidth;
    }
}