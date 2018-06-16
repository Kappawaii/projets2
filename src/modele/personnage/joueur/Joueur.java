
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
	
	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Animation a, Modele modele, ArrayList<Input> inputs) {
		super(pv, position, 16, vitesse,a, modele);
		this.inventaire = new ArrayList<>(); 
		this.inputs = inputs;
		isControllable = false;
	}

	@Override
	public void jouer() {
		System.out.println(inputs);
		if(isActive && isControllable && inputs != null) {
			int[] movInputs = getMovements(inputs, vitesse);
			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
			if(arme != null)
				arme.executer(inputs);
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
	
	//TODO Polymorphism




}
