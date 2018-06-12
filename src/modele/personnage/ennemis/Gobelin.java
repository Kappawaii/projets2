package modele.personnage.ennemis;

import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Gobelin extends Personnage {
	
	public Gobelin(String nom, Coordonnee position, int vitesse, Tileset tileset, Animation a) {
		super(nom, 10, position, 16,vitesse, a);
	}
	
	public void attaque(Personnage p) {
		System.out.println("Mon maître est SA-ROUU-MAAAAANE");
	}
}
