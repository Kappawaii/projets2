package modele.plateau;
import modele.collision.BoiteCollision;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Cellule{

	Tileset tileset;
	int id;
	Coordonnee pos;
	int scale;
	Sprite spr;
	BoiteCollision collider;
	boolean isWalkable;
	
	public Cellule(Tileset tileset,int id, int taille,int x, int y, boolean isWalkable) {
		this.tileset = tileset;
		this.scale = taille;
		this.id = id-1;
		spr = new Sprite(tileset,taille,id);
		pos = new Coordonnee(x, y);
		//collider = new BoiteCollision(pos, taille);
		this.isWalkable = isWalkable;
	}
	
	public Coordonnee getPos() {
		return pos;
	}
	
	public Coordonnee setPos() {
		return pos;
	}
	
	public boolean isWalkable() {
		return isWalkable;
	}
	
//	public Coordonnee getPosition() {
//		return pos;
//	}
//	
//	public boolean positionDansLaCase(Coordonnee c) {
//		if (c.getX() < pos.getX() || c.getX() <  pos.getX())
//			return false;
//		if (c.getX() > (pos.getX()+16*scale) || c.getY() > (pos.getY()+16*scale))
//			return false;
//		return true;
//	}

	public Sprite getSprite() {
		return spr;
	}
	
	public int getId() {
		return this.id;
	}
}