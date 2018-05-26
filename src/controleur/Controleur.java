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
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Controleur {


	@FXML
	private TilePane tuiles = new TilePane();
	@FXML
	private BorderPane borderpane = new BorderPane();
	@FXML
	private StackPane stackpane = new StackPane();
	
	// permet de definir l'animation
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
		modele.setJoueur(new Joueur("test", 0, 
				new Coordonnee(100,100),1,
				new Tileset("sprites/personnages/joueur/personnage.png", displayScale, 16, 16)));
		modele.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale, 968, 526));
		modele.addTileset(new Tileset("sprites/personnages/joueur/rgb.png", displayScale, 50, 16));
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
		System.out.println(keymanager);
		modele.getJoueur().seDeplace(Axe.GAUCHE);
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
		borderpane.getChildren().add(modele.getJoueur().getSprite().getView());
		for (int i = 0; i < borderpane.getChildren().size(); i++) {
			borderpane.getChildren().get(i).equals(modele.getJoueur().getSprite().getView());
			marqueur = i;
		}
		
		modele.getJoueur().getAnimations().add(new Animation(3));
		modele.getJoueur().getAnimations().get(0).genererAnimation(modele.getTileset(1), displayScale);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopjeu){
						System.out.println("fini");
						gameLoop.stop();
					}
					else {
						/*precX=modele.getJoueur().getPosition().getX();
						precY=modele.getJoueur().getPosition().getY(); */
						if(temps%60>50) {
							modele.getJoueur().getSprite().setView(modele.getJoueur().getAnimations().get(0).nextSprite().getView());
							borderpane.getChildren().set(marqueur, modele.getJoueur().getSprite().getView());
						}
						//borderpane.getChildren().set(marqueur, modele.getJoueur().getAnimations().get(0).nextSprite().getView());
						
						modele.getJoueur().getSprite().getView().setY(modele.getJoueur().getPosition().getY()*displayScale);
						modele.getJoueur().getSprite().getView().setX(modele.getJoueur().getPosition().getX()*displayScale);
						//System.out.println("avant le deplacement" + modele.getJoueur().getPosition().getX() + " " + modele.getJoueur().getPosition().getY());
						
						modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps));
					/*	if (modele.getPlateau(0).getCellule(modele.getJoueur().getPosition().getX(),modele.getJoueur().getPosition().getY()).getId()!=1126){
							
						}*/
						System.out.println("après deplacement "  + modele.getJoueur().getPosition().getX() + " " + modele.getJoueur().getPosition().getY());
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}

