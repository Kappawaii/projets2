package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;

public abstract class Clip {
	
	public abstract ArrayList<Input> getActions();
	
	public abstract boolean isFinished();
}
