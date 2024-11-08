package View.Share;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Column extends JPanel {
    private Component[] children;

    public Column(int gap, Component... children) {
        super();
        this.children = children;

        setAlignmentX(JPanel.CENTER_ALIGNMENT);
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < children.length - 1; i++) {
            add(children[i]);
            add(Box.createVerticalStrut(gap));
        }
        add(children[children.length - 1]);
    }

    public void expandChild() {
        setLayout(new GridLayout(children.length, 1));

        int tmp = 1;
        while (tmp < getComponentCount())
            remove(tmp++);
    }
}