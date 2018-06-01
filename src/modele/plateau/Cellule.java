package modele.plateau;

import modele.collision.BoiteCollision;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Cellule{

	Tileset tileset;
	int id;
//	ImageView view;
	Coordonnee pos;
	int scale;
	Sprite spr;
	BoiteCollision collider;
	
	public Cellule(Tileset tileset,int id, int taille) {
		this.tileset = tileset;
		this.scale = taille;
		this.id = id-1;
		spr = new Sprite(tileset,taille,id);
		collider = new BoiteCollision(pos, taille);
	}
	
	public Coordonnee getPosition() {
		return pos;
	}
	
	public boolean positionDansLaCase(Coordonnee c) {
		if (c.getX() < pos.getX() || c.getX() <  pos.getX())
			return false;
		if (c.getX() > (pos.getX()+16*scale) || c.getY() > (pos.getY()+16*scale))
			return false;
		return true;
	}

	public Sprite getSprite() {
		return spr;
	}
	
	public int getId() {
		return this.id;
	}
}