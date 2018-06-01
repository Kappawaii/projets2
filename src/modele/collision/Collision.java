package modele.collision;

import modele.coordonnee.Coordonnee;

public class Collision {
	
	BoiteCollision b1;
	BoiteCollision b2;
	
	public Collision(BoiteCollision b1, BoiteCollision b2) {
		this.b1 = b1;
		this.b2 = b2;
		System.out.println("Collision détectée");
	}
}
