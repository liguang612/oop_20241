package View.Prepare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import Controller.PrepareController;
import Data.AppColor;
import Data.AppConstants;
import Model.PokeType;
import View.Share.ChoiceList;
import View.Share.RoundPanel;

@SuppressWarnings("rawtypes")

public class Filter extends RoundPanel implements FocusListener, KeyListener {
    private PrepareController controller;

    private int mode = 0;
    private JLabel gen, type, caught, unlocks, misc, sort;
    private JPopupMenu popup;
    private ChoiceList caughtList, genList, miscList, sortList, typeList, unlocksList;
    private RoundPanel mainPanel;

    public Filter(PrepareController controller) {
        super(AppConstants.BORDER_RADIUS, 4, Color.BLACK, AppColor.red01);
        this.controller = controller;

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setFocusable(true);
        setLayout(new GridLayout(1, 1));
        setPreferredSize(new Dimension((int) (controller.getPokeSelection().getPreferredSize().width),
                (int) (controller.getPokeSelection().getPreferredSize().height * 0.105)));

        mainPanel = new RoundPanel(AppConstants.BORDER_RADIUS, 0, null, AppColor.blue02);
        mainPanel.setLayout(new GridLayout(1, 6));
        add(mainPanel);

        gen = new JLabel("Gen", JLabel.CENTER);
        gen.setForeground(Color.WHITE);
        gen.setFont(AppConstants.FONT_CONSOLAS);
        mainPanel.add(gen);

        genList = new ChoiceList<String>(this, "All", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");

        type = new JLabel("Type", JLabel.CENTER);
        type.setForeground(gen.getBackground());
        type.setFont(gen.getFont());
        mainPanel.add(type);

        typeList = new ChoiceList<PokeType>(this, PokeType.values());

        caught = new JLabel("Caught", JLabel.CENTER);
        caught.setForeground(gen.getBackground());
        caught.setFont(gen.getFont());
        mainPanel.add(caught);

        caughtList = new ChoiceList<String>(this, "All", "RED_BALL", "BLUE_BALL", "YELLOW_BALL", "Not shiny",
                "Uncaught");

        unlocks = new JLabel("Unlocks", JLabel.CENTER);
        unlocks.setForeground(gen.getForeground());
        unlocks.setFont(gen.getFont());
        mainPanel.add(unlocks);

        unlocksList = new ChoiceList<String>(this, "Passive", "Cost Reduction");

        misc = new JLabel("Misc", JLabel.CENTER);
        misc.setForeground(gen.getForeground());
        misc.setFont(gen.getFont());
        mainPanel.add(misc);

        miscList = new ChoiceList<String>(this, "Favourite", "Ribbon", "Hidden Ability", "Egg", "Pokerús");

        sort = new JLabel("Sort", JLabel.CENTER);
        sort.setForeground(gen.getForeground());
        sort.setFont(gen.getFont());
        mainPanel.add(sort);

        sortList = new ChoiceList<String>(this, "No.", "Cost", "Candy Count", "IVs", "Name");

        popup = new JPopupMenu();

        addFocusListener(this);
        addKeyListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
        switch (mode) {
            case 0:
                controller.hideFilterPopup(genList);
                break;
            case 1:
                controller.hideFilterPopup(typeList);
                break;
            case 2:
                controller.hideFilterPopup(caughtList);
                break;
            case 3:
                controller.hideFilterPopup(unlocksList);
                break;
            case 4:
                controller.hideFilterPopup(miscList);
                break;
            case 5:
                controller.hideFilterPopup(sortList);
                break;
            default:
                break;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        ((JLabel) mainPanel.getComponent(mode)).setIcon(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ((JLabel) mainPanel.getComponent(mode--)).setIcon(null);

            mode = (mode + 6) % 6;
            ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ((JLabel) mainPanel.getComponent(mode++)).setIcon(null);

            mode %= 6;
            ((JLabel) mainPanel.getComponent(mode)).setIcon(AppConstants.IMG_CURSOR);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.getPokeList().requestFocusInWindow();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            switch (mode) {
                case 0:
                    controller.showFilterPopup(genList);
                    break;
                case 1:
                    controller.showFilterPopup(typeList);
                    break;
                case 2:
                    controller.showFilterPopup(caughtList);
                    break;
                case 3:
                    controller.showFilterPopup(unlocksList);
                    break;
                case 4:
                    controller.showFilterPopup(miscList);
                    break;
                case 5:
                    controller.showFilterPopup(sortList);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}