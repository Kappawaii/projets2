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
	

	private char notreMap[][] = { {'m','m','m','m','m','m','m','m','m','m','m','m'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'},
						  {'s','s','s','s','s','s','s','s','s','s','s','s'}
						};
	
	public char[][] getPlateau() {
		return this.notreMap;
	}
}
