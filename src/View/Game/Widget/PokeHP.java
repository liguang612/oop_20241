package View.Game.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Data.AppColor;
import Model.PokeType;
import Model.Pokemon;

public class PokeHP extends JPanel {
    private Pokemon pokemon;

    public PokeHP(Pokemon pokemon) {
        super();
        this.pokemon = pokemon;

        setBackground(AppColor.black02);
        setBorder(BorderFactory.createLineBorder(AppColor.black01, 1, true));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel hpPanel = new JPanel(new GridLayout(2, 1));

        JPanel topHPPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        topHPPanel.add(new JLabel(pokemon.getName()) {
            @Override
            public void setForeground(Color fg) {
                super.setForeground(AppColor.black);
            }
        });
        topHPPanel.add(new JLabel("Lv." + pokemon.getIVs()) {
            @Override
            public void setForeground(Color fg) {
                super.setForeground(AppColor.black);
            }
        });
        hpPanel.add(topHPPanel);

        JPanel bottomHPPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                double percent = pokemon.getHpLeft() / pokemon.getHp();

                g2d.setColor(AppColor.green02);
                g2d.fillRect(0, 0,
                        (int) (getWidth() * percent), getHeight());

                g2d.setColor(AppColor.white);
                g2d.fillRect((int) (getWidth() * percent), 0,
                        (int) (getWidth() * (1 - percent)), getHeight());
            }
        };
        hpPanel.add(bottomHPPanel);

        JPanel typePanel = new JPanel(new GridLayout(1, pokemon.getType().length));
        for (PokeType type : pokemon.getType()) {
            JLabel label = new JLabel("  ");
            label.setBackground(type.getColor());
            label.setOpaque(true);

            typePanel.add(label);
        }

        add(hpPanel);
        add(typePanel);
    }

}
