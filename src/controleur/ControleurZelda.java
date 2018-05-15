package controleur;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    
	static int scale = 4;
	Image img = new Image(new File("tilesets/tileset0.png").toURI().toString(),968*scale,526*scale,true,true);
	 
	 
	 public void initialize(URL location, ResourceBundle resources) {
			Plateau unPlateau = new modele.Plateau.Plateau();
			System.out.println(tuiles);
			for(int x = 0; x<12; x++) {
				for(int y = 0; y<12; y++) {
					afficherImage(unPlateau.getPlateauInt()[x][y]);
				}	
			}	
		}
	 
	 
	 void afficherImage(int i) {
		 	int id = i-1;
			tuiles.getChildren().add(temp);
			
	 }
	 
	 
}
