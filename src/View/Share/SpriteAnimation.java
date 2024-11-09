package View.Share;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Utils.Utils;

public class SpriteAnimation {
    private JPanel animationPanel;
    private List<Rectangle> coords;
    private int currentFrame;
    private BufferedImage spriteSheet;
    private Timer timer;

    private final int SIZE = 150;

    public SpriteAnimation(String spriteSheetPath, String spriteCoordinatesPath) {
        try {
            this.spriteSheet = ImageIO.read(new File(spriteSheetPath));
            this.currentFrame = 0;

            coords = Utils.parseSpriteCoordinates(spriteCoordinatesPath);

            this.animationPanel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawCurrentFrame(g);
                };
            };
            animationPanel.setOpaque(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawCurrentFrame(Graphics g) {
        Rectangle coord = coords.get(currentFrame);
        g.drawImage(spriteSheet, 0, 0, SIZE, SIZE, coord.x, coord.y, coord.x + coord.width, coord.y + coord.height,
                null);
    }

    public JPanel getAnimationPanel() {
        this.animationPanel.setPreferredSize(new Dimension(SIZE, SIZE));
        return this.animationPanel;
    }

    public void startAnimation(int delay) {
        timer = new Timer(delay, (e) -> {
            currentFrame = (currentFrame + 1) % coords.size();
            animationPanel.repaint();
        });
        timer.start();
    }
}
