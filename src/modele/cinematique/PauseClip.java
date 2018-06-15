package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;

public class PauseClip extends Clip {
	
	private Cinematique cinematique;
	private boolean activated;
	
	public PauseClip(Cinematique cinematique) {
		this.cinematique = cinematique;
		activated = false;
	}

	@Override
	public ArrayList<Input> getActions() {
		return null;
	}

	@Override
	public boolean isFinished() {
		if(!activated) {
			cinematique.inPause = true;
			activated = true;
		}
		if(cinematique.inPause)
			return false;
		return true;
	}

}
