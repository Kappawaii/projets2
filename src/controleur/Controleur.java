package controleur;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import modele.Case.Case;
import modele.Coordonnee.Axe;
import modele.Coordonnee.Coordonnee;
import modele.Personnage.Joueur.Joueur;
import modele.Plateau.Plateau;
import modele.Tileset.Tileset;

public class Controleur {

	// permet de definir l'animation
	private Timeline gameLoop;
	private int temps;
	@FXML
	private Circle leCercle;
	
	private Joueur joueur = new Joueur("test", 0, new Coordonnee(400,400),1);


	@FXML
	private TilePane tuiles = new TilePane();

	@FXML
	private BorderPane borderpane = new BorderPane();

	boolean stop = false;
	static int scale = 4;
	KeyManager keymanager;
	Tileset tileset = new Tileset("tilesets/tileset0.png",scale, 968, 526);

	@FXML
	public void initialize() {
		//TODO sync scale with main
		Plateau unPlateau = new modele.Plateau.Plateau();
		System.out.println(tuiles);
		afficherCarte(unPlateau.getPlateauInt());
		initAnimation();
		gameLoop.play();
		leCercle.toFront();
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

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		joueur.setImage("src/vue/personnage.png", scale);
		borderpane.getChildren().add(joueur.getView());
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stop){
						System.out.println("fini");
						gameLoop.stop();
					}
					else if (temps>5){
						leCercle.setLayoutX(leCercle.getLayoutX()+5);
						leCercle.setLayoutY(leCercle.getLayoutY()+5);
						joueur.getView().setY(joueur.getPosition().getY());
						joueur.getView().setX(joueur.getPosition().getX());
						
						joueur.seDeplace(keymanager.getMovementInputs(temps));
						
//						if(keymanager.getKeyState(Axe.HAUT)) {
//							joueur.seDeplace(Axe.HAUT);
//							joueur.getView().setY(joueur.getPosition().getY());
//						}
//						if(keymanager.getKeyState(Axe.BAS)) {
//							joueur.seDeplace(Axe.BAS);
//							joueur.getView().setY(joueur.getPosition().getY());
//						}
//						if(keymanager.getKeyState(Axe.GAUCHE)) {
//							joueur.seDeplace(Axe.GAUCHE);
//							joueur.getView().setX(joueur.getPosition().getX());
//						}
//						if(keymanager.getKeyState(Axe.DROITE)) {
//							joueur.seDeplace(Axe.DROITE);
//							joueur.getView().setX(joueur.getPosition().getX());
//						}
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}

