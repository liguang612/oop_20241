package View.Game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import Data.AppColor;
import Data.AppConstants;
import Model.Pokemon;
import Utils.Utils;

public class PokeDetail extends JPanel {
    private Pokemon pokemon;
    private JLabel genderLabel, idLabel, nameLabel;
    private JPanel idPanel, inforPanel, namePanel, statPanel;

    public PokeDetail() {
        super();

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
        initStat();

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
        idLabel.setFont(idLabel.getFont().deriveFont(48f));
        idPanel.add(idLabel);
        layout.putConstraint(SpringLayout.WEST, idLabel, 6, SpringLayout.EAST, noLabel);
        layout.putConstraint(SpringLayout.SOUTH, idLabel, -4, SpringLayout.SOUTH, idPanel);
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

            layout.putConstraint(SpringLayout.EAST, genderLabel, -6, SpringLayout.EAST, namePanel);
            layout.putConstraint(SpringLayout.NORTH, genderLabel, (namePanel.getHeight() - genderLabel.getHeight()) / 2,
                    SpringLayout.NORTH, namePanel);
        });
    }

    private void initInfor() {
    }

    private void initStat() {
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
