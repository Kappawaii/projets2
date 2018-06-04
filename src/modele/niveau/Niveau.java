package modele.niveau;

import java.util.ArrayList;
import modele.plateau.Plateau;

public class Niveau {

	private int id;
	Plateau plateau;
	ArrayList<Entity> entites;
	
	public Niveau() {
		entites = new ArrayList<Entity>();
	}
	
	public Niveau(Plateau plateau) {
		this();
		this.plateau = plateau;
	}
	
	public Niveau(Plateau plateau, ArrayList<Entity> liste) {
		this(plateau);
		for (int i = 0; i < liste.size(); i++) {
			entites.add(liste.get(i));
		}
	}
	
}
