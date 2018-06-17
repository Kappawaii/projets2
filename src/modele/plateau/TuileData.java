package modele.plateau;

import modele.Event.Event;
import modele.coordonnee.Coordonnee;

public class TuileData {

	protected Event[] events;
	protected Coordonnee[] pos;
	protected boolean isTrigger;
	protected int tailleX;
	protected int tailleY;
	protected int offsetX;
	protected int offsetY;

	public TuileData(int tailleX, int tailleY, int offsetX, int offsetY, Event[] events,
			Coordonnee[] pos, boolean isTrigger) {

		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.events = events;
		this.pos = pos;
		this.isTrigger = isTrigger;
	}
}
