package modele.arme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import controleur.Input;
import modele.Modele;
import modele.Entity.Entity;
import modele.collision.Collider;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Arme extends Entity {

	//READ-ONLY
	Personnage parent;

	Modele modele;
	Collider collider;
	int degats;
	int ticksRemaining;
	int[] offsetPos;
	long idAttaque;
	Random idGenerator;
	
	public Arme (Modele modele, int degats,Personnage parent) {
		super(new Coordonnee(parent.getPosition().getX(),parent.getPosition().getY()));
		this.parent = parent;
		this.degats = degats;
		this.modele = modele;
		ticksRemaining = 0;
		idGenerator = new Random();
	}

	public Input attaquer(ArrayList<Input> direction) {
		//instantiation d'une nouvelle attaque si une attaque n'est pas en cours
		if(ticksRemaining == 0) {
			ticksRemaining--;
			return Input.EMPTY;
		}
		if(ticksRemaining == -1) {
			int[] dirInputs = getDirection(direction);
			//on passe l'attaque si il l'entrée est nulle
			if (dirInputs[0] != 0 || dirInputs[1] != 0) {
				idAttaque = idGenerator.nextLong();
				ticksRemaining = 20;
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
			position.setX(parent.getPosition().getX()+offsetPos[0]*16);
			position.setY(parent.getPosition().getY()+offsetPos[1]*16);
			collider.setPosition(position);
			//on détecte les collisions sur ce collider
			ArrayList<Collider> collisions = collider.detecterCollisions(modele.getAllColliders(modele.getIdNiveau()));

			//on vérifie chaque collisions détectée
			for (Iterator<Collider> iterator = collisions.iterator(); iterator.hasNext();) {
				Collider detectedCollider = iterator.next();

				//si la collision est une collision non matérielle
				if(!detectedCollider.isTrigger()) {
					detectedCollider.receiveDamage(degats,idAttaque);
				}

			}
			ticksRemaining--;
			return Input.arrayToDirection(new int[] {offsetPos[0],offsetPos[1]});
		}
		return null;
	}
	
	public int getTicksRemaining() {
		return ticksRemaining;
	}

	@Override
	public String toString() {
		return "Epee Sélectionnée";
	}
}
