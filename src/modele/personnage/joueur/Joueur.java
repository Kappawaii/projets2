
package modele.personnage.joueur;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.Objet;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Joueur extends Personnage{

	private ArrayList<Objet> inventaire;
	private boolean isControllable;
	ArrayList<Input> inputs;
	boolean didAttack;
	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Animation a, Modele modele, ArrayList<Input> inputs) {
		super(pv, position, 16, vitesse,a, modele);
		this.inventaire = new ArrayList<>(); 
		this.inputs = inputs;
		isControllable = false;
	}

	@Override
	public void jouer() {
		if(isActive && isControllable && inputs != null) {
			int[] movInputs = getMovements(inputs, vitesse);

			if(arme != null) {
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
			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
			else if(didAttack) {
				updateAnimation();
			}
		}
	}

	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}

	public boolean isControllable() {
		return isControllable;
	}

	public void setControllable(boolean isControllable) {
		this.isControllable = isControllable;
	}

}