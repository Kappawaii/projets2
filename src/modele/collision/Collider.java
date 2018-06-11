package modele.collision;
import java.util.ArrayList;

import modele.coordonnee.Coordonnee;

public class Collider {
	Coordonnee o;
	Coordonnee x;
	Coordonnee y;
	Coordonnee xy;
	int tailleX;
	int tailleY;
	boolean isTrigger;
	
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

	public Collider(Coordonnee coordonnee, int taille, boolean isTrigger) {
		this.tailleX = taille;
		this.tailleY = taille;
		o = new Coordonnee();
		x = new Coordonnee();
		y = new Coordonnee();
		xy = new Coordonnee();
		setPosition(coordonnee);
		this.isTrigger = isTrigger;
	}
	
	public Collider(Coordonnee coordonnee, int tailleX, int tailleY, boolean isTrigger) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
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
		x.setX(coordonnee.getX()+tailleX);
		x.setY(coordonnee.getY());
		y.setX(coordonnee.getX());
		y.setY(coordonnee.getY()+tailleY);
		xy.setX(coordonnee.getX()+tailleX);
		xy.setY(coordonnee.getY()+tailleY);
	}

	public int getTailleX() {
		return tailleX;
	}
	
	public int getTailleY() {
		return tailleX;
	}

	public ArrayList<Collider> detecterCollisions(ArrayList<Collider> other) {
		ArrayList<Collider> colliders = new ArrayList<Collider>();
		for (int index = 0; index < other.size(); index++) {
			if(!this.equals(other.get(index)))
					if(detecterCollision(other.get(index))) {
						colliders.add(other.get(index));
					}
		}
		return colliders;
	}

	public boolean detecterCollision(Collider other) {
		/*
		 * Test de non-collision horizontale
		 */
		if (this.o.getX() >= other.getX().getX()) {
			return false;
		}
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

	public void setTrigger(boolean b) {
		isTrigger = b;
		
	}
}
