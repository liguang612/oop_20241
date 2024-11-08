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
    private int currentFrame;
    private List<BufferedImage> sprites;
    private BufferedImage spriteSheet;
    private Timer timer;

    public SpriteAnimation(String spriteSheetPath, String spriteCoordinatesPath) {
        try {
            this.spriteSheet = ImageIO.read(new File(spriteSheetPath));
            this.currentFrame = 0;

            List<Rectangle> coords = Utils.parseSpriteCoordinates(spriteCoordinatesPath);

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
        Rectangle rect = spriteCoordinates.get(currentFrame);
        g.drawImage(spriteSheet, 0, 0, rect.width, rect.height, rect.x, rect.y, rect.x + rect.width,
                rect.y + rect.height, null);
    }

    public JPanel getAnimationPanel() {
        return this.animationPanel;
    }

    public void startAnimation(int delay) {
        timer = new Timer(delay, (e) -> {
            currentFrame = (currentFrame + 1) % sprites.size();
            animationPanel.repaint();
        });
        timer.start();
    }
}
