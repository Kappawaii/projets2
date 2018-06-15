package modele.personnage;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.Entity.Entity;
import modele.animation.Animation;
import modele.collision.Collider;
import modele.collision.EventCollider;
import modele.coordonnee.Coordonnee;
import modele.personnage.joueur.Joueur;
import vue.sprite.Sprite;

public abstract class Personnage extends Entity {

	private Animation animation;
	protected Collider collider;
	protected Input direction;
	private int pv;
	protected int vitesse;
	protected Modele modele;
	private boolean isActive;

	public Personnage(String nom, int pv, Coordonnee position, int taille, int vitesse, Animation a, Modele modele) {
		this.modele = modele;
		super.nom = nom;
		super.position = position;
		animation = a;
		collider = new Collider(position, false, taille, taille);
		this.pv=pv;
		this.vitesse=vitesse;
		isActive = false;
	}

	public void seDeplace(ArrayList<Input> inputs) {
		if(isActive && inputs != null) {
			int xInput = 0;
			int yInput = 0;
			//somme des entrées de mouvement
			for (int i = 0; i < inputs.size(); i++) {
				if(inputs.get(i).isMovement()) {
					xInput += inputs.get(i).x()*vitesse;
					yInput += inputs.get(i).y()*vitesse;
				}
			}
			//calcul de la prochaine position
			int nextPosX = position.getX() + xInput;
			int nextPosY = position.getY() + yInput;

			//on passe les tests si il n'y a pas de mouvement
			if (xInput != 0 || yInput != 0) {

				//on crée un collider fictif
				Collider nextPosCollider = new Collider(new Coordonnee(nextPosX,nextPosY), collider.isTrigger(), collider.getTailleX(), collider.getTailleY());
				//on détecte les collisions sur ce collider fictif
				ArrayList<Collider> collisions = nextPosCollider.detecterCollisions(modele.getAllColliders(modele.getIdNiveau()));

				boolean pasDeCollisionMaterielle = true;
				//tri des colliders récupérés
				for (int i = 0; (i < collisions.size()); i++) {
					//si le collider n'est pas un trigger, on enregistre une collision matérielle
					if(!collisions.get(i).isTrigger())
						pasDeCollisionMaterielle = false;		
					//si le collider est un collider évenementiel, on active sa méthode triggerEvent()
					if(collisions.get(i) instanceof EventCollider)
						((EventCollider) collisions.get(i)).triggerEvent();

				}
				//si il n'y a pas de collision matérielle, le personnage peut se déplacer
				if (pasDeCollisionMaterielle) {
					//calcul de la direction du personnage en fonction du déplacement
					this.direction = Input.arrayToDirection(new int[]{xInput,yInput});					
					//changement de position du personnage
					position.setX(nextPosX);
					position.setY(nextPosY);
					//actualisation de la position du collider
					collider.setPosition(position);
					//animation du personnage
					updateAnimation();
				}
			}
		}
	}

	public void updateAnimation() {
		//si le personnage es
		if(isActive) {
			if(direction != null) {
				int[] orientation = direction.directiontoArray();
				orientation[0] = orientation[0]*10 + orientation[1];

				switch (orientation[0]) {
				case 10: case 11: case 9:
					animation.animate(0);
					break;
				case 1:
					animation.animate(1);
					break;
				case -10: case -11: case -9:
					animation.animate(2);
					break;			
				case -1:
					animation.animate(3);
					break;
				}
			}
			animation.nextFrame();
		}
	}

	public Collider getCollider() {
		return collider;
	}

	public Animation getAnimation() {
		return animation;
	}

	public String getNom() {
		return nom;
	}

	public Coordonnee getPosition() {
		return this.position;
	}

	public Sprite getSprite() {
		return animation.getSprite();
	}

	public int getPV() {
		return this.pv;
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

	public abstract void attaque(Personnage p);

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void setActive(boolean b) {
		isActive = b;
	}
	
	public boolean getActive() {
		return isActive;
	}
}