package modele.Event;

import java.util.ArrayList;

import modele.Modele;
import modele.personnage.Personnage;

public class LoadEntitiesEvent extends Event {

	ArrayList<Personnage> personnage;
	boolean executed = false;
	public LoadEntitiesEvent(Modele modele, ArrayList<Personnage> arrayList) {
		super(modele);
		this.personnage = arrayList;
	}

	@Override
	public void execute() {
		if(!executed) {
			System.out.println("executed");
			for (Personnage p : personnage) {
				modele.getCurrentNiveau().getEntites().add(p);
				modele.getAffichage().ajouterEntite(p);
			}

			executed = true;
		}
	}
}
