package modele.cellule;
import modele.collision.Collider;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Cellule{

	Tileset tileset;
	int id;
	Coordonnee pos;
	int scale;
	Sprite spr;
	Collider collider;
	boolean isWalkable;
	
	public Cellule(Tileset tileset,int id, int taille,int x, int y, boolean isTrigger) {
		this.tileset = tileset;
		this.scale = taille;
		this.id = id-1;
		this.spr = new Sprite(tileset,taille,id);
		this.pos = new Coordonnee(x, y);
		this.isWalkable = isTrigger;
		this.collider = new Collider(pos, taille, isTrigger);
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

	public Collider getCollider() {
		return collider;
	}

	public Sprite getSprite() {
		return spr;
	}
	
	public int getId() {
		return this.id;
	}
}