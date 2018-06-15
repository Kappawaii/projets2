package modele.cellule;

import java.util.ArrayList;

import modele.Entity.Entity;
import modele.Event.Event;
import modele.collision.Collider;
import modele.collision.EventCollider;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Cellule extends Entity{

	Sprite spr;
	EventCollider collider;
	ArrayList<Event> events;

	public Cellule(Tileset tileset,int id, int x, int y, boolean isTrigger, int scale, int offsetX, int offsetY, int tailleX, int tailleY, ArrayList<Event> eventsToAdd) {
		super(new Coordonnee(x, y));		
		this.spr = new Sprite(tileset,scale,id);
		this.collider = new EventCollider(new Coordonnee(x+offsetX,y+offsetY), isTrigger, tailleX, tailleY, this);
		this.events = new ArrayList<Event>();
		if (eventsToAdd != null) {
			for (int i = 0; i < eventsToAdd.size(); i++) {
				events.add(eventsToAdd.get(i));
			}
		}

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

	public void triggerEvent() {
		for (int i = 0; i < events.size(); i++) {
			events.get(i).execute();
		}
	}
}