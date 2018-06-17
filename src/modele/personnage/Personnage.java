package modele.personnage;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.Entity.Entity;
import modele.animation.Animation;
import modele.arme.Arme;
import modele.collision.Collider;
import modele.collision.EventCollider;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;

public abstract class Personnage extends Entity {

	protected Input direction;
	protected Modele modele;

	protected int pv; // 1 coeur = 4 pv
	protected int vitesse;

	private Animation animation;
	protected Collider collider;

	ArrayList<Long> idAttaques;

	protected boolean isActive;	
	protected boolean dead;
	protected int isDying;
	protected int animOffset = 0;
	protected boolean didAttack;
	//flash rouge dommages
	protected boolean hit = false;

	public Personnage(int pv, Coordonnee position, int taille, int vitesse, Animation a, Modele modele) {
		super(position);
		this.modele = modele;

		this.pv=pv;
		this.vitesse=vitesse;

		animation = a;
		collider = new Collider(position, false, taille, taille);

		idAttaques = new ArrayList<Long>();

		//le personnage est créé désactivé
		setActive(false);
		dead = false;
		isDying = -1;
	}

	public void unTour() {
		if(!dead) {
			animation.unflash();
			jouer();
		}
		else {
			if(isDying == -1) {
				isDying = 10;
				collider = null;
			}
			else if(isDying > 0) {
				isDying --;
				animation.die();
			}
		}
	}

	//TODO Polymorphism jouer();	
	//@Override
	protected abstract void jouer();

	/**
	 * pour les cinématiques, ne prend pas en compte isControllable
	 * @param inputs
	 */
	public void move(ArrayList<Input> inputs) {
		if(isActive && inputs != null) {
			int[] movInputs = getMovements(inputs, vitesse);
			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
		}
	}

	/**
	 * méthode utilisée pour les animations
	 * @param xInput
	 * @param yInput
	 * @param nextPosX
	 * @param nextPosY
	 */
	protected void moveAndAnimate(int xInput, int yInput, int nextPosX, int nextPosY) {
		//on crée un collider fictif
		Collider nextPosCollider = new Collider(
				new Coordonnee(nextPosX,nextPosY),
				collider.isTrigger(),
				collider.getTailleX(), collider.getTailleY()
				);

		//on détecte les collisions sur ce collider fictif
		ArrayList<Collider> collisions = nextPosCollider.detecterCollisions(modele.getAllColliders(modele.getIdNiveau()));

		boolean pasDeCollisionMaterielle = true;
		//tri des colliders récupérés
		for (int i = 0; (i < collisions.size()); i++) {

			//pas d'autocollision
			if(!collider.equals(collisions.get(i))) {
				//si le collider n'est pas un trigger, on enregistre une collision matérielle
				if(!collisions.get(i).isTrigger()) {
					pasDeCollisionMaterielle = false;
				}
				//si le collider est un collider évenementiel, on active sa méthode triggerEvent()
				if(collisions.get(i) instanceof EventCollider)
					((EventCollider) collisions.get(i)).triggerEvent();
			}

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

	protected void animationAttackToMovement() {
		getAnimation().animate(Input.directionToInt(direction));
	}

	protected void updateAnimation() {

		if(isActive) {
			if(direction != null) {
				int[] orientation = direction.directiontoArray();
				orientation[0] = orientation[0]*10 + orientation[1];
				switch (orientation[0]) {
				case 10: case 11: case 9:
					animation.animate(0+animOffset);
					break;
				case 1:
					animation.animate(1+animOffset);
					break;
				case -10: case -11: case -9:
					animation.animate(2+animOffset);
					break;			
				case -1:
					animation.animate(3+animOffset);
					break;
				}
			}
			animation.nextFrame();
		}
	}

	public void receiveDamage(int dmg, long id) {
		if(!idAttaques.contains(id)) {
			animation.flash();
			idAttaques.add(id);
			pv -= dmg;
			//mort
			if(pv < 0) {
				dead = true;
			}
		}
	}

	public Collider getCollider() {
		return collider;
	}

	public Animation getAnimation() {
		return animation;
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
		if(pv > 0) {
			return true;
		}
		return false;
	}

	//TODO Calcul distance ici
	//	public void attaquePersoVisible(Joueur joueur) {
	//		double distance = Math.sqrt(Math.pow((this.getPosition().getX()-joueur.getPosition().getX()), 2) + Math.pow((this.getPosition().getY()-joueur.getPosition().getY()), 2));
	//		if(this.isAlive() && distance < 4) {
	//			this.attaque(joueur);
	//		}
	//	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void setActive(boolean b) {
		animation.setVisible(b);
		isActive = b;
	}

	public boolean getActive() {
		return isActive;
	}

	public void attaquer(ArrayList<Input> inputs, Arme arme) {
		Input armeDirection = arme.attaquer(inputs);
		if( armeDirection != null && direction != null) {
			//décalage animation déplacement
			switch (direction) {
			case DROITE:
				animOffset = 4;
				didAttack = true;
				break;
			case BAS:
				animOffset = 3;
				didAttack = true;
				break;
			case GAUCHE:
				animOffset = 2;
				didAttack = true;
				break;
			case HAUT:
				animOffset = 1;
				didAttack = true;
				break;
			case EMPTY:
				animOffset = 0;
				didAttack = true;
				break;
			default:
				break;
			}

			//décalage animation attaque
			switch (armeDirection) {
			case DROITE:
				animOffset += 0;
				didAttack = true;
				break;
			case BAS:
				animOffset += 1;
				didAttack = true;
				break;
			case GAUCHE:
				animOffset += 2;
				didAttack = true;
				break;
			case HAUT:
				animOffset += 3;
				didAttack = true;
			case EMPTY:
				animOffset += 0;
				didAttack = true;
				break;
			default:
				break;
			}
		}
		else if(didAttack) {
			didAttack = false;
			animationAttackToMovement();
			animOffset = 0;
		}
	}

}