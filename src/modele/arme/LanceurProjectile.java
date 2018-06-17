
package modele.arme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import controleur.Input;
import modele.Modele;
import modele.collision.Collider;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.sprite.Sprite;

public class LanceurProjectile extends Arme {

	//READ-ONLY
	Sprite spr;


	public LanceurProjectile (Modele modele, int degats, Personnage parent) {
		super(modele, degats, parent);
		this.parent = parent;
		this.degats = degats;
		this.modele = modele;
		ticksRemaining = 0;
		idGenerator = new Random();
		spr = new Sprite(modele.getAffichage().getTileset(0), 4, 0);
	}

	public Input attaquer(ArrayList<Input> direction) {
		//instantiation d'une nouvelle attaque si une attaque n'est pas en cours
		if(ticksRemaining == 0) {
			modele.getAffichage().enleverArme(this);
			position.setX(parent.getPosition().getX());
			position.setY(parent.getPosition().getY());
			ticksRemaining--;
			return Input.EMPTY;
		}
		if(ticksRemaining == -1) {
			int[] dirInputs = getDirection(direction);
			//on passe l'attaque si il l'entrée est nulle
			if (dirInputs[0] != 0 || dirInputs[1] != 0) {
				position.setX(parent.getPosition().getX());
				position.setY(parent.getPosition().getY());
				modele.getAffichage().ajouterArme(this);
				idAttaque = idGenerator.nextLong();
				ticksRemaining = 60;
				offsetPos = dirInputs;
				collider = new Collider(
						new Coordonnee(parent.getPosition().getX(),parent.getPosition().getY()),
						true,
						4, 4
						);
			}
		}

		if(ticksRemaining > 0) {
			//mettre a jour position arme
			position.setX(position.getX()+offsetPos[0]*2);
			position.setY(position.getY()+offsetPos[1]*2);
			if(Input.arrayToDirection(new int[] {offsetPos[0],offsetPos[1]}) != null){
				switch (Input.arrayToDirection(new int[] {offsetPos[0],offsetPos[1]})) {
				case DROITE:
					spr.getView().setRotate(0);
					break;
				case GAUCHE:
					spr.getView().setRotate(180);
					break;
				case HAUT:
					spr.getView().setRotate(270);
					break;
				case BAS:
					spr.getView().setRotate(90);
					break;
				default:
					spr.getView().setRotate(0);
					break;
				}
			}
			else {
				int value;
				value = offsetPos[0]*10 + offsetPos[1];
				switch (value) {
				case 11:
					spr.getView().setRotate(45);					
					break;
				case -9:
					spr.getView().setRotate(135);					
					break;
				case -11:
					spr.getView().setRotate(225);					
					break;
				case 9:
					spr.getView().setRotate(315);					
					break;

				default:
					break;
				}
			}

			modele.getAffichage().mettreAJourPositionProjectile(position,spr);
			collider.setPosition(position);
			//on détecte les collisions sur ce collider
			ArrayList<Collider> collisions = collider.detecterCollisions(modele.getAllColliders(modele.getIdNiveau()));

			//on vérifie chaque collisions détectée
			for (Iterator<Collider> iterator = collisions.iterator(); iterator.hasNext();) {
				Collider detectedCollider = iterator.next();

				//si la collision est une collision non matérielle
				if(!detectedCollider.isTrigger() && detectedCollider != parent.getCollider()) {
					detectedCollider.receiveDamage(degats,idAttaque);
				}

			}
			ticksRemaining--;
			if(ticksRemaining > 30)
				return Input.arrayToDirection(new int[] {offsetPos[0],offsetPos[1]});
		}
		return null;
	}

	public Sprite getSpr() {
		return spr;
	}

	@Override
	public String toString() {
		return "Arc Sélectionné";
	}
}
