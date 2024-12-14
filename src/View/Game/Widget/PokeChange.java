package View.Game.Widget;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Data.AppColor;
import Model.Pokemon;

public class PokeChange extends JPanel {
    private Pokemon pokemon;

    public PokeChange(Pokemon pokemon) {
        super();
        this.pokemon = pokemon;

        setBackground(AppColor.blue05);
        setBorder(BorderFactory.createLineBorder(AppColor.blue04, 4));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(initLeft());
        add(initRight());
    }

    JPanel initLeft() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(new JLabel(pokemon.getAvatar()));

        JPanel right = new JPanel(new GridLayout(2, 1, 5, 5));
        right.setOpaque(false);
        right.add(new JLabel(pokemon.getName()));
        right.add(new JLabel("Lv. " + pokemon.getIVs(), JLabel.CENTER));
        panel.add(right);

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    JPanel initRight() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setOpaque(false);

        panel.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(AppColor.green02);
                g2d.fillRect(0, 0,
                        getWidth() * pokemon.getHpLeft() / pokemon.getHp(), getHeight());

                g2d.setColor(AppColor.white);
                g2d.fillRect(getWidth() * pokemon.getHpLeft() / pokemon.getHp(), 0,
                        getWidth() - getWidth() * pokemon.getHpLeft() / pokemon.getHp(), getHeight());
            }
        });
        panel.add(new JLabel("" + pokemon.getHpLeft() + "/" + pokemon.getHp(), JLabel.RIGHT));

        return panel;
    }

    void setSelected(boolean selected) {
        if (selected) {
            setBackground(AppColor.blue06);
        } else {
            setBackground(AppColor.blue05);
        }
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

}