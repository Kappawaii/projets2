package modele.Event;

import modele.Modele;
import modele.coordonnee.Coordonnee;
import modele.personnage.joueur.Joueur;
import vue.sprite.Sprite;

public class GetArcEvent extends Event {

	Joueur joueur;
	boolean executed = false;
	Sprite arc;

	public GetArcEvent(Modele modele, Coordonnee positionSpr) {
		super(modele);
		this.joueur = modele.getJoueur();
		arc = new Sprite(modele.getAffichage().getTileset(0), 4, 68);
		modele.getAffichage().getTuiles().getChildren().add(arc.getView());
	}

	@Override
	public void execute() {
		if(!executed) {
			joueur.obtenirArc();
			modele.getAffichage().getTuiles().getChildren().remove(arc.getView());
		}
		executed = true;
	}

}
