package modele.Event;

import modele.Modele;

public abstract class Event {
	
	Modele modele;
	
	public Event(Modele modele) {
		if(modele == null)
			throw new IllegalArgumentException("null argument");
		this.modele = modele;
	}
	
	/**
	 * exécute l'évenement
	 */
	public abstract void execute();
}
