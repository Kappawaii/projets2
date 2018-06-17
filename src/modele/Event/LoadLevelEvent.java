package modele.Event;

import modele.Modele;
import modele.coordonnee.Coordonnee;

public class LoadLevelEvent extends Event {

	int idNewNiveau;
	Coordonnee newPosJoueur;
	public LoadLevelEvent(Modele modele,int niveau,Coordonnee newPosJoueur) {
		super(modele);
		this.idNewNiveau = niveau;
		this.newPosJoueur = newPosJoueur;
	}
	
	@Override
	public void execute() {
		modele.changerMap(idNewNiveau, false);
		
		modele.getJoueur().setPosition(newPosJoueur);
	}
}
