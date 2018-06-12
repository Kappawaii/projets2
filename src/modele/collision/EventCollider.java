package modele.collision;

import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;

public class EventCollider extends Collider {

	Cellule owner;

	/**
	 * collider avec Event
	 * @param coordonnee
	 * @param isTrigger
	 * @param tailleX
	 * @param tailleY
	 * @param cellule
	 */
	public EventCollider(Coordonnee coordonnee,boolean isTrigger, int tailleX, int tailleY, Cellule cellule) {
		super(coordonnee, isTrigger, tailleX, tailleY);
		owner = cellule;
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		o = new Coordonnee();
		x = new Coordonnee();
		y = new Coordonnee();
		xy = new Coordonnee();
		setPosition(coordonnee);
		this.isTrigger = isTrigger;
	}

	public void triggerEvent() {
		owner.triggerEvent();
		//System.out.println("Collision avec" + super.o);
	}

	@Override
	public boolean detecterCollision(Collider other) {
		if(super.detecterCollision(other)) {
			triggerEvent();
			//System.out.println("omegalul");
			return true;
		}
		return false;
	}
}
