package Controller;

import javax.swing.JPanel;

import Data.AppConstants;
import Model.Pokemon;
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

    private int totalIVs = 0;

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

    public boolean checkEnable(Pokemon pokemon) {
        return totalIVs + pokemon.getIVs() <= 10;
    }

    public boolean selectPoke(int index) {
        Pokemon pokemon = AppConstants.ALL_OF_POKEMONS.get(index);
        if (pokemon.getIVs() + totalIVs > 10)
            return false;

        totalIVs += pokemon.getIVs();
        pokeSelected.selectPoke(pokemon);

        return true;
    }

    public void unselectPoke(int index) {
        Pokemon pokemon = AppConstants.ALL_OF_POKEMONS.get(index);

        totalIVs -= pokemon.getIVs();
        pokeSelected.unselectPoke(pokemon);
    }

    public void backToMenu() {
        new MenuController();
        prepare.setVisible(false);
    }

    public void changePokemon(Pokemon pokemon) {
        pokeDetail.setPokemon(pokemon);
    }

    public void goToGame() {
        new GameController(pokeSelected.getPokemons());
        prepare.setVisible(false);
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

    public int getTotalIVs() {
        return totalIVs;
    }
}
