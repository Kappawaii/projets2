package modele.personnage.joueur;

import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Joueur extends Personnage{
	
	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Tileset tileset) {
		super(nom, pv, position, vitesse, tileset);
	}

}
