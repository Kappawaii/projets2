package modele;

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
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'},
						  {'c','c','c','c','c','c','c','c','c','c','c','c'}
						};
	
	public char[][] getLePlateau() {
		return this.notreMap;
	}
}
