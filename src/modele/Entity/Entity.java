package modele.Entity;

import modele.coordonnee.Coordonnee;

public abstract class Entity {
	
	protected String nom;
	protected Coordonnee position;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	
}
