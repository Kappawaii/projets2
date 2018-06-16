package controleur;

import java.util.ArrayList;
import java.util.Iterator;

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
import modele.cinematique.Cinematique;
import modele.cinematique.PassiveClip;
import modele.cinematique.PauseClip;
import modele.cinematique.TextClip;
import modele.coordonnee.Coordonnee;
import modele.niveau.Niveau;
import modele.personnage.Personnage;
import modele.personnage.ennemis.Gobelin;
import modele.personnage.ennemis.Plante;
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
	private Pane joueurPane;
	@FXML
	private Pane dialogueBox;
	@FXML
	private Label saisieDialogue = new Label();
	@FXML
	private Label cliquezPourContinuer = new Label();

	//Label debug position joueur
	private Label joueurpos = new Label();
	private Timeline gameLoop;
	private Modele modele;
	private Affichage affichage;
	private static int displayScale = 4;
	private KeyManager keymanager;
	private boolean jeuEnPause = false;
	private boolean debugMode = false;
	private KeyFrame play;
	private Cinematique cinematiqueDebut;
	private ArrayList<Input> inputs = new ArrayList<Input>();

	@FXML
	public void initialize() {
		//initialisation modele et Affichage
		modele = new Modele();
		affichage = new Affichage(modele, tuiles, entites, displayScale);
		modele.setAffichage(affichage);
		//initialisation de toutes les ressources
		initRessources();
		//initialisation gameLoop
		initTextAffichage();
		initAnimation();
		gameLoop.play();
		//tests pathfinding

	}

	public void mouseClicked() {
		cinematiqueDebut.unPause();
		jeuEnPause = false;
	}

	private void initAnimation() {
		modele.changerMap(0,debugMode);
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		joueurPane.getChildren().add(modele.getJoueur().getSprite().getView());

		joueurPane.getChildren().add(joueurpos);
		joueurpos.setTextFill(Color.web("#AAEAAE"));

		affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());
		modele.getCurrentNiveau().getEntites().add(modele.getPersonnagesACharger(1).get(0));
		modele.getAffichage().ajouterPersonnage(modele.getPersonnagesACharger(1).get(0));

		modele.getJoueur().setActive(true);
		//		modele.getJoueur().setControllable(false);
		modele.getJoueur().setControllable(true);

		play = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(jeuEnPause){
						System.out.println("Jeu en pause");
					}
					else {
						keymanager.updateInputs();

						//test mode debug F/F activé
						if(debugMode)
							jeuEnPause = true;

						modele.getJoueur().unTour();

						//							if(!cinematiqueDebut.isfinished())
						//								cinematiqueDebut.play();

						//debug position joueur							
						joueurpos.setText(modele.getJoueur().getPosition().toString() + "\n" + modele.getPersonnagesACharger(1).get(0).getPosition().toString());

						for (Iterator<Personnage> iterator = modele.getPersonnages().iterator(); iterator.hasNext();) {
							Personnage personnage = iterator.next();
							personnage.unTour();

						}
//						System.out.println(modele.getPersonnages().get(1).getPosition());
						//rafraichissement de l'affichage

						//avec scrolling map
						if(affichage.isScrollingMapEnabled()) {
							
							affichage.mettreAJourPositionPersonnage(modele.getJoueur(), new Coordonnee(96,96));

							for (Iterator<Personnage> iterator = modele.getPersonnages().iterator(); iterator.hasNext();) {
								Personnage personnage = iterator.next();
								affichage.mettreAJourPositionPersonnage(personnage, personnage.getPosition());
							}
							affichage.centerPanetoPosition(tuiles,modele.getJoueur().getPosition());
							affichage.centerPanetoPosition(entites,modele.getJoueur().getPosition());
						}
						//sans scrolling map
						else {
							for (Iterator<Personnage> iterator = modele.getPersonnages().iterator(); iterator.hasNext();) {
								Personnage personnage = iterator.next();
								affichage.mettreAJourPositionPersonnage(personnage, personnage.getPosition());
							}
							affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());	
						}

					}
				}));		
		gameLoop.getKeyFrames().add(play);
	}

	private void initTextAffichage() {
		dialogueBox.getChildren().add(saisieDialogue);	
		saisieDialogue.setLayoutX(290);
		saisieDialogue.setLayoutY(600);
		//saisieDialogue.setTextAlignment(TextAlignment.CENTER);
		saisieDialogue.setTextFill(Color.web("#FFFFFF"));
		saisieDialogue.setFont(new Font("Open Sans", 22));
		saisieDialogue.setText("");

		dialogueBox.getChildren().add(cliquezPourContinuer);	
		cliquezPourContinuer.setLayoutX(450);
		cliquezPourContinuer.setLayoutY(650);
		cliquezPourContinuer.setTextFill(Color.web("#FFFFFF"));
		cliquezPourContinuer.setFont(new Font("Open Sans", 12));
		cliquezPourContinuer.setText("");
	}


	public void initInputs() {
		keymanager = new KeyManager(rootpane.getScene());
		keymanager.setInputList(inputs);
		keymanager.addKey(Input.HAUT,"Z");
		keymanager.addKey(Input.GAUCHE,"Q");
		keymanager.addKey(Input.BAS,"S");
		keymanager.addKey(Input.DROITE,"D");
		keymanager.addKey(Input.ENTREE,"enter");
		keymanager.addKey(Input.UTILISER, "E");
		keymanager.addKey(Input.AHAUT, "UP");
		keymanager.addKey(Input.AGAUCHE, "LEFT");
		keymanager.addKey(Input.ADROITE, "right");
		keymanager.addKey(Input.ABAS, "DOWN");
	}

	private void initRessources() {
		/**
		 * cinématique début du jeu
		 */
		cinematiqueDebut = new Cinematique(modele);
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "poc poc poc"));
		cinematiqueDebut.addClip(new TextClip(cliquezPourContinuer, "Cliquez pour continuer..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new PassiveClip(Input.BAS,16));
		cinematiqueDebut.addClip(new TextClip(cliquezPourContinuer, ""));
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "il y a du bruit dans l'armoire"));
		cinematiqueDebut.addClip(new TextClip(cliquezPourContinuer, "Cliquez pour continuer..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new PassiveClip(Input.DROITE,28));
		cinematiqueDebut.addClip(new PassiveClip(Input.HAUT,41));
		cinematiqueDebut.addClip(new PassiveClip(Input.DROITE,30));
		cinematiqueDebut.addClip(new PassiveClip(Input.HAUT,1));
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Never gonna give you up omegalul"));
		cinematiqueDebut.addClip(new TextClip(cliquezPourContinuer, "Cliquez pour continuer..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, ""));
		cinematiqueDebut.addClip(new TextClip(cliquezPourContinuer, ""));

		affichage.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		affichage.addTileset(new Tileset("sprites/personnages/joueur/walking.png", displayScale));

		Animation walking = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);
		Animation walking2 = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);
		Animation walking3 = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);

		modele.getPersonnages().add(
				new Gobelin("gobelin", 10,
						new Coordonnee(0,0),16,
						walking2,
						modele));
		modele.getPersonnages().add(
				new Plante(new Coordonnee(0,0), walking3, modele));

		modele.setJoueur(
				new Joueur("joueur", 12, 
						new Coordonnee(53,108),1,
						walking,
						modele,inputs));

		modele.addNiveau(
				new Niveau("maps/level0.tmx",
						affichage.getTileset(0),
						null,
						displayScale,
						0,
						modele));

		modele.addNiveau(
				new Niveau("maps/level1.tmx",
						affichage.getTileset(0),
						null,
						displayScale,
						1,
						modele));
	}

	public int getScale() {
		return displayScale;
	}


	//		private KeyFrame getCurrentKeyFrame() {
	//			return cinematique;
	//		}
}