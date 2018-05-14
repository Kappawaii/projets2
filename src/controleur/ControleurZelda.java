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
import modele.Plateau.Plateau;

public class ControleurZelda implements Initializable{
	
	@FXML
    private TilePane tuiles = new TilePane();
	
	 Image img = new Image(new File("tilesets/tileset0.png").toURI().toString(),968*2.0,526*2.0,true,true);
	 
	 
	 public void initialize(URL location, ResourceBundle resources) {
			Plateau unPlateau = new modele.Plateau.Plateau();
			for(int x = 0; x<12; x++) {
				for(int y = 0; y<12; y++) {
					afficherImage(unPlateau.getPlateauInt()[x][y],2);
				}	
			}	
		}
	 
	 
	 void afficherImage(int i, int scale) {
		 	int id = i-1;
			ImageView temp = new ImageView(img);
			//temp.setViewport(new Rectangle2D((id%57*17)+1, Math.floor(id/57)*17+1, 16, 16));
			temp.setViewport(new Rectangle2D(id%57*17*scale, id/57*17*scale, 16*scale, 16*scale));
			temp.setFitWidth(16*scale);
			temp.setFitHeight(16*scale);
			temp.setSmooth(true);
			tuiles.getChildren().add(temp);
			
	 }

}
