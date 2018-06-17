package modele.Event;

import modele.Bateau;
import modele.Modele;
import modele.cellule.Cellule;
import modele.personnage.joueur.Joueur;

public class GetBateauEvent extends Event {

	Joueur joueur;
	boolean executed = false;
	Cellule[][] cellules;
	int x;
	int y;
	
	public GetBateauEvent(Modele modele, Cellule[][] cellules,int x, int y) {
		super(modele);
		this.joueur = modele.getJoueur();
		this.cellules = cellules;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		if(!executed) {
			joueur.obtenirObjet(new Bateau());
			cellules[x][y].getSprite().setId(66);
			System.out.println("done");
		}
	}

}
