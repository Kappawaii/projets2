package vue;

import javafx.scene.layout.Pane;
import modele.Modele;
import modele.cellule.Cellule;

public class Affichage {
	
	Pane tuiles;
	Pane entites;
	Cellule[][] cellules;
	int displayScale;
	Modele modele;
	
	public Affichage(Modele modele, Pane tuiles, Pane entites,int displayScale) {
		this.tuiles = tuiles;
		this.entites = entites;
		this.displayScale = displayScale;
		this.modele = modele;
	}
	

}
