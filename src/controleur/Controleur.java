package controleur;


import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import modele.Modele;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.personnage.joueur.Joueur;
import modele.plateau.Cellule;
import modele.plateau.Plateau;
import modele.plateau.BuilderPlateau;
import vue.tileset.Tileset;

public class Controleur {

	// permet de definir l'animation
	private Timeline gameLoop;
	private int temps;
	private Modele modele;
	
	@FXML
	private TilePane tuiles = new TilePane();
	@FXML
	private BorderPane borderpane = new BorderPane();

	boolean stop = false;
	static int scale = 4;
	KeyManager keymanager;
	

	@FXML
	public void initialize() {
		//TODO sync scale with main
		modele = new Modele();
		modele.setJoueur(new Joueur("test", 0, 
				 		 new Coordonnee(400,400),1,
				 		 new Tileset("src/vue/personnage.png", scale, 16, 16)));
		
		modele.addTileset(new Tileset("tilesets/tileset0.png",scale, 968, 526));
		modele.addPlateau(new Plateau());
		System.out.println(modele.getPlateau(0));
		BuilderPlateau a = new BuilderPlateau();
		a.remplirPlateau(modele.getPlateau(0), modele.getTileset(0), scale);
		afficherCarte(modele.getPlateau(0).getPlateau());
		initAnimation();
		gameLoop.play();
	}
	
	public int getScale() {
		return scale;
	}
	
	public void init() {
		System.out.println(borderpane.getScene());
		keymanager = new KeyManager(borderpane.getScene());
		keymanager.addKey(Axe.HAUT,"Z");
		keymanager.addKey(Axe.GAUCHE,"Q");
		keymanager.addKey(Axe.BAS,"S");
		keymanager.addKey(Axe.DROITE,"D");
		keymanager.addKey(Axe.ENTREE,"ENTER");
		keymanager.addKey(Axe.UTILISER, "E");
		keymanager.addKey(Axe.AHAUT, "UP");
		keymanager.addKey(Axe.AGAUCHE, "LEFT");
		keymanager.addKey(Axe.ADROITE, "DROITE");
		keymanager.addKey(Axe.ABAS, "DOWN");
	}

	public void mouseClicked() {
		System.out.println(keymanager);
	}


	void afficherCarte(Cellule cellules[][]) {
		for(int x = 0; x<12; x++) {
			for(int y = 0; y<12; y++) {
				//afficherImage(cellules[x][y]);
				tuiles.getChildren().add(cellules[x][y].getSprite().getView());
			}		
		}
	}

	//Afficher l'image
	void afficherImage(int i) {
		int id = i-1;
		tuiles.getChildren().add(new Cellule(modele.getTileset(0),id,scale).getSprite().getView());
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		modele.getJoueur().setImage("file:src/vue/personnage.png", scale);
		borderpane.getChildren().add(modele.getJoueur().getSprite().getView());
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stop){
						System.out.println("fini");
						gameLoop.stop();
					}
					else if (temps>5){
						modele.getJoueur().getSprite().getView().setY(modele.getJoueur().getPosition().getY());
						modele.getJoueur().getSprite().getView().setX(modele.getJoueur().getPosition().getX());
						modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps));
						
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}

