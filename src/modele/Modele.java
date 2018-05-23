package modele;

import java.util.ArrayList;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.Plateau;
import vue.tileset.Tileset;

public class Modele {

	ArrayList<Plateau> plateaux;
	ArrayList<Tileset> tilesets;
	ArrayList<Personnage> personnages;
	Joueur joueur;
	
	
	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Modele() {
		plateaux = new ArrayList<Plateau>();
		tilesets = new ArrayList<Tileset>();
		personnages= new ArrayList<Personnage>();
	}
	
	public void addPlateau(Plateau plateau) {
		plateaux.add(plateau);
	}
	
	public void addTileset(Tileset tileset) {
		tilesets.add(tileset);
	}
	
	public Plateau getPlateau(int index) {
		return plateaux.get(index);
	}
	
	public Tileset getTileset(int index) {
		return tilesets.get(index);
	}
	
	
	
}