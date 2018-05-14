package modele.Plateau;

import javafx.scene.image.ImageView;

public class Plateau {

	/*
	 * 'a' : arbre
	 * 'n' : case noir, vide?
	 * 'b' : blanc
	 * 'm' : mur
	 * 's' : sol
	 */
	

	private char notreMap[][] = {
							{'m','m','m','m','m','m','m','m','m','m','m','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','s','s','s','s','s','s','s','s','s','s','m'},
							{'m','m','m','m','m','m','m','m','m','m','m','m'}
						};
	
	public char[][] getPlateau() {
		return this.notreMap;
	}
}
