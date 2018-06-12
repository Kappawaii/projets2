package modele.Event;

import modele.Modele;
import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import vue.tileset.Tileset;

public class LoadLevelEvent extends Event {

	int idNewNiveau;

	public LoadLevelEvent(Modele modele,int niveau) {
		super(modele);
		idNewNiveau = niveau;
	}
	
	@Override
	public void execute() {
		//debugMode activé ici
		modele.changerMap(idNewNiveau, true);
		Cellule cell = new Cellule(
						new Tileset("sprites/tilesets/tileset0.png",4),
						1, 
						390, 160, 
						false, 
						4, 
						0, 0, 
						4, 4, 
						null);
		modele.getAffichage().getTuiles().getChildren().add(cell.getSprite().getView());
		modele.getJoueur().setPosition(new Coordonnee(16,16));
	}
}
