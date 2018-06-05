package modele;

import java.util.ArrayList;

import modele.collision.Collider;
import modele.niveau.Niveau;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.Plateau;
import vue.tileset.Tileset;

public class Modele {

	ArrayList<Tileset> tilesets;
	ArrayList<Personnage> personnages;
	ArrayList<Niveau> niveaux;
	int idNiveau;
	Joueur joueur;
	
	public Modele() {
		tilesets = new ArrayList<Tileset>();
		personnages = new ArrayList<Personnage>();
		niveaux = new ArrayList<Niveau>();
	}

//	public ArrayList<Collider> getAllColliders(){
//		ArrayList<Collider> colliders = new ArrayList<Collider>();
//		System.out.println("taille = " +niveaux.get(idNiveau).size());
//		for (int i = 0; i < niveaux.get(idNiveau).getPlateau().get().length; i++) {
//			ArrayList<Collider> temp = getPlateauCollider(i);
//			for (int j = 0; j < temp.size(); j++) {
//				if (!colliders.contains(temp.get(j))) {
//					colliders.add(temp.get(j));
//				}
//			}
//		}
//		return colliders;
//	}

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
	
	public Niveau getNiveau(int index) {
		return niveaux.get(index);
	}
	
	
	
	public int getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(int idMap) {
		this.idNiveau = idMap;
	}
	
}
