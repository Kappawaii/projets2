package controleur;

import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import modele.Modele;
import modele.animation.Animation;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.niveau.Niveau;
import modele.personnage.joueur.Joueur;
import vue.Affichage;
import vue.tileset.Tileset;

public class Controleur {

	@FXML
	private Pane rootpane;
	@FXML
	private Pane tuiles;
	@FXML
	private Pane entites;

	@FXML
	private Pane dialogueBox;
	@FXML
	private Label saisieDialogue;

	private Timeline gameLoop;
	private int temps;
	private Modele modele;
	private Affichage affichage;
	boolean stopJeu = false;
	static int displayScale = 4;
	private KeyManager keymanager;
	boolean jeuEnPause = false;
	boolean debugMode = false;


	@FXML
	public void initialize() {
		modele = new Modele();
		affichage = new Affichage(modele, tuiles, entites, displayScale);
		modele.setAffichage(affichage);
		//initialisation du joueur
		modele.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking.png", displayScale));
		initRessources();
		initAnimation();
		gameLoop.play();
	}

	public int getScale() {
		return displayScale;
	}

	public void initDialogue(String text) {
		dialogueBox.getChildren().add(saisieDialogue);
		saisieDialogue.setLayoutX(290);
		saisieDialogue.setLayoutY(600);
		saisieDialogue.setTextFill(Color.web("#FFFFFF"));
		saisieDialogue.setFont(new Font("Open Sans", 28));
		String qqchose = text;
		saisieDialogue.setText(qqchose);
	}

	public void initInputs() {
		System.out.println(rootpane.getScene());
		keymanager = new KeyManager(rootpane.getScene());
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

	private void initRessources() {
		Animation walking = new Animation(6/*framesBetweenSprites*/, modele.getTileset(1),displayScale, 0);
		modele.setJoueur(new Joueur("test", 0, 
				new Coordonnee(60,100),1,
				new Tileset("sprites/personnages/joueur/personnage.png", displayScale),
				walking));

		modele.addNiveau(
				new Niveau("maps/level0.tmx",
						modele.getTileset(0),
						null,
						displayScale,
						0,
						modele));
		modele.addNiveau(
				new Niveau("maps/level1.tmx",
						modele.getTileset(0),
						null,
						displayScale,
						1,
						modele));

	}

	public void mouseClicked() {
		//modele.getJoueur().setPosition(new Coordonnee(0,0));
	}

	private void initAnimation() {
		modele.changerMap(0,debugMode);
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);	
		entites.getChildren().add(modele.getJoueur().getSprite().getView());
		Label joueurpos = new Label();
		entites.getChildren().add(joueurpos);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopJeu){
						System.out.println("fini");
						gameLoop.stop();
					}
					else {
						if(!jeuEnPause) {
							if(debugMode && !jeuEnPause)
								jeuEnPause = true;
							modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps),modele);
							
							//non-scrolling map
							joueurpos.setText(modele.getJoueur().getPosition().toString());
							
							if(affichage.isScrollingMapEnabled()) {
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(), new Coordonnee(96,96));
								affichage.centerMaptoPosition(modele.getJoueur().getPosition());
							}
							else {
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());	
							}

						}

					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}
}
