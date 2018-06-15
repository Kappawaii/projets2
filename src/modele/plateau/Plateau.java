package modele.plateau;

import modele.cellule.Cellule;

public class Plateau {
	
	private Cellule[][] cellules;
	
	public Plateau() {
	
	}
	
	public Cellule getCellule(int x, int y) {
		//cas underflow
        if(x < 0 || y < 0)
            return null;
        //cas overflow
        if(x >= cellules.length || y >= cellules[x].length)
            return null;
        
        return cellules[x][y];
    }
	
	public Cellule[][] get() {
		return cellules;
	}
	
	public int[] getCellulePositionInPlateau(Cellule cellule) {
        for (int i = 0; i < cellules.length-1; i++) {
        	for (int j = 0; j < cellules[i].length-1; j++) {
	             if (cellule == (cellules[i][j]))
	            	 return new int[] {i,j};
	            }
	        }
	        return null;
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