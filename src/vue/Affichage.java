package vue;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import modele.Modele;
import modele.arme.Arme;
import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Affichage {

	ArrayList<Tileset> tilesets;
	Pane tuiles;
	Pane entites;
	Cellule[][] cellules;
	int displayScale;
	Modele modele;
	ArrayList<Node> nodes;
	boolean scrollingMap = false;
	Coordonnee offsetScrollingmap = new Coordonnee(896,256);

	public Affichage(Modele modele, Pane tuiles, Pane entites,int displayScale) {
		tilesets = new ArrayList<Tileset>();
		this.tuiles = tuiles;
		this.entites = entites;
		this.displayScale = displayScale;
		this.modele = modele;
	}



	public void addTileset(Tileset tileset) {
		tilesets.add(tileset);
	}

	public Tileset getTileset(int index) {
		return tilesets.get(index);
	}

	public void nettoyerPane(Pane pane) {
		pane.getChildren().clear();
	}

	public void nettoyerEntites() {
		int length = entites.getChildren().size();
		for (int i = 1; i < length; i++) {
			entites.getChildren().remove(i);
		}
	}

	public void ajouterPersonnage(Personnage p) {
		System.out.println(entites.getChildren().size());
		entites.getChildren().add(p.getSprite().getView());
	}

	public void enleverArme(Arme arme) {
		entites.getChildren().remove(arme.getSpr().getView());
	}
	
	public void ajouterArme(Arme a) {
		if(!entites.getChildren().contains(a.getSpr().getView()))
			entites.getChildren().add(a.getSpr().getView());
//		else
//			System.err.println("ajout de sprite déjà dans le pane !");
	}

	public void ajouterCarte(Cellule[][] cellules, boolean debug) {
		nettoyerPane(tuiles);
		nettoyerPane(entites);
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				cellules[x][y].getSprite().getView().setLayoutX(cellules[x][y].getPos().getX()*displayScale);
				cellules[x][y].getSprite().getView().setLayoutY(cellules[x][y].getPos().getY()*displayScale);
				tuiles.autosize();
				tuiles.getChildren().add(cellules[x][y].getSprite().getView());
				//				if(debug) {
				//					Label a = new Label(""+cellules[x][y].getCollider().isTrigger());
				//					a.setLayoutX(cellules[x][y].getPos().getX()*displayScale);
				//					a.setLayoutY(cellules[x][y].getPos().getY()*displayScale);
				//					tuiles.getChildren().add(a);
				//				}
			}		
		}
	}

	public void mettreAJourCarte(Cellule[][] cellules) {
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				tuiles.getChildren().set(x*cellules.length+y, cellules[x][y].getSprite().getView());

			}		
		}		
	}

	public void mettreAJourPositionPersonnage(Personnage pers,Coordonnee pos) {
		pers.getSprite().getView().setX(pos.getX()*displayScale);
		pers.getSprite().getView().setY(pos.getY()*displayScale);
	}

	public void mettreAJourPositionArme(Arme a,Coordonnee pos) {
		a.getSpr().getView().setX(pos.getX()*displayScale);
		a.getSpr().getView().setY(pos.getY()*displayScale);
	}

	//scrolling map
	public void centerPanetoPosition(Pane pane,Coordonnee coordonnee) {
		pane.setTranslateX((tuiles.getLayoutBounds().getMaxX()/2-coordonnee.getX()*displayScale-offsetScrollingmap.getX()));
		pane.setTranslateY((tuiles.getLayoutBounds().getMaxY()/2-coordonnee.getY()*displayScale-offsetScrollingmap.getY()));
	}	

	public boolean isScrollingMapEnabled() {
		return scrollingMap;
	}


	public void setScrollingMap(boolean scrollingMap) {
		this.scrollingMap = scrollingMap;
	}

	public Pane getTuiles() {
		return tuiles;
	}


}
