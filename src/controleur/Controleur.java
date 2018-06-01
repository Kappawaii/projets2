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
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.BuilderPlateau;
import modele.plateau.Cellule;
import modele.plateau.Plateau;
import vue.tileset.Tileset;


public class Controleur {


	@FXML
	private TilePane tuiles = new TilePane();
	@FXML
	private BorderPane borderpane = new BorderPane();
	@FXML
	private StackPane stackpane = new StackPane();
	
	int testAnimationManager = 0;
	private Timeline gameLoop;
	private int temps;
	private Modele modele;

	boolean stopJeu = false;
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
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking_right.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking_down.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking_left.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking_up.png", displayScale));
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
		
		modele.getJoueur().getAnimationManager().setCurrentAnimation(testAnimationManager++%modele.getJoueur().getAnimations().size());

	}


	void afficherCarte(Cellule cellules[][]) {
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				tuiles.getChildren().add(cellules[x][y].getSprite().getView());
				System.out.println(cellules[x][y].getPos());
			}		
		}
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		modele.getJoueur().setImage("file:src/vue/personnage.png", displayScale,0);
		initJoueur();
		borderpane.getChildren().add(modele.getJoueur().getSprite().getView());
		for (int i = 0; i < borderpane.getChildren().size(); i++) {
			borderpane.getChildren().get(i).equals(modele.getJoueur().getSprite().getView());
			marqueur = i;
		}
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopJeu){
						System.out.println("fini");
						gameLoop.stop();
					}
					else {

						if(temps%10==0) {
							//Animations
							
							modele.getJoueur().getSprite().setView(modele.getJoueur().getAnimationManager().nextFrame().getView());;
							borderpane.getChildren().set(marqueur, modele.getJoueur().getSprite().getView());
						}
						updatePositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	private void updatePositionPersonnage(Personnage pers,Coordonnee pos) {
		pers.seDeplace(keymanager.getMovementInputs(temps));
		pers.getSprite().getView().setY(pos.getY()*displayScale);
		pers.getSprite().getView().setX(pos.getX()*displayScale);
	}

	private void initJoueur() {
		//initialisation joueur
		Animation walking_right = new Animation(1/*framesBetweenSprites*/, modele.getTileset(1),displayScale);
		Animation walking_down = new Animation(1/*framesBetweenSprites*/, modele.getTileset(2),displayScale);
		Animation walking_left = new Animation(1/*framesBetweenSprites*/, modele.getTileset(3),displayScale);
		Animation walking_up = new Animation(1/*framesBetweenSprites*/, modele.getTileset(4),displayScale);
		modele.getJoueur().getAnimations().add(walking_right);
		modele.getJoueur().getAnimations().add(walking_down);
		modele.getJoueur().getAnimations().add(walking_left);
		modele.getJoueur().getAnimations().add(walking_up);
		System.out.println(modele.getTileset(1).getPxParImage());
		System.out.println(modele.getTileset(2).getPxParImage());
	}
}

