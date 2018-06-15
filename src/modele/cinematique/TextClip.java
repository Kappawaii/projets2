package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;
import javafx.scene.control.Label;

public class TextClip extends Clip {
	
	boolean isDrawn;
	boolean isFinished;
	String text;
	Label label;
	
	/**
	 * 
	 * @param label
	 * @param text
	 * @throws IllegalArgumentException
	 */
	public TextClip(Label label,String text) {
		if(label == null || text == null)
			throw new IllegalArgumentException();
		this.label = label;
		this.text = text;
		isDrawn = false;
		isFinished = false;
	}

	@Override
	public ArrayList<Input> getActions() {
		if(!isDrawn) {
			label.setText(text);
			isDrawn = true;
		}
		return null;
	}

	@Override
	public boolean isFinished() {
		return isDrawn;
	}

}
