//package modele.personnage.ennemis;
//
//import javafx.animation.Timeline;
//import modele.coordonnee.Coordonnee;
//import modele.personnage.Personnage;
//import modele.personnage.joueur.Joueur;
//import vue.tileset.Tileset;
//
//public class Plante extends Personnage {
//
//	
//	public Plante(String nom, Coordonnee position, int vitesse, Tileset tileset) {
//		super(nom, 5, 1, position, vitesse, tileset, 16);
//	}
//	
//	public boolean isAlive() {
//		if(this.getPv() > 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	
//	
//	public void attaquePersoVisible(Joueur joueur) {
//		double distance = Math.sqrt(Math.pow((this.getPosition().getX()-joueur.getPosition().getX()), 2) + Math.pow((this.getPosition().getY()-joueur.getPosition().getY()), 2));
//		if(this.isAlive() && distance < 4) {
//			this.attaque(joueur);
//		}
//	
//	}
//	
//	
//	
//	
//
//}
