
package modele.personnage.joueur;

import java.util.ArrayList;

import modele.Modele;
import modele.animation.Animation;
import modele.collision.Collider;
import modele.collision.EventCollider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.objet.Objet;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Joueur extends Personnage{
	private ArrayList<Objet> inventaire;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Tileset tileset, Animation a) {
		super(nom, pv, position, 16,vitesse, a);
		this.inventaire = new ArrayList<>(); 
	}

	public void seDeplace(Axe direction, Modele modele) {
		int nextPosX = position.getX()+direction.x()*vitesse;
		int nextPosY = position.getY()+direction.y()*vitesse;
		if (direction.isMovement()) {
			Collider nextPosCollider = new Collider(new Coordonnee(nextPosX,nextPosY), collider.isTrigger(), collider.getTailleX(), collider.getTailleY());
			ArrayList<Collider> collisions = nextPosCollider.detecterCollisions(modele.getPlateauCollider(modele.getIdNiveau()));
			boolean result = true;
			for (int i = 0; (i < collisions.size()); i++) {
				if(!collisions.get(i).isTrigger()) {
//					System.out.println("collision avec" + collisions.get(i));
					if(collisions.get(i) instanceof EventCollider)
					((EventCollider) collisions.get(i)).triggerEvent();
					result = false;		
				}

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

	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}
	
	public void attaque(Personnage p) {
		System.out.println("Je t'attaque "+p.getNom());
	}
}
