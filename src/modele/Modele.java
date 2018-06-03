package modele;

import java.util.ArrayList;

import modele.collision.Collider;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.Plateau;
import vue.tileset.Tileset;

public class Modele {

	ArrayList<Plateau> plateaux;
	ArrayList<Tileset> tilesets;
	ArrayList<Personnage> personnages;
	Joueur joueur;
	private int VitesseAnimations;

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

	public int getVitesseAnimations() {
		return VitesseAnimations;
	}

	public void setVitesseAnimations(int vitesseAnimations) {
		VitesseAnimations = vitesseAnimations;
	}	

	public ArrayList<Collider> getAllColliders(){
		ArrayList<Collider> colliders = new ArrayList<Collider>();
		for (int i = 0; i < plateaux.size(); i++) {
			ArrayList<Collider> temp = getPlateauCollider(i);
			for (int j = 0; j < temp.size(); j++) {
				if (!colliders.contains(temp.get(j))) {
					colliders.add(temp.get(j));
				}
			}
		}
		return colliders;
	}

	private ArrayList<Collider> getPlateauCollider(int idPlateau){
		if (plateaux.get(idPlateau) == null) {
			throw new NullPointerException("Plateau non trouvé (idPlateau :" + idPlateau +  " )");
		}
		else {
			ArrayList<Collider> colliders = new ArrayList<Collider>();
			for (int x = 0; x < plateaux.get(idPlateau).getPlateau().length; x++) {
				for (int y = 0; y < plateaux.get(idPlateau).getPlateau()[x].length; y++) {
					colliders.add(plateaux.get(idPlateau).getCellule(x, y).getCollider());					
				}
			}
			return colliders;
		}
	}
}