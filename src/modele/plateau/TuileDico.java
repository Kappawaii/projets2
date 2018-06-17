package modele.plateau;
import java.util.HashMap;
import java.util.Map;

import modele.Modele;
import modele.Event.Event;
import modele.Event.GetArcEvent;
import modele.Event.GetBateauEvent;
import modele.Event.LoadEntitiesEvent;
import modele.Event.LoadLevelEvent;
import modele.Event.SetScrollingMapEvent;
import modele.coordonnee.Coordonnee;

public class TuileDico {
	
	private static Map<Integer,TuileData> dicoMap = new HashMap<Integer, TuileData>();
	
	
	/**
	 * charge le dictionnaire
	 * @param modele
	 * @param plateau
	 * @param x
	 * @param y
	 */
	public static void load(Modele modele, Plateau plateau, int x, int y) {
		dicoMap.clear();
		TuileData mur = new TuileData(16, 16, 0, 0, null, null, false);
		TuileData mobilier = new TuileData(1, 1, 3, 4, null, null, false);
		TuileData lit = new TuileData(1, 1, 5, 3, null, null, false);
		TuileData bibliotheque = new TuileData(8, 8, 2, 3, null, null, false);
		Event e1 = new LoadLevelEvent(modele, 1,new Coordonnee(64,144));
		Event level2 = new LoadLevelEvent(modele, 2,new Coordonnee(64,144));
		Event e2 = new SetScrollingMapEvent(modele);
		Event e3 = new GetArcEvent(modele,plateau.get(),x,y);
		Event e4 = new LoadEntitiesEvent(modele,modele.getPersonnagesACharger(1),new Coordonnee[] {new Coordonnee(300,150),new Coordonnee(150,150)});
		Event e5 = new GetBateauEvent(modele,plateau.get(),x,y);
		
			dicoMap.put(348,mur);
			dicoMap.put(1187,mur);
			dicoMap.put(1129,mur);
			dicoMap.put(1068,mur);
			dicoMap.put(1069,mur);
			dicoMap.put(1073,mur);
			dicoMap.put(1130,mur);
			dicoMap.put(1426,mur);	
			dicoMap.put(1428,mur);
			dicoMap.put(1483,mur);
			dicoMap.put(1485,mur);
			dicoMap.put(1143,mur);
			dicoMap.put(1496,mur);
			dicoMap.put(1439,mur);
			dicoMap.put(1495,mur);
			dicoMap.put(1086,new TuileData(4, 1, 0, 0, new Event[]{level2,e2}, null, false));
			dicoMap.put(1438,mur);
			dicoMap.put(1552,mur);	
			dicoMap.put(1553,mur);		
			dicoMap.put(1540,mur);	
			dicoMap.put(1542,mur);		
			dicoMap.put(1246,mobilier);
			dicoMap.put(1244,mobilier);
			dicoMap.put(1245,mobilier);
			dicoMap.put(130,mobilier);
			dicoMap.put(1074,bibliotheque);
			dicoMap.put(1075,bibliotheque);
			//lit
			dicoMap.put(131,lit);
			//eau
			dicoMap.put(232,mur);	
			//Armoire
			dicoMap.put(1188,new TuileData(8, 8, 7, 3, null, null, false));
			
			dicoMap.put(1186,new TuileData(8, 4, 3, 2, null, null, false));
			//Porte
			dicoMap.put(90,new TuileData(4, 1, 0, 0, new Event[]{e1,e2}, null, false));
			//Arc
			dicoMap.put(124,new TuileData(4, 4, 0, 0, new Event[]{e3}, null, true));
			//Bateau
			dicoMap.put(181,new TuileData(4, 4, 0, 0, new Event[]{e5}, null, true));
			//chargement level1
			dicoMap.put(63,new TuileData(16, 16, 0, 0, new Event[]{e4}, null, true));

	}
	
	public static TuileData get(int id) {
		return dicoMap.get(id);
	}
}
