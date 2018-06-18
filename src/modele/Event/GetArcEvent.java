package modele.Event;

import modele.Modele;
import modele.cellule.Cellule;
import modele.personnage.joueur.Joueur;

public class GetArcEvent extends Event {

	Joueur joueur;
	boolean executed = false;
	Cellule[][] cellules;
	int x;
	int y;
	
	public GetArcEvent(Modele modele, Cellule[][] cellules,int x, int y) {
		super(modele);
		this.joueur = modele.getJoueur();
		this.cellules = cellules;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		if(!executed) {
			joueur.obtenirArc();
			cellules[x][y].getSprite().setId(66);
		}
		executed = true;
	}

}