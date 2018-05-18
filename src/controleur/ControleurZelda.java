package controleur;


import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import modele.Case.Case;
import modele.Plateau.Plateau;
import modele.Tileset.Tileset;

public class ControleurZelda {

	@FXML
	private TilePane tuiles = new TilePane();
	
	@FXML
    private BorderPane borderpane = new BorderPane();

	
	static int scale = 1;
	KeyManager keymanager;
	Tileset tileset = new Tileset("tilesets/tileset0.png",scale, 968, 526);

	@FXML
	public void initialize() {
		//TODO sync scale with main
		Plateau unPlateau = new modele.Plateau.Plateau();
		System.out.println(tuiles);
		afficherCarte(unPlateau.getPlateauInt());
		
	}
	
	public void init() {
		System.out.println(borderpane.getScene());
		keymanager = new KeyManager(borderpane.getScene());
		keymanager.addKey("haut","Z");
		keymanager.addKey("gauche","Q");
		keymanager.addKey("bas","S");
		keymanager.addKey("droite","D");
		keymanager.addKey("enter","ENTER");
		keymanager.addKey("utiliser", "E");
	}
	
	public void mouseClicked() {
		System.out.println(keymanager);
	}

	
	void afficherCarte(int plateau[][]) {
		for(int x = 0; x<12; x++) {
			for(int y = 0; y<12; y++) {
				afficherImage(plateau[x][y]);
			}		
		}
	}
	
	//Afficher l'image
	void afficherImage(int i) {
		int id = i-1;
		tuiles.getChildren().add(new Case(tileset,id,scale).getImageView());
	}
}

