package vue;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modele.Modele;
import modele.arme.LanceurProjectile;
import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import vue.sprite.Sprite;
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
		entites.getChildren().add(p.getSprite().getView());
	}

	public void ajouterCarte(Cellule[][] cellules, boolean debug) {
		nettoyerPane(tuiles);
		nettoyerPane(entites);
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				cellules[x][y].getSprite().getView().setLayoutX(cellules[x][y].getPosition().getX()*displayScale);
				cellules[x][y].getSprite().getView().setLayoutY(cellules[x][y].getPosition().getY()*displayScale);
				tuiles.autosize();
				tuiles.getChildren().add(cellules[x][y].getSprite().getView());
				if(debug) {
					Label a = new Label(""+cellules[x][y].getPosition());
					a.setLayoutX(cellules[x][y].getPosition().getX()*displayScale);
					a.setLayoutY(cellules[x][y].getPosition().getY()*displayScale);
					tuiles.getChildren().add(a);
				}
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
		if(!isScrollingMapEnabled()) {
			tuiles.setTranslateX(0);
			tuiles.setTranslateY(0);
		}		
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


	public void ajouterArme(LanceurProjectile a) {
		System.out.println("a");
		if(!entites.getChildren().contains(a.getSpr().getView()))
			entites.getChildren().add(a.getSpr().getView());
	}

	public void enleverArme(LanceurProjectile a) {
		entites.getChildren().remove(a.getSpr().getView());
	}

	public void mettreAJourPositionProjectile(Coordonnee position, Sprite spr) {
		spr.getView().setLayoutX(position.getX()*4);
		spr.getView().setLayoutY(position.getY()*4);

	}


}
