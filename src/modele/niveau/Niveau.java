package modele.niveau;

import java.util.ArrayList;

import modele.Entity.Entity;
import modele.plateau.BuilderPlateau;
import modele.plateau.Plateau;
import vue.tileset.Tileset;

public class Niveau {

	Plateau plateau;
	ArrayList<Entity> entites;
	String url;
	
	public String getUrl() {
		return url;
	}

	public Niveau() {
		entites = new ArrayList<Entity>();
	}

	public Niveau(String url,Tileset tileset, ArrayList<Entity> liste, int displayScale) {
		if(liste != null)
			for (int i = 0; i < liste.size(); i++) {
				entites.add(liste.get(i));
			}
		this.url = url;
		plateau = new Plateau();
		BuilderPlateau a = new BuilderPlateau();
		a.fileReader(url);
		a.remplirPlateau(plateau, tileset, 16);
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public ArrayList<Entity> getEntites(){
		return entites;
	}
}
