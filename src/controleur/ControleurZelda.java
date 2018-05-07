package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.Plateau;

public class ControleurZelda implements Initializable{
	
	@FXML
    private TilePane tuiles;
	 Image image = new Image("/home/etudiants/info/msean/Images/flower.jpg");
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Plateau unPlateau = new Plateau();
		for(int x = 0; x<12; x++) {
			for(int y = 0; y<12; y++) {
				if(unPlateau.getLePlateau()[x][y] == 'm') {
					tuiles.getChildren().add(new ImageView(image));
				}
			}
			
		}
		
	}

}
