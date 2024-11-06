package View.Share;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Data.AppColor;
import Data.AppConstants;
import Model.PokeType;

public class ChoiceList<T> extends RoundPanel implements KeyListener {
    private ImageIcon cursor;
    private int cursorIndex = 0;
    private RoundPanel mainPanel;

    private List<JRadioButton> choiceButtons;
    private T[] choices;
    private Set<Integer> selected;

    private JPanel predecessor;

    public ChoiceList(JPanel predecessor, T... choices) {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
        this.choices = choices;
        this.predecessor = predecessor;

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setFocusable(true);
        setLayout(new GridLayout(1, 1));
        setOpaque(false);

        cursor = AppConstants.IMG_CURSOR_SMALL;

        mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                JRadioButton button = choiceButtons.get(cursorIndex);
                g2d.drawImage(cursor.getImage(), button.getX() - cursor.getIconWidth(),
                        button.getY() + button.getHeight() / 2 - cursor.getIconHeight() / 2, mainPanel);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 8));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        initElements();

        selected = new HashSet<>();

        addKeyListener(this);
    }

    public Set<Integer> getSelected() {
        return selected;
    }

    private void initElements() {
        choiceButtons = new ArrayList<>();
        for (T choice : choices) {
            JRadioButton button = new JRadioButton(choice.toString());
            if (choice instanceof PokeType) {
                button.setForeground(((PokeType) choice).getColor());
            }

            button.setFont(button.getFont().deriveFont(24.0f));
            button.setOpaque(false);

            choiceButtons.add(button);
            mainPanel.add(button);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            cursorIndex++;
            cursorIndex %= choiceButtons.size();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            cursorIndex = (cursorIndex + choiceButtons.size() - 1) % choiceButtons.size();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            JRadioButton button = choiceButtons.get(cursorIndex);

            if (!button.isSelected()) {
                button.setSelected(true);
                selected.add(cursorIndex);
            } else {
                button.setSelected(false);
                selected.remove(cursorIndex);
            }
        }

        revalidate();
        repaint();

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            predecessor.requestFocusInWindow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
