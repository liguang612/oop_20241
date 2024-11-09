package View.Share;

import java.awt.*;
import javax.swing.*;

public class Row extends JPanel {
    private int gap;

    public Row(int gap, Component... children) {
        super();
        this.gap = gap;

        int maxHeight = 0, tmp;

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for (int i = 0; i < children.length - 1; i++) {
            add(children[i]);
            add(Box.createHorizontalStrut(gap));

            tmp = ((int) children[i].getMaximumSize().getHeight());
            maxHeight = maxHeight < tmp ? tmp : maxHeight;
        }
        add(children[children.length - 1]);
        tmp = ((int) children[children.length - 1].getMaximumSize().getHeight());
        maxHeight = maxHeight < tmp ? tmp : maxHeight;

        setMaximumSize(new Dimension(((int) getMaximumSize().getWidth()), maxHeight));
    }

    public void resetChildren(Component... children) {
        removeAll();

        int maxHeight = 0, tmp;

        for (int i = 0; i < children.length - 1; i++) {
            add(children[i]);
            add(Box.createHorizontalStrut(gap));

            tmp = ((int) children[i].getMaximumSize().getHeight());
            maxHeight = maxHeight < tmp ? tmp : maxHeight;
        }
        add(children[children.length - 1]);
        tmp = ((int) children[children.length - 1].getMaximumSize().getHeight());
        maxHeight = maxHeight < tmp ? tmp : maxHeight;

        setMaximumSize(new Dimension(((int) getMaximumSize().getWidth()), maxHeight));
    }
}