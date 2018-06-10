package modele.cellule;

import modele.Entity.Entity;
import modele.Event.Event;
import modele.collision.Collider;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Cellule extends Entity{

	Sprite spr;
	Collider collider;
	Event event;

	public Cellule(Tileset tileset,int id, int taille,int x, int y, boolean isTrigger, int scale, int offsetX, int offsetY, int offsetTaille, Event event) {
		this.spr = new Sprite(tileset,scale,id);
		position = new Coordonnee(x, y);
		this.collider = new Collider(new Coordonnee(x+offsetX,y+offsetY), taille+offsetTaille, isTrigger);
	}
	
	public Coordonnee getPos() {
		return position;
	}
	
	public Coordonnee setPos() {
		return position;
	}

	public Collider getCollider() {
		return collider;
	}

	public Sprite getSprite() {
		return spr;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}