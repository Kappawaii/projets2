package modele.personnage.ennemis;

import javafx.animation.Timeline;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import vue.tileset.Tileset;

public class Plante extends Personnage {

	private int vitesse;
	
	public Plante(String nom, Coordonnee position, int vitesse, Tileset tileset, Animation a) {
		super(nom, 5, position, 16, a);
		this.vitesse=vitesse;
	}

	
	public boolean isAlive() {
		if(this.getPV() > 0) {
			return true;
		}
		return false;
	}
	
	
	
	public void attaquePersoVisible(Joueur joueur) {
		double distance = Math.sqrt(Math.pow((this.getPosition().getX()-joueur.getPosition().getX()), 2) + Math.pow((this.getPosition().getY()-joueur.getPosition().getY()), 2));
		if(this.isAlive() && distance < 4) {
			this.attaque(joueur);
		}
	}
	
	
	
	public void attaque(Personnage p) {
		System.out.println("Que le soleil vous détruise");
	}
	
	
	
	

}
