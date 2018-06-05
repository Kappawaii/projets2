package modele.plateau;

import modele.cellule.Cellule;
import vue.tileset.Tileset;

public class Plateau {
	
	private Cellule[][] cellules;
	
	public Plateau() {
	
	}
	
	public Cellule getCellule(int x, int y) {
		return cellules[x][y];
	}
	
	public Cellule[][] get() {
		return cellules;
	}
	
	public void remplir(BuilderPlateau builder, Plateau plateau, Tileset tileset, int scale) {
		builder.remplirPlateau(plateau, tileset, scale);
	}
	
	public void initCellules(int x, int y) {
		cellules = new Cellule[x][y];
	}
	
	public int getHeight() {
		return cellules.length;
	}
	public int getWidth() {
		return cellules[0].length;
	}
		
}