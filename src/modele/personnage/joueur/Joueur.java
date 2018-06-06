
package modele.personnage.joueur;

import java.util.ArrayList;

import modele.Modele;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.objet.Objet;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Joueur extends Personnage{
	private int vitesse;
	private ArrayList<Objet> inventaire;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Tileset tileset) {
		super(nom, pv, position, 16);
		this.vitesse = vitesse;
		this.inventaire = new ArrayList<>(); 
	}

	public void seDeplace(Axe direction, Modele modele) {
		int nextPosX = position.getX()+direction.x()*vitesse;
		int nextPosY = position.getY()+direction.y()*vitesse;
		if (direction.isMovement()) {
			Collider nextPosCollider = new Collider(new Coordonnee(nextPosX,nextPosY), collider.getTaille(), collider.isTrigger());
			ArrayList<Collider> collisions = nextPosCollider.detecterCollisions(modele.getPlateauCollider(modele.getIdNiveau()));
			boolean result = true;
			for (int i = 0; (i < collisions.size()); i++) {
				if(!collisions.get(i).isTrigger())
					result = false;		
			}
			if (result) {
				this.direction = direction;
				position.setX(nextPosX);
				position.setY(nextPosY);
				collider.setPosition(position);
				updateAnimation();
			}
		}
		else 
			throw new Error("Bad direction parameter : '" + direction +"' Axe.isMovement should be true");
	}

	public int getVitesse() {
		return this.vitesse;
	}

	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}
}
