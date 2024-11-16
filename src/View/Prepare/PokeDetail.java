package View.Prepare;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Data.AppColor;
import Data.AppConstants;
import Model.PokeType;
import Model.Pokemon;
import Model.Skill;
import Utils.Utils;
import View.Share.Column;
import View.Share.Row;
import View.Share.SpriteAnimation;

public class PokeDetail extends JPanel {
    private Column skillColumn;
    private JLabel abilityLabel, genderLabel, idLabel, nameLabel, natureLabel;
    private JPanel idPanel, inforPanel, namePanel, statPanel;
    private Pokemon pokemon;
    private Row typeRow;
    private SpriteAnimation animationPanel;

    public PokeDetail(Pokemon pokemon) {
        super();
        this.pokemon = pokemon;

        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        idPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = ((Graphics2D) g);
                int xPoints[] = { 0, 0, getWidth() / 2, getWidth() / 2 - 50 };
                int yPoints[] = { 0, getHeight(), getHeight(), 0 };

                g2d.setColor(AppColor.gray03);
                g2d.fillPolygon(xPoints, yPoints, xPoints.length);

                super.paintComponent(g);
            }
        };
        idPanel.setOpaque(false);

        statPanel = new JPanel();
        statPanel.setBackground(AppColor.blue03);
        statPanel.setBorder(BorderFactory.createLineBorder(AppColor.gray03, 6));

        namePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = ((Graphics2D) g);
                int[] xPoints = { 0, 0, getWidth(), getWidth() - 30 };
                int[] yPoints = { 0, getHeight(), getHeight(), 0 };

                g2d.setColor(AppColor.gray04);
                g2d.fillPolygon(xPoints, yPoints, xPoints.length);

                super.paintComponent(g);
            }
        };
        namePanel.setOpaque(false);

        inforPanel = new JPanel();
        inforPanel.setBackground(AppColor.white01);
        inforPanel.setBorder(BorderFactory.createLineBorder(AppColor.gray03, 6));
        initInfor();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 10;
        add(idPanel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 100);
        gbc.weighty = 62.5;
        add(statPanel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 10;
        add(namePanel, gbc);

        gbc.gridy = 3;
        gbc.weighty = 17.5;
        add(inforPanel, gbc);

        SwingUtilities.invokeLater(() -> {
            initId();
            initName();
            initStat();
        });
    }

    private void initId() {
        SpringLayout layout = new SpringLayout();
        idPanel.setLayout(layout);

        JLabel noLabel = new JLabel("No ");
        noLabel.setFont(AppConstants.FONT_AGENCYFB);
        idPanel.add(noLabel);
        layout.putConstraint(SpringLayout.WEST, noLabel, 4, SpringLayout.WEST, idPanel);
        layout.putConstraint(SpringLayout.SOUTH, noLabel, -4, SpringLayout.SOUTH, idPanel);

        idLabel = new JLabel(Utils.formatPokeId(0));
        idLabel.setFont(AppConstants.FONT_CHAVA);
        idPanel.add(idLabel);
        layout.putConstraint(SpringLayout.WEST, idLabel, 6, SpringLayout.EAST, noLabel);
        layout.putConstraint(SpringLayout.SOUTH, idLabel, -4, SpringLayout.SOUTH, idPanel);

        JLabel buttonS = new JLabel(": to start", AppConstants.IMG_BUTTON_S, JLabel.RIGHT);
        idPanel.add(buttonS);
        layout.putConstraint(SpringLayout.SOUTH, buttonS, -4, SpringLayout.SOUTH, idPanel);
        layout.putConstraint(SpringLayout.WEST, buttonS, idPanel.getWidth() / 2, SpringLayout.WEST, idPanel);
    }

    private void initName() {
        SpringLayout layout = new SpringLayout();
        namePanel.setLayout(layout);

        nameLabel = new JLabel("Poke_Name");
        nameLabel.setFont(nameLabel.getFont().deriveFont(48f));
        namePanel.add(nameLabel);

        genderLabel = new JLabel(AppConstants.IMG_GENDER_MALE);
        namePanel.add(genderLabel);

        SwingUtilities.invokeLater(() -> {
            layout.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, namePanel);
            layout.putConstraint(SpringLayout.NORTH, nameLabel, (namePanel.getHeight() - nameLabel.getHeight()) / 2,
                    SpringLayout.NORTH, namePanel);

            layout.putConstraint(SpringLayout.EAST, genderLabel, -32, SpringLayout.EAST, namePanel);
            layout.putConstraint(SpringLayout.NORTH, genderLabel, (namePanel.getHeight() - genderLabel.getHeight()) / 2,
                    SpringLayout.NORTH, namePanel);

            namePanel.revalidate();
            namePanel.repaint();
        });
    }

    private void initInfor() {
        inforPanel.setLayout(new GridLayout(2, 1));

        abilityLabel = new JLabel("Ability: " + pokemon.getAbility());
        abilityLabel.setFont(abilityLabel.getFont().deriveFont(48f));
        abilityLabel.setForeground(AppColor.black);
        inforPanel.add(abilityLabel);

        natureLabel = new JLabel("Nature: " + pokemon.getNature());
        natureLabel.setFont(abilityLabel.getFont());
        natureLabel.setForeground(AppColor.black);
        inforPanel.add(natureLabel);
    }

    private void initStat() {
        SpringLayout layout = new SpringLayout();

        statPanel.setLayout(layout);

        List<TypeLabel> typeLabels = new ArrayList<>();
        for (PokeType type : pokemon.getType()) {
            TypeLabel label = new TypeLabel(type.getName(), type.getColor());
            label.setFont(label.getFont().deriveFont(24f));

            typeLabels.add(label);
        }
        typeRow = new Row(8, typeLabels.toArray(new TypeLabel[0]));
        statPanel.add(typeRow);
        layout.putConstraint(SpringLayout.SOUTH, typeRow, -6, SpringLayout.SOUTH, statPanel);
        layout.putConstraint(SpringLayout.WEST, typeRow, 6, SpringLayout.WEST, statPanel);

        List<TypeLabel> skillLabels = new ArrayList<>();
        for (Skill skill : pokemon.getSkills()) {
            TypeLabel label = new TypeLabel(skill.getName(), skill.getType().getColor());
            label.setFont(label.getFont().deriveFont(24f));

            skillLabels.add(label);
        }
        skillColumn = new Column(0, skillLabels.toArray(new JLabel[0]));
        statPanel.add(skillColumn);
        layout.putConstraint(SpringLayout.NORTH, skillColumn, 0, SpringLayout.NORTH, statPanel);
        layout.putConstraint(SpringLayout.EAST, skillColumn, 0, SpringLayout.EAST, statPanel);
        skillColumn.expandChild();

        animationPanel = new SpriteAnimation(
                getClass().getResource("../../assets/animation/bulbasaur.png").getPath(),
                getClass().getResource("../../assets/animation/bulbasaur.json").getPath());
        statPanel.add(animationPanel);
        animationPanel.startAnimation();

        SwingUtilities.invokeLater(() -> {
            layout.putConstraint(SpringLayout.NORTH, animationPanel,
                    (statPanel.getHeight() - animationPanel.getHeight()) / 2, SpringLayout.NORTH, statPanel);
            layout.putConstraint(SpringLayout.WEST, animationPanel,
                    (statPanel.getWidth() - animationPanel.getWidth()) / 2, SpringLayout.WEST, statPanel);

            statPanel.revalidate();
            statPanel.repaint();
        });
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;

        idLabel.setText(Utils.formatPokeId(pokemon.getId()));

        nameLabel.setText(pokemon.getName());

        abilityLabel.setText("Ability: " + pokemon.getAbility());
        natureLabel.setText("Nature: " + pokemon.getNature());

        List<TypeLabel> typeLabels = new ArrayList<>();
        for (PokeType type : pokemon.getType()) {
            TypeLabel label = new TypeLabel(type.getName(), type.getColor());
            label.setFont(label.getFont().deriveFont(24f));

            typeLabels.add(label);
        }
        typeRow.resetChildren(typeLabels.toArray(new TypeLabel[0]));

        List<TypeLabel> skillLabels = new ArrayList<>();
        for (Skill skill : pokemon.getSkills()) {
            TypeLabel label = new TypeLabel(skill.getName(), skill.getType().getColor());
            label.setBackground(skill.getType().getColor());
            label.setFont(label.getFont().deriveFont(24f));

            skillLabels.add(label);
        }
        skillColumn.resetChildren(skillLabels.toArray(new TypeLabel[0]));

        animationPanel.stopAnimation();
        statPanel.remove(animationPanel);
        statPanel.add(animationPanel = pokemon.getAnimation());
        animationPanel.startAnimation();

        SpringLayout layout = ((SpringLayout) statPanel.getLayout());

        SwingUtilities.invokeLater(() -> {
            layout.putConstraint(SpringLayout.NORTH, animationPanel,
                    (statPanel.getHeight() - animationPanel.getHeight()) / 2, SpringLayout.NORTH, statPanel);
            layout.putConstraint(SpringLayout.WEST, animationPanel,
                    (statPanel.getWidth() - animationPanel.getWidth()) / 2, SpringLayout.WEST, statPanel);

            statPanel.revalidate();
            statPanel.repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    class TypeLabel extends JLabel {
        public TypeLabel(String text, Color background) {
            super(text);
            setBackground(background);
            setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            Color backgroundColor = getBackground();
            Color topBorderColor = backgroundColor.brighter();
            Color bottomBorderColor = backgroundColor.darker();

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

            g2.setStroke(new BasicStroke(4));

            g2.setColor(topBorderColor);
            g2.drawLine(4, 2, getWidth() - 4, 2);

            g2.setColor(bottomBorderColor);
            g2.drawLine(4, getHeight() - 2, getWidth() - 4, getHeight() - 2);

            super.paintComponent(g);
        }
    }
}
