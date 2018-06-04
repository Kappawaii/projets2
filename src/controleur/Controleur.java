package controleur;

import java.util.ArrayList;

import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.Modele;
import modele.animation.Animation;
import modele.cellule.Cellule;
import modele.chemin.PathFinding;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;
import modele.personnage.joueur.Joueur;
import modele.plateau.BuilderPlateau;
import modele.plateau.Plateau;
import vue.tileset.Tileset;

public class Controleur {
	
	@FXML
	private Pane tuiles = new Pane();
	@FXML
	private BorderPane borderpane = new BorderPane();

	int testAnimationManager = 0;
	private Timeline gameLoop;
	private int temps;
	private Modele modele;

	boolean stopJeu = false;
	static int displayScale = 4;
	KeyManager keymanager;
	int marqueur;
	int remind;
	int remindend;
	boolean jeuEnPause = false;
	boolean debugMode = false;
	boolean scrollingMap;
	
	@FXML
	public void initialize() {
		modele = new Modele();
		//initialisation du joueur
		modele.setJoueur(new Joueur("test", 0, 
				new Coordonnee(100,100),1,
				new Tileset("sprites/personnages/joueur/personnage.png", displayScale)));
		modele.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/debug_right.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/debug_down.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/debug_left.png", displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/debug_up.png", displayScale));
		modele.addPlateau(new Plateau());
		BuilderPlateau a = new BuilderPlateau();
		a.remplirPlateau(modele.getPlateau(0), modele.getTileset(0), displayScale);
		ajouterCarte(modele.getPlateau(0).getPlateau());
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

	@SuppressWarnings("static-access")
	public void mouseClicked() {
//		System.out.println(keymanager);
//		modele.getJoueur().seDeplace(Axe.GAUCHE);
//
//		modele.getJoueur().getAnimationManager().setCurrentAnimation(testAnimationManager++%modele.getJoueur().getAnimations().size());
//		tuiles.getChildren().get(remind).setLayoutX(tuiles.getChildren().get(remind).getLayoutX()+1);
//		System.out.println(tuiles.getLayoutX());
		if(debugMode)
			jeuEnPause = false;
		PathFinding chemin = new PathFinding();
		ArrayList<Coordonnee> a = chemin.chemin(modele.getPlateau(0), modele.getJoueur().getPosition(), new Coordonnee(100,100));
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
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
		borderpane.getChildren().get(borderpane.getChildren().size()-1).toFront();
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
						}if(!jeuEnPause) {
							if(debugMode && !jeuEnPause)
								jeuEnPause = true;
							modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps),modele);
							//non-scrolling map
							mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());
							mettreAJourCarte(modele.getPlateau(0).getPlateau());
							//updatePositionPersonnage(modele.getJoueur(), new Coordonnee(100,100));
//							System.out.print("Joueur :");
//							modele.getJoueur().getCollider().sysout();
							if(scrollingMap)
							centerMaptoPosition(modele.getJoueur().getPosition());
						}
						
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}
	
	void ajouterCarte(Cellule cellules[][]) {
		int index = tuiles.getChildren().size();
		remind = index;
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				//TODO pourquoi X et y inversés ?
				cellules[x][y].getSprite().getView().setLayoutX(cellules[x][y].getPos().getY()*displayScale);
				cellules[x][y].getSprite().getView().setLayoutY(cellules[x][y].getPos().getX()*displayScale);
				tuiles.getChildren().add(index+x*cellules.length+y, cellules[x][y].getSprite().getView());
				remindend = index;
			}		
		}
		//		tuiles.getChildren().get(40).setLayoutX(-30);
		//		System.out.println(tuiles.getChildren().get(41).getLayoutX());
		//		System.out.println(tuiles.getChildren().get(42).getLayoutX());
		//		tuiles.getChildren().get(15).setLayoutX(-30);
		//		System.out.println();
	}
	
	private void mettreAJourCarte(Cellule cellules[][]) {
		for(int x = 0; x < cellules.length; x++) {
			for(int y = 0; y < cellules[x].length; y++) {
				//TODO pourquoi X et y inversés ?
				tuiles.getChildren().set(remind+x*cellules.length+y, cellules[x][y].getSprite().getView());
				
			}		
		}		
	}

	private void mettreAJourPositionPersonnage(Personnage pers,Coordonnee pos) {
		modele.getJoueur().getAnimationManager().updateAnimationsPos(pos, displayScale);
//		pers.getSprite().getView().setY(pos.getY()*displayScale);
//		pers.getSprite().getView().setX(pos.getX()*displayScale);
	}
	
	//scrolling map
	private void centerMaptoPosition(Coordonnee coordonnee) {
		tuiles.setTranslateX(tuiles.getLayoutBounds().getMaxX()/2-coordonnee.getX()*displayScale);
		tuiles.setTranslateY(tuiles.getLayoutBounds().getMaxY()/2-coordonnee.getY()*displayScale);
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
	}
}

