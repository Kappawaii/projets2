package modele.personnage.ennemis;

import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Plante extends Personnage {
	
	public Plante(String nom, Coordonnee position, int vitesse, Tileset tileset, Animation a, Modele modele) {
		super(5, position, 16, vitesse, a, modele);
	}

	@Override
	public void jouer() {
		// TODO Auto-generated method stub
		
	}

}