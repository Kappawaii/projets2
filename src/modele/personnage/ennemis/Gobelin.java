package modele.personnage.ennemis;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Gobelin extends Personnage {

	public Gobelin(String nom, int pv, Coordonnee position, int taille, Animation a, Modele modele) {
		super(pv, position, taille, 1, a, modele);
	}

	public void jouer() {
		ArrayList<Input> inputs = new ArrayList<Input>();
		//actions.add(Axe.DROITE);

		if(isActive && inputs != null) {

			inputs.add(Input.randomMovement());	
			int[] movInputs = getMovements(inputs, vitesse);

			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
		}
	}
	
	public void attaque(Personnage p) {
		System.out.println("Mon maître est SA-ROUU-MAAAAANE");
	}
}