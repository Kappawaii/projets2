package modele.collision;

public class Collision {
	
	Collider b1;
	Collider b2;
	
	public Collision(Collider b1, Collider b2) {
		this.b1 = b1;
		this.b2 = b2;
		System.out.println("Collision détectée");
	}
}
