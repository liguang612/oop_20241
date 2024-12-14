package View.Game;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

import Controller.GameController;
import Model.Pokemon;
import View.Game.Widget.PokeChange;

public class ChangePokemon extends JPanel {
    private CardLayout layout;

    public ChangePokemon(GameController controller) {
        super();

        setLayout(layout = new CardLayout(10, 10));
        setOpaque(false);

        for (Pokemon pokemon : controller.getOurPokemons()) {
            add(new PokeChange(pokemon));
        }
    }

    public void next() {
        layout.next(this);
    }

    public Pokemon getPokemon() {
        for (Component comp : getComponents()) {
            if (comp.isVisible()) {
                return ((PokeChange) comp).getPokemon();
            }
        }

        return null;
    }
}
