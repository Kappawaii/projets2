package modele.personnage.ennemis;

import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import vue.tileset.Tileset;

public class Plante extends Personnage {

	public Plante(String nom, Coordonnee position, int vitesse, Tileset tileset, Animation a, Modele modele) {
		super(5, position, 16, vitesse, a, modele);
	}

	public void jouer() {

		if(modele.getJoueur().getPosition().getX()/16 >= (this.getPosition().getX()/16)-3 && (modele.getJoueur().getPosition().getX())/16 <= (this.getPosition().getX()/16)+3 && // attaque � partir d'une
				modele.getJoueur().getPosition().getY()/16 >= (this.getPosition().getY()/16)-3 && (modele.getJoueur().getPosition().getY())/16 <= (this.getPosition().getY()/16)+3) { // certaine distance
			this.attaque(modele.getJoueur());	
		}

	}

	public void attaque(Personnage p) {
		System.out.println("Que le soleil vous détruise");
	}

	//TODO Polymorphism	

}