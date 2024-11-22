package Controller;

import javax.swing.JPanel;

import Data.AppConstants;
import Model.Pokemon;
import View.Menu.Menu;
import View.Prepare.BuildTeam;
import View.Prepare.Filter;
import View.Prepare.PokeDetail;
import View.Prepare.PokeList;
import View.Prepare.PokeSelected;
import View.Prepare.PokeSelection;
import View.Prepare.Prepare;

public class PrepareController {
    private BuildTeam buildTeam;
    private Filter filter;
    private PokeDetail pokeDetail;
    private PokeList pokeList;
    private PokeSelection pokeSelection;
    private PokeSelected pokeSelected;
    private Prepare prepare;

    public PrepareController() {
        prepare = new Prepare();
        prepare.setController(this);

        pokeSelection = new PokeSelection(this);

        filter = new Filter(this);
        pokeSelected = new PokeSelected(this);
        pokeList = new PokeList(this);
        pokeDetail = new PokeDetail(AppConstants.BULBASAUR);

        pokeSelection.init();

        buildTeam = new BuildTeam(this);
        buildTeam.requestFocusInWindow();
        prepare.setLayeredPane(buildTeam);
    }

    public void backToMenu() {
        new MenuController();
        prepare.setVisible(false);
    }

    public void changePokemon(Pokemon pokemon) {
        pokeDetail.setPokemon(pokemon);
    }

    public void goToGame() {
        prepare.setVisible(false);
        new GameController();
    }

    public void hideFilterPopup(JPanel popup) {
        buildTeam.hideFilterPopup(popup);
    }

    public void showFilterPopup(JPanel popup) {
        buildTeam.showFilterPopup(popup);
    }

    public BuildTeam getBuildTeam() {
        return buildTeam;
    }

    public Filter getFilter() {
        return filter;
    }

    public PokeDetail getPokeDetail() {
        return pokeDetail;
    }

    public PokeList getPokeList() {
        return pokeList;
    }

    public PokeSelection getPokeSelection() {
        return pokeSelection;
    }

    public PokeSelected getPokeSelected() {
        return pokeSelected;
    }

    public Prepare getPrepare() {
        return prepare;
    }
}
