package modele.arme;

import java.util.ArrayList;

import controleur.Input;
import modele.Entity.Entity;
import modele.coordonnee.Coordonnee;

public class Arme extends Entity {

	int degats;

	//READ-ONLY
	Coordonnee ownerPosition;

	public Arme (int degats,Coordonnee ownerPosition) {
		this.degats = degats;
		this.ownerPosition = ownerPosition;		
	}

	public void attaquer(ArrayList<Input> direction) {
		int[] dirInputs = getDirection(direction);
		//on passe l'attaque si il l'entrée est nulle
		if (dirInputs[0] != 0 || dirInputs[1] != 0) {
			int[] nextPosXetY = getNextPos(dirInputs[0], dirInputs[1]);
		}
	}

	private int[] getDirection(ArrayList<Input> inputs) {
		int xInput = 0;
		int yInput = 0;
		//somme des entrées de mouvement
		for (int i = 0; i < inputs.size(); i++) {
			if(inputs.get(i).isMovement()) {
				xInput += inputs.get(i).x();
				yInput += inputs.get(i).y();
			}
		}
		return new int[] {xInput,yInput};
	}

}
