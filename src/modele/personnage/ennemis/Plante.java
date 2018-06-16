package modele.personnage.ennemis;

import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Plante extends Personnage {

	public Plante(String nom, Coordonnee position, Animation a, Modele modele) {
		super(20, position, 16, 0, a, modele);
		super.collider.setParent(this);
	}

	public void jouer() {
		System.out.println(getPosition());
		if(modele.getJoueur().getPosition().getX()/16 >= (this.getPosition().getX()/16)-3&& (modele.getJoueur().getPosition().getX())/16 <= (this.getPosition().getX()/16)+3 && // attaque à partir d'une
				modele.getJoueur().getPosition().getY()/16 >= (this.getPosition().getY()/16)-3 && (modele.getJoueur().getPosition().getY())/16 <= (this.getPosition().getY()/16)+3) { // certaine distance
			this.attaque(modele.getJoueur());	
		}
	}

	public void attaque(Personnage p) {
		System.out.println("Que le soleil vous détruise");
	}

}