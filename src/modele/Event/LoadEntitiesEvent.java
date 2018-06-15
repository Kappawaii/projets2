package modele.Event;

import java.util.ArrayList;

import modele.Modele;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class LoadEntitiesEvent extends Event {

	ArrayList<Personnage> personnage;
	ArrayList<Coordonnee> coordonnee;
	
	boolean executed = false;
	public LoadEntitiesEvent(Modele modele, ArrayList<Personnage> arrayList, ArrayList<Coordonnee> arrayListPos) {
		super(modele);
		this.personnage = arrayList;
		this.coordonnee = arrayListPos;
	}

	@Override
	public void execute() {
		if(!executed) {
			System.out.println("executed");
			for (int i = 0;i < personnage.size(); i++) {
				Personnage p = personnage.get(i);
				Coordonnee c = coordonnee.get(i);
				modele.getCurrentNiveau().getEntites().add(p);
				modele.getAffichage().ajouterPersonnage(p);
				p.setPosition(c);
				p.setActive(true);
			}

			executed = true;
		}
	}
}
