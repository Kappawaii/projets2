package modele.collision;
import java.util.ArrayList;

import modele.coordonnee.Coordonnee;

public class Collider {
	Coordonnee o;
	Coordonnee x;
	Coordonnee y;
	Coordonnee xy;
	int taille;
	boolean isTrigger;
	public int id;
	
	public boolean isTrigger() {
		return isTrigger;
	}

	public Coordonnee getO() {
		return o;
	}

	public Coordonnee getX() {
		return x;
	}

	public Coordonnee getY() {
		return y;
	}

	public Coordonnee getXY() {
		return xy;
	}

	public Collider(Coordonnee coordonnee, int taille, boolean isTrigger, int id) {
		this.taille = taille;
		o = new Coordonnee();
		x = new Coordonnee();
		y = new Coordonnee();
		xy = new Coordonnee();
		setPosition(coordonnee);
		this.isTrigger = isTrigger;
	}

	public void setPosition(Coordonnee coordonnee) {
		o.setX(coordonnee.getX());
		o.setY(coordonnee.getY());
		x.setX(o.getX()+taille);
		x.setY(o.getY());
		y.setX(o.getX());
		y.setY(o.getY()+taille);
		xy.setX(o.getX()+taille);
		xy.setY(o.getY()+taille);
	}

	public int getTaille() {
		return taille;
	}

	public ArrayList<Collider> detecterCollisions(ArrayList<Collider> other) {
		ArrayList<Collider> colliders = new ArrayList<Collider>();
		for (int index = 0; index < other.size(); index++) {
			if(!this.equals(other.get(index)))
					if(this.detecterCollision(other.get(index))) {
						colliders.add(other.get(index));
					}
		}
		return colliders;
	}

	public boolean detecterCollision(Collider other) {
		/*
		 * Test de non-collision horizontale
		 */
		if (this.o.getX() >= other.getX().getX())
			return false;
		else if (this.x.getX() <= other.getO().getX()) {
			return false;
		}
		/*
		 * A partir d'ici, il y a forcément une intersection entre les deux
		 * polygones sur le plan horizontal (x)
		 */
		if (this.x.getY() >= other.getY().getY()) {
			return false;
		}
		else if (this.getXY().getY() <= other.getO().getY()) {
			return false;
		}
		return true;
	}
	
	public void sysout() {
		System.out.print(getO());
		System.out.print(getX());
		System.out.print(getY());
		System.out.print(getXY());
		System.out.println(isTrigger);
	}
}
