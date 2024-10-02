package View.Share;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RoundPanel extends JPanel {
    private int borderRadius, borderWidth;
    private Color borderColor;
    private Color backgroundColor;

    public RoundPanel() {
    }

    public RoundPanel(int borderRadius, int borderWidth, Color borderColor, Color backgroundColor) {
        this.borderRadius = borderRadius;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(borderColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(borderWidth, borderWidth, getWidth() - 2 * borderWidth, getHeight() - 2 * borderWidth,
                borderRadius, borderRadius);
    }
}
