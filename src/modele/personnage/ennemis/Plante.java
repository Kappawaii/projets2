package modele.personnage.ennemis;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.arme.LanceurProjectile;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Plante extends Personnage {
	
	private LanceurProjectile pois;

	public Plante(Coordonnee position, Animation a, Modele modele, int pv) {
		super(pv, position, 16, 0, a, modele);
		super.collider.setParent(this);
		pois = new LanceurProjectile(modele,2, this);
	}

	public void jouer() {
		collider.setPosition(position);
		if(pois.getTicksRemaining() != -1 || (modele.getJoueur().getPosition().getX()/16 >= (this.getPosition().getX()/16)-3&& (modele.getJoueur().getPosition().getX())/16 <= (this.getPosition().getX()/16)+3 && // attaque à partir d'une
				modele.getJoueur().getPosition().getY()/16 >= (this.getPosition().getY()/16)-3 && (modele.getJoueur().getPosition().getY())/16 <= (this.getPosition().getY()/16)+3)) { // certaine distance
			this.attaque(modele.getJoueur());
		}
	}

	public void attaque(Personnage p) {
		ArrayList<Input> directions = new ArrayList<Input>();
		directions.add(Input.randomDirection());
		directions.add(Input.randomDirection());
		pois.attaquer(directions);
	}
}