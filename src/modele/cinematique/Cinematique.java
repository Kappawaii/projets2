package modele.cinematique;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;

public class Cinematique  {
	private ArrayList<Clip> clips;
	private int cineTime;
	private boolean finished;
	protected boolean inPause;
	private Modele modele;
	public Cinematique(Modele modele) {
		finished = false;
		cineTime = 0;
		clips = new ArrayList<Clip>();
		this.modele = modele;

	}
	
	/**
	 * retourne vrai lorsque la cinématique est finie
	 * @return
	 */
	public void play() {
		if(modele.getJoueur().getActive() != true)
			System.err.println("Cinematique avec joueur non actif !");
		if(!inPause && (cineTime < clips.size()) && !finished) {
			modele.getJoueur().seDeplace(iterateur());
			//non-scrolling map						
			modele.getAffichage().mettreAJourPositionPersonnage(
					modele.getJoueur(),
					modele.getJoueur().getPosition());
//			System.out.println(numberOfFrames);	
		}
	}
	
	/**
	 * itérateur entre les clips de la cinématique
	 * @return
	 */
	private ArrayList<Input> iterateur() {
		if(clips.get(cineTime).isFinished()) {
			cineTime++;
			if(cineTime >= clips.size()) {
				finished = true;
				return null;
			}
		}
		//System.out.println(cineTime);
		return clips.get(cineTime).getActions();
	}

	public void addClip(Clip clip) {
		clips.add(clip);
	}

	public void unPause(){
		if(inPause)
			inPause = false;
	}

	public boolean isfinished() {
		return finished;
	}

}