package View.Game.Widget;

import java.awt.Dimension;
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
    // Model
    private Pokemon pokemon;

    // UI
    private JLabel nameLabel, levelLabel, hpLeftLabel, manaLabel;
    private JPanel typePanel;

    public PokeHP(Pokemon pokemon) {
        super();
        this.pokemon = pokemon;

        setBackground(AppColor.black02);
        setBorder(BorderFactory.createLineBorder(AppColor.black01, 1, true));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel hpPanel = new JPanel(new GridLayout(2, 1));

        JPanel topHPPanel = new JPanel(new GridLayout(1, 2, 15, 15));

        nameLabel = new JLabel();
        nameLabel.setForeground(AppColor.black);
        topHPPanel.add(nameLabel);

        levelLabel = new JLabel();
        levelLabel.setForeground(AppColor.black);
        topHPPanel.add(levelLabel);

        hpPanel.add(topHPPanel);

        JPanel bottomHPPanel = new JPanel(new GridLayout(1, 2)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                double percent = (double) pokemon.getHpLeft() / pokemon.getHp();

                g2d.setColor(AppColor.green02);
                g2d.fillRect(0, 0,
                        (int) (getWidth() * percent), getHeight());

                g2d.setColor(AppColor.white);
                g2d.fillRect((int) (getWidth() * percent), 0,
                        (int) (getWidth() * (1 - percent)), getHeight());
            }

            @Override
            public void setPreferredSize(Dimension preferredSize) {
                Dimension d = new Dimension(pokemon.getHp() * 5, ((int) preferredSize.getHeight()));
                super.setPreferredSize(d);
            }
        };
        hpPanel.add(bottomHPPanel);

        manaLabel = new JLabel("" + pokemon.getMana());
        manaLabel.setForeground(AppColor.blue);
        bottomHPPanel.add(manaLabel);

        hpLeftLabel = new JLabel("" + pokemon.getHpLeft(), JLabel.RIGHT);
        hpLeftLabel.setForeground(AppColor.red);
        bottomHPPanel.add(hpLeftLabel);

        typePanel = new JPanel(new GridLayout(1, pokemon.getType().length));

        initUI();

        add(hpPanel);
        add(typePanel);
    }

    private void initUI() {
        nameLabel.setText(pokemon.getName());
        levelLabel.setText("Lv. " + pokemon.getIVs());
        manaLabel.setText("" + pokemon.getMana());
        hpLeftLabel.setText("" + pokemon.getHpLeft());

        typePanel.removeAll();
        for (PokeType type : pokemon.getType()) {
            JLabel label = new JLabel("  ");
            label.setBackground(type.getColor());
            label.setOpaque(true);

            typePanel.add(label);
        }
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        initUI();

        revalidate();
        repaint();
    }
}
