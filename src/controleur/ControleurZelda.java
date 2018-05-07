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
import javafx.scene.layout.VBox;
import modele.Plateau;

public class ControleurZelda implements Initializable{
	
	@FXML
    private TilePane tuiles = new TilePane();
	
	String efrefr = new File("src/vue/tileset.png").toURI().toString();
	 Image image = new Image(efrefr);

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("gdyzeu");
		Plateau unPlateau = new Plateau();
		for(int x = 0; x<12; x++) {
			for(int y = 0; y<12; y++) {
				if(unPlateau.getLePlateau()[x][y] == 'm') {
					System.out.println(unPlateau.getLePlateau()[x][y]);
					ImageView temp = new ImageView(image);
					temp.setViewport(new Rectangle2D(0, 0, 16, 16));
					tuiles.getChildren().add(temp);
				}
			}
			
		}
		
	}

}
