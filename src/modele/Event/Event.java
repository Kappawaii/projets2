package modele.Event;

import modele.Modele;

public abstract class Event {
	
	Modele modele;
	
	public Event(Modele modele) {
		this.modele = modele;
	}
	
	public abstract void execute();
}
