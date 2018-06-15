package modele.personnage.ennemis;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Gobelin extends Personnage {
	
	public Gobelin(String nom, int pv, Coordonnee position, int taille, Animation a, Modele modele) {
		super(nom, pv, position, taille, 1, a, modele);
	}
	
	public void jouer() {
		ArrayList<Input> actions = new ArrayList<Input>();
		//actions.add(Axe.DROITE);
		actions.add(Input.randomMovement());
		jouer(actions);
	}
	public void attaque(Personnage p) {
		System.out.println("Mon maître est SA-ROUU-MAAAAANE");
	}
}
