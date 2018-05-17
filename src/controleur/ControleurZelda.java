package controleur;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import modele.Case.Case;
import modele.Plateau.Plateau;
import modele.Tileset.Tileset;

public class ControleurZelda implements Initializable{
	
	
	
	@FXML
	private TilePane tuiles = new TilePane();
    
    
	static int scale = 4;
	
	Tileset tileset = new Tileset("tilesets/tileset0.png",scale, 968, 526);
	//Image tileset1 = new Image(new File("tilesets/tileset0.png").toURI().toString(),968*scale,526*scale,true,true);
	 
	 
	 public void initialize(URL location, ResourceBundle resources) {
			Plateau unPlateau = new modele.Plateau.Plateau();
			System.out.println(tuiles);
			for(int x = 0; x<12; x++) {
				for(int y = 0; y<12; y++) {
					afficherImage(unPlateau.getPlateauInt()[x][y]);
				}	
			}	
		}
	 

	 public void mouseClicked() {

		 System.out.println("click");
	 }
	 
//     void keyPressed(KeyEvent e) {
//	       System.out.println(e.getCode());
//  	   
//	       if (e.getCode().equals(KeyCode.RIGHT)) {
//	            System.out.println("Right key pressed");
//	        }
//	        else if (e.getCode().equals(KeyCode.LEFT)) {
//	            System.out.println("Left key pressed");
//	        }
//	        else if (e.getCode().equals(KeyCode.UP)) {
//	            System.out.println("Left key pressed");	        }
//	        else if (e.getCode().equals(KeyCode.DOWN)) {
//	            System.out.println("Left key pressed");
//	        }
//		 
//     }
	 
	 //Afficher l'image
	 void afficherImage(int i) {
		 	int id = i-1;
		 	//tuiles.getChildren().add(new ImageView(new Image(new File("tilesets/tileset0.png").toURI().toString())));
			tuiles.getChildren().add(new Case(tileset,id,scale).getImageView());
			
	 }
	 
	 
}
