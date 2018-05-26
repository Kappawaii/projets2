package modele.plateau;

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
	
	public Cellule(Tileset tileset,int id, int scale) {
		this.tileset = tileset;
		this.scale = scale;
		this.id = id-1;
		spr = new Sprite(tileset,scale,id);
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