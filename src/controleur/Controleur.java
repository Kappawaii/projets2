package controleur;


import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.personnage.joueur.Joueur;
import modele.plateau.Cellule;
import modele.plateau.Plateau;
import modele.plateau.BuilderPlateau;
import vue.tileset.Tileset;


public class Controleur {


	@FXML
	private TilePane tuiles = new TilePane();
	@FXML
	private BorderPane borderpane = new BorderPane();
	@FXML
	private StackPane stackpane = new StackPane();
	

	private Timeline gameLoop;
	private int temps;
	private Modele modele;

	boolean stopjeu = false;
	static int displayScale = 4;
	KeyManager keymanager;
	int marqueur;

	@FXML
	public void initialize() {
		modele = new Modele();
		//initialisation du joueur
		modele.setJoueur(new Joueur("test", 0, 
				new Coordonnee(100,100),1,
				new Tileset("sprites/personnages/joueur/personnage.png", displayScale)));
		modele.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking_down.png", displayScale));
		modele.addPlateau(new Plateau());
		BuilderPlateau a = new BuilderPlateau();
		a.remplirPlateau(modele.getPlateau(0), modele.getTileset(0), displayScale);
		afficherCarte(modele.getPlateau(0).getPlateau());
		initAnimation();
		gameLoop.play();
	}

	public int getScale() {
		return displayScale;
	}

	public void init() {
		System.out.println(borderpane.getScene());
		keymanager = new KeyManager(borderpane.getScene());
		keymanager.addKey(Axe.HAUT,"Z");
		keymanager.addKey(Axe.GAUCHE,"Q");
		keymanager.addKey(Axe.BAS,"S");
		keymanager.addKey(Axe.DROITE,"D");
		keymanager.addKey(Axe.ENTREE,"enter");
		keymanager.addKey(Axe.UTILISER, "E");
		keymanager.addKey(Axe.AHAUT, "UP");
		keymanager.addKey(Axe.AGAUCHE, "LEFT");
		keymanager.addKey(Axe.ADROITE, "right");
		keymanager.addKey(Axe.ABAS, "DOWN");
	}

	public void mouseClicked() {
//		System.out.println(keymanager);
//		modele.getJoueur().seDeplace(Axe.GAUCHE);
		
	}


	void afficherCarte(Cellule cellules[][]) {
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				//afficherImage(cellules[x][y]);
				tuiles.getChildren().add(cellules[x][y].getSprite().getView());
			}		
		}
	}

	//Afficher l'image
	void afficherImage(int i) {
		int id = i-1;
		tuiles.getChildren().add(new Cellule(modele.getTileset(0),id,displayScale).getSprite().getView());
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		modele.getJoueur().setImage("file:src/vue/personnage.png", displayScale,0);
		Animation temp = new Animation(1/*framesBetweenSprites*/, modele.getTileset(1),displayScale);
		modele.getJoueur().getAnimations().add(temp);
		borderpane.getChildren().add(modele.getJoueur().getSprite().getView());
		for (int i = 0; i < borderpane.getChildren().size(); i++) {
			borderpane.getChildren().get(i).equals(modele.getJoueur().getSprite().getView());
			marqueur = i;
			System.out.println(i);
		}
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopjeu){
						System.out.println("fini");
						gameLoop.stop();
					}
					else {

						if(temps%20==0) {
							//Animations
							modele.getJoueur().getSprite().setView(modele.getJoueur().getAnimations().get(0).next().getView());
							borderpane.getChildren().set(marqueur, modele.getJoueur().getSprite().getView());
						}
						modele.getJoueur().getSprite().getView().setY(modele.getJoueur().getPosition().getY()*displayScale);
						modele.getJoueur().getSprite().getView().setX(modele.getJoueur().getPosition().getX()*displayScale);
						modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps));
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}

