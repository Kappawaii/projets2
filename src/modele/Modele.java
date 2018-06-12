package modele;

import java.util.ArrayList;

import modele.collision.Collider;
import modele.niveau.Niveau;
import modele.personnage.joueur.Joueur;
import vue.Affichage;
import vue.tileset.Tileset;

public class Modele {

	ArrayList<Tileset> tilesets;
	ArrayList<Niveau> niveaux;
	int idNiveau;
	Joueur joueur;
	Affichage affichage;
    
	public Modele() {
		tilesets = new ArrayList<Tileset>();
		niveaux = new ArrayList<Niveau>();
	}
	
	public void changerMap(int idNewNiveau, boolean debugMode) {
		idNiveau = idNewNiveau;
		//affichage.nettoyerEntites();
		affichage.ajouterCarte(niveaux.get(idNiveau).getPlateau().get(), debugMode);
	}
	
	public ArrayList<Collider> getPlateauCollider(int idNiveau){
		if (niveaux.get(idNiveau).getPlateau() == null) {
			throw new NullPointerException("Plateau non trouvé (idPlateau :" + idNiveau +  " )");
		}
		else {
			
			ArrayList<Collider> colliders = new ArrayList<Collider>();
			for (int x = 0; x < niveaux.get(idNiveau).getPlateau().get().length; x++) {
				for (int y = 0; y < niveaux.get(idNiveau).getPlateau().get()[x].length; y++) {
					colliders.add(niveaux.get(idNiveau).getPlateau().getCellule(x, y).getCollider());					
				}
			}
			return colliders;
		}
	}	

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}	

	public void addTileset(Tileset tileset) {
		tilesets.add(tileset);
	}

	public Tileset getTileset(int index) {
		return tilesets.get(index);
	}	
	
	
	public void addNiveau(Niveau niveau) {
		niveaux.add(niveau);
	}
	
	public Niveau getNiveau() {
		return niveaux.get(idNiveau);
	}	
	
	public int getIdNiveau() {
		return idNiveau;
	}


	public Affichage getAffichage() {
		return affichage;
	}

	public void setAffichage(Affichage affichage) {
		this.affichage = affichage;
	}

	
}
