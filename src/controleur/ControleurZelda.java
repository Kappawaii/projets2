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

public class ControleurZelda implements Initializable{
	
	@FXML
    private TilePane tuiles = new TilePane();
	
	String mur = new File("src/vue/mur.png").toURI().toString();
	 Image imgmur = new Image(mur);
	 
	String sol = new File("src/vue/sol.png").toURI().toString();
	 Image imgsol = new Image(sol);

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("gdyzeu");
		modele.Plateau.Plateau unPlateau = new modele.Plateau.Plateau();
		for(int x = 0; x<12; x++) {
			for(int y = 0; y<12; y++) {
				if(unPlateau.getPlateau()[x][y] == 'm') {
					System.out.println(unPlateau.getPlateau()[x][y]);
					ImageView temp = new ImageView(imgmur);
					temp.setViewport(new Rectangle2D(0, 0, 16, 16));
					tuiles.getChildren().add(temp);
				}
				else if(unPlateau.getPlateau()[x][y] == 's') {
					System.out.println(unPlateau.getPlateau()[x][y]);
					ImageView temp = new ImageView(imgsol);
					temp.setViewport(new Rectangle2D(0, 0, 16, 16));
					tuiles.getChildren().add(temp);
				}
			}
			
		}
		
	}

}
