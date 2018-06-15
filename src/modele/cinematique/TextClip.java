package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;

public class TextClip extends Clip {

	public TextClip(String text) {
	}

	@Override
	public ArrayList<Input> getActions() {
		
		return null;
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
