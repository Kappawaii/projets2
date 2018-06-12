package vue;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import modele.Modele;
import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Affichage {
	Pane tuiles;
	Pane entites;
	Cellule[][] cellules;
	int displayScale;
	Modele modele;
	ArrayList<Node> nodes;
	boolean scrollingMap = false;
	Coordonnee offsetScrollingmap = new Coordonnee(896,96);

	public Affichage(Modele modele, Pane tuiles, Pane entites,int displayScale) {
		this.tuiles = tuiles;
		this.entites = entites;
		this.displayScale = displayScale;
		this.modele = modele;
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

	public void ajouterCarte(Cellule[][] cellules, boolean debug) {
		nettoyerPane(tuiles);
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
				//TODO pourquoi X et y inversés ?
				tuiles.getChildren().set(x*cellules.length+y, cellules[x][y].getSprite().getView());

			}		
		}		
	}

	public void mettreAJourPositionPersonnage(Personnage pers,Coordonnee pos) {
//		modele.getJoueur().getAnimationManager().updateAnimationsPos(pos, displayScale);
		pers.getSprite().getView().setY(pos.getY()*displayScale);
		pers.getSprite().getView().setX(pos.getX()*displayScale);
	}

	//scrolling map
	public void centerMaptoPosition(Coordonnee coordonnee) {
		tuiles.setTranslateX((tuiles.getLayoutBounds().getMaxX()/2-coordonnee.getX()*displayScale-offsetScrollingmap.getX()));
		tuiles.setTranslateY((tuiles.getLayoutBounds().getMaxY()/2-coordonnee.getY()*displayScale-offsetScrollingmap.getY()));
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
