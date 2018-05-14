package controleur;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.Plateau.PlateauInt;

public class ControleurZelda implements Initializable{
	
	@FXML
    private TilePane tuiles = new TilePane();
	
	 Image mur = new Image(new File("tilesets/tileset0.png").toURI().toString());
	 
	 public void initialize(URL location, ResourceBundle resources) {
			PlateauInt unPlateau = new modele.Plateau.PlateauInt();
			for(int x = 0; x<12; x++) {
				for(int y = 0; y<12; y++) {
					afficherImage(unPlateau.getPlateauInt()[x][y],32,32);
				}	
			}	
		}
	 
	 
	 void afficherImage(int i,int x, int y) {
		 	int id = i-1;
			ImageView temp = new ImageView(mur);
			temp.setViewport(new Rectangle2D(((id%57)*16)+id%57, Math.floor(id/57)*17, 16, 16));
			DoubleProperty tailleX = new SimpleDoubleProperty(x);
			DoubleProperty tailleY = new SimpleDoubleProperty(y);
			temp.setFitWidth(x);
			temp.setFitHeight(y);
			tuiles.getChildren().add(temp);
	 }

}
