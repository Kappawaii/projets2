package modele;

import java.util.ArrayList;

import modele.Entity.Entity;
import modele.collision.Collider;
import modele.niveau.Niveau;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import vue.Affichage;

public class Modele {

	ArrayList<Niveau> niveaux;
	ArrayList<Entity> entites;
	int idNiveau;
	Joueur joueur;
	Affichage affichage;
    
	public Modele() {
		niveaux = new ArrayList<Niveau>();
		entites = new ArrayList<Entity>();
	}
	
	public void changerMap(int idNewNiveau, boolean debugMode) {
		idNiveau = idNewNiveau;
		//affichage.nettoyerEntites();
		affichage.ajouterCarte(niveaux.get(idNiveau).getPlateau().get(), debugMode);
	}
	
	public ArrayList<Collider> getAllColliders(int idNiveau){
		ArrayList<Collider> colliders = new ArrayList<Collider>();
		if (niveaux.get(idNiveau).getPlateau() == null) {
			throw new NullPointerException("Plateau non trouvé (idPlateau :" + idNiveau +  " )");
		}
		else {			
			for (int x = 0; x < niveaux.get(idNiveau).getPlateau().get().length; x++) {
				for (int y = 0; y < niveaux.get(idNiveau).getPlateau().get()[x].length; y++) {
					colliders.add(niveaux.get(idNiveau).getPlateau().getCellule(x, y).getCollider());					
				}
			}
		}
		for (int i = 0; i < entites.size(); i++) {
			if(entites.get(i) instanceof Personnage) {
				colliders.add(((Personnage) entites.get(i)).getCollider());
			}
		}
		return colliders;
	}	

	public ArrayList<Personnage> getPersonnagesACharger(int niveau) {
		ArrayList<Personnage> personnagesACharger = new ArrayList<Personnage>();
		switch (niveau) {
		case 0:
			
			break;

		case 1:
			 personnagesACharger.add((Personnage) entites.get(0));
			break;
		}
		return personnagesACharger;
	}

	public ArrayList<Entity> getEntitesACloner() {
		return entites;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}	
	
	public void addNiveau(Niveau niveau) {
		niveaux.add(niveau);
	}
	
	public Niveau getCurrentNiveau() {
		return niveaux.get(idNiveau);
	}
	
	public Niveau getNiveau(int id) {
		return niveaux.get(id);
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
