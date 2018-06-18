
package modele.personnage.ennemis;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.arme.Arme;
import modele.cellule.Cellule;
import modele.chemin.PathFinding;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import modele.plateau.Plateau;

public class Gobelin extends Personnage {

	Arme epee;
	Cellule pathMemory;

	public Gobelin(String nom, int pv, Coordonnee position, int taille, Animation a, Modele modele) {
		super(pv, position, taille, 1, a, modele);
		super.collider.setParent(this);
		epee = new Arme(modele, 3, this);
	}

	@Override
	public void jouer() {
		if(isActive) {
			Plateau map = this.modele.getCurrentNiveau().getPlateau();
			if(pathMemory != null) {
//				System.out.println(1);
				if(this.getPosition().equals(pathMemory.getPosition())) {
//					System.out.println(2);
					pathMemory = null;
				}
			}
			if (pathMemory == null) {
				PathFinding path = new PathFinding(modele, map.getCellule((this.getPosition().getX())/16, (this.getPosition().getY())/16),
						map.getCellule(
								modele.getJoueur().getPosition().getX()/16,
								modele.getJoueur().getPosition().getY()/16)
						);
				pathMemory = path.chemin().get(0);
			}


			ArrayList<Input> inputs = new ArrayList<Input>();

			if(pathMemory.getPosition().getX() > this.getPosition().getX()) {			//Droite
				inputs.add(Input.DROITE);
//				System.out.println("droite");
			}
			else if(pathMemory.getPosition().getX() < this.getPosition().getX()) {			//Gauche
				inputs.add(Input.GAUCHE);
//				System.out.println("gauche");
			}
			else if(pathMemory.getPosition().getY() < this.getPosition().getY()) {			//Haut
				inputs.add(Input.HAUT);
//				System.out.println("haut");
			}
			else if(pathMemory.getPosition().getY() > this.getPosition().getY()) {			//Bas
				inputs.add(Input.BAS);
//				System.out.println("bas");
			}
//			System.out.println(this.getPosition());
//			System.out.println("mem" + pathMemory.getPosition());
//			System.out.println("\n\n\n\n\n\n\n");

			int[] movInputs = getMovements(inputs, vitesse);

			for (int i = 0; i < movInputs.length; i++) {
				movInputs[i] = Math.max(-1, movInputs[i]);
				movInputs[i] = Math.min(1, movInputs[i]);
			}

			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}

			if(epee != null) {
				super.attaquer(directionAttack(modele.getJoueur()),epee);
			}
		}
	}
}