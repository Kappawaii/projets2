package modele.Entity;

import java.util.ArrayList;

import controleur.Input;
import modele.coordonnee.Coordonnee;

public abstract class Entity {
	
	protected Coordonnee position;
	
	//TODO Polymorphism
//	public abstract void jouer();
	
	public Entity(Coordonnee position) {
		this.position = position;
	}
	
	public Coordonnee getPosition() {
		return position;
	}
	
	public void setPosition(Coordonnee position) {
		this.position = position;
	}
	
	protected int[] getNextPos(int xInput, int yInput) {
		int nextPosX = position.getX() + xInput;
		int nextPosY = position.getY() + yInput;
		return new int[] {nextPosX,nextPosY};
	}
	
	protected int[] getDirection(ArrayList<Input> inputs) {
		int xInput = 0;
		int yInput = 0;
		//somme des entrées de mouvement
		for (int i = 0; i < inputs.size(); i++) {
			if(inputs.get(i).isAttack()) {
				xInput += inputs.get(i).x();
				yInput += inputs.get(i).y();
			}
		}
		return new int[] {xInput,yInput};
	}
	
	protected int[] getMovements(ArrayList<Input> inputs, int vitesse) {
		int xInput = 0;
		int yInput = 0;
		//somme des entrées de mouvement
		for (int i = 0; i < inputs.size(); i++) {
			if(inputs.get(i).isMovement()) {
				xInput += inputs.get(i).x();
				yInput += inputs.get(i).y();
			}
		}
		return new int[] {xInput*vitesse,yInput*vitesse};
	}
}
