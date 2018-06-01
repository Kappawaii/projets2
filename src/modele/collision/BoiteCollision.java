package modele.collision;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import modele.coordonnee.Coordonnee;

public class BoiteCollision {
	
	Coordonnee o;
	Coordonnee x;
	Coordonnee y;
	Coordonnee xy;
	
	public BoiteCollision(Coordonnee coordonnee, int taille) {
		setPosition(coordonnee, taille);
	}
	
	public void setPosition(Coordonnee coordonnee, int taille) {
		o.setX(coordonnee.getX());
		o.setY(coordonnee.getY());
		x.setX(o.getX()+taille);
		x.setY(o.getY());
		y.setX(o.getX());
		y.setY(o.getY()+taille);
		xy.setX(o.getX()+taille);
		xy.setY(o.getY()+taille);
	}
	public void detecterCollision(BoiteCollision other) {
		if(true) {
			creerCollision(other);
		}
	}
	public Collision creerCollision(BoiteCollision other) {
		return new Collision(this,other);
	}
}
