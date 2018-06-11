package modele.Event;

import modele.niveau.Niveau;

public class LoadLevelEvent {

	Niveau targetniveau;

	public LoadLevelEvent(Niveau niveau) {
		targetniveau = niveau;	
	}

	public void activerEvent() {
		
	}
}
