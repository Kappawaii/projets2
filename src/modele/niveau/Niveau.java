package modele.niveau;

import java.util.ArrayList;

import modele.Modele;
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

	public Niveau (
			String url,
			Tileset tileset, 
			ArrayList<Entity> liste, 
			int displayScale, 
			int id, 
			Modele modele) {
		
		if(url == "")
			throw new IllegalArgumentException("url null");
		if(tileset == null)
			throw new IllegalArgumentException("tileset null");
//		if(liste == null)
//			throw new IllegalArgumentException("liste null");
		if(displayScale == 0)
			throw new IllegalArgumentException("displayScale = 0");
//		if(id < 1)
//			throw new IndexOutOfBoundsException("id < 1");
		if(modele == null)
			throw new IllegalArgumentException("modele null");
			
		entites = new ArrayList<Entity>();
		if(liste != null)
			for (int i = 0; i < liste.size(); i++) {
				entites.add(liste.get(i));
			}
		this.url = url;
		plateau = new Plateau();
		BuilderPlateau a = new BuilderPlateau();
		a.fileReader(url);
		a.remplirPlateau(plateau, tileset, modele, id);
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public ArrayList<Entity> getEntites(){
		return entites;
	}
}
