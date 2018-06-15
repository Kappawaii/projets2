package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;

public class PassiveClip extends Clip {
	
	private ArrayList<Input> actions;

	// durée du Clip en nombre de frames
	int duration;
	// nombre de frames restantes avant la fin du Clip
	int remaining;

	public PassiveClip(Input axe, int duration) {
		actions = new ArrayList<Input>();
		actions.add(axe);
		this.duration = duration;
		remaining = duration;		
	}

	public ArrayList<Input> getActions() {
		if(remaining -1 < 0) {
			return null;
		}
		//System.out.println(remaining);
		remaining--;
		return actions;
	}

	public boolean isFinished() {
		if(remaining > 0)
			return false;
		return true;
	}
}