
package modele.personnage.ennemis;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.cellule.Cellule;
import modele.chemin.PathFinding;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.Plateau;

public class Gobelin extends Personnage {
	
	
	public Gobelin(String nom, int pv, Coordonnee position, int taille, Animation a, Modele modele) {
		super(pv, position, taille, 1, a, modele);
	}
	
	public void jouer() {
		Joueur joueur = modele.getJoueur();
		Plateau map = this.modele.getCurrentNiveau().getPlateau();
		PathFinding path = new PathFinding(this.modele, 
							map.getCellule(this.getPosition().getX()/16, this.getPosition().getY()/16),
							map.getCellule(joueur.getPosition().getX()/16, joueur.getPosition().getY()/16));
		ArrayList<Cellule> pathToTheHero = path.chemin();
		ArrayList<Input> inputs = new ArrayList<Input>();
		if(pathToTheHero != null && !pathToTheHero.isEmpty() && isActive && inputs != null) {
		
//			//actions.add(Axe.DROITE);
//			inputs.add(Input.randomMovement());
			
			for (int i = 0; i < pathToTheHero.size(); i++) {
				if(pathToTheHero.get(i).getPos().getX()/16 == (this.getPosition().getX()/16)+1) {			//Droite
					inputs.add(Input.DROITE);
				}
				if(pathToTheHero.get(i).getPos().getX()/16 == (this.getPosition().getX()/16)-1) {			//Gauche
					inputs.add(Input.GAUCHE);
				}
				if(pathToTheHero.get(i).getPos().getY()/16 == (this.getPosition().getY()/16)-1) {			//Haut
					inputs.add(Input.HAUT);
				}
				if(pathToTheHero.get(i).getPos().getY()/16 == (this.getPosition().getY()/16)+1) {			//Bas
					inputs.add(Input.BAS);
				}
			}
			
			int[] movInputs = getMovements(inputs, vitesse);
				
			//on passe le d�placement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
			
		}
	}
	public void attaque(Personnage p) {
		System.out.println("Mon ma�tre est SA-ROUU-MAAAAANE");
	}
}
