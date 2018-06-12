package modele.personnage.ennemis;

import java.util.ArrayList;

import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Gobelin extends Personnage {
	
	public Gobelin(String nom, int pv, Coordonnee position, int taille, Animation a, Modele modele) {
		super(nom, pv, position, taille, 1, a, modele);
	}
	
	public void jouer() {
		ArrayList<Axe> actions = new ArrayList<Axe>();
		//actions.add(Axe.DROITE);
		actions.add(Axe.getRandomMovement());
		seDeplace(actions);
	}
	public void attaque(Personnage p) {
		System.out.println("Mon maître est SA-ROUU-MAAAAANE");
	}
}
