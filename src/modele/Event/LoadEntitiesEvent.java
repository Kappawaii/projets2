package modele.Event;

import java.util.ArrayList;

import modele.Modele;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class LoadEntitiesEvent extends Event {

	ArrayList<Personnage> personnage;
	Coordonnee[] coordonnee;
	
	boolean executed = false;
	public LoadEntitiesEvent(Modele modele, ArrayList<Personnage> arrayList, Coordonnee[] coordonnees) {
		super(modele);
		this.personnage = arrayList;
		this.coordonnee = coordonnees;
	}

	@Override
	public void execute() {
		if(!executed) {
			for (int i = 0;i < personnage.size(); i++) {
				Personnage p = personnage.get(i);
				Coordonnee c = coordonnee[i];
				modele.getCurrentNiveau().getEntites().add(p);
				modele.getAffichage().ajouterPersonnage(p);
				p.setPosition(c);
				p.setActive(true);
			}

			executed = true;
		}
	}
}
