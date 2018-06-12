package modele.Event;

import modele.Modele;

public class SetScrollingMapEvent extends Event {
	
	public SetScrollingMapEvent(Modele modele) {
		super(modele);
	}

	@Override
	public void execute() {
		modele.getAffichage().setScrollingMap(!modele.getAffichage().isScrollingMapEnabled());
	}

}
