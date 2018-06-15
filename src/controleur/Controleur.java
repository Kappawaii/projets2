package controleur;

import java.util.ArrayList;

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
import modele.cellule.Cellule;
import modele.chemin.PathFinding;
import modele.cinematique.Cinematique;
import modele.cinematique.PassiveClip;
import modele.cinematique.PauseClip;
import modele.coordonnee.Coordonnee;
import modele.niveau.Niveau;
import modele.personnage.Personnage;
import modele.personnage.ennemis.Gobelin;
import modele.personnage.joueur.Joueur;
import modele.plateau.Plateau;
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

	//Label debug position joueur
	private Label joueurpos = new Label();

	private Timeline gameLoop;
	private Modele modele;
	private Affichage affichage;
	private boolean stopJeu = false;
	private static int displayScale = 4;
	private KeyManager keymanager;
	boolean jeuEnPause = false;
	boolean debugMode = false;
	private KeyFrame play;
	private Cinematique cinematiqueDebut;

	@FXML
	public void initialize() {
		//initialisation modele et Affichage
		modele = new Modele();
		affichage = new Affichage(modele, tuiles, entites, displayScale);
		modele.setAffichage(affichage);
		//initialisation de toutes les ressources
		initRessources();
		//initialisation gameLoop
		initAffichage();
		initAnimation();
		gameLoop.play();
      Plateau currentPlateau = modele.getNiveau(0).getPlateau();
      PathFinding path = new PathFinding(currentPlateau,  currentPlateau.getCellule(8, 8),currentPlateau.getCellule(6, 7));
      ArrayList<Cellule> ccc = path.chemin();
      for (int i = 0; i < ccc.size(); i++) {
			Cellule cell = ccc.get(i);
      			System.out.println("Passe par la case " + cell.getPos().getX() + " " + cell.getPos().getX());
     		}
	}



	public int getScale() {
		return displayScale;
	}

	public void initInputs() {
		keymanager = new KeyManager(rootpane.getScene());
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
		cinematiqueDebut = new Cinematique(modele);
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new PassiveClip(Input.BAS,16));
		cinematiqueDebut.addClip(new PassiveClip(Input.DROITE,28));
		cinematiqueDebut.addClip(new PassiveClip(Input.HAUT,41));
		cinematiqueDebut.addClip(new PassiveClip(Input.DROITE,30));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		affichage.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		affichage.addTileset(new Tileset("sprites/personnages/joueur/walking.png", displayScale));
		Animation walking = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);
		Animation walking2 = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);

		modele.getEntitesACloner().add(
				new Gobelin("plante", 0,
						new Coordonnee(100,100),1,
						walking2,
						modele));

		modele.setJoueur(
				new Joueur("joueur", 0, 
						new Coordonnee(53,108),1,
						walking,
						modele));

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

	private void initAffichage() {
		dialogueBox.getChildren().add(saisieDialogue);	
		saisieDialogue.setLayoutX(290);
		saisieDialogue.setLayoutY(600);
		saisieDialogue.setTextFill(Color.web("#FFFFFF"));
		saisieDialogue.setFont(new Font("Open Sans", 28));
		String qqchose = "";
		saisieDialogue.setText(qqchose);
	}

	public void mouseClicked() {
		//		modele.getJoueur().setPosition(new Coordonnee(0,0));
		//		entites.getChildren().add(((Personnage) modele.getEntitesACloner().get(0)).getSprite().getView());
		//				System.out.println(joueurPane.getTranslateX());
		//				System.out.println(entites.getTranslateX());
		cinematiqueDebut.unPause();
		//joueurHasControl = true;
		jeuEnPause = false;
	}

	private void initAnimation() {
		modele.changerMap(0,debugMode);
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		joueurPane.getChildren().add(modele.getJoueur().getSprite().getView());
		//		Personnage ia = ((Personnage) modele.getCurrentNiveau().getEntites().get(0));
		//		entites.getChildren().add(ia.getSprite().getView());
		joueurPane.getChildren().add(joueurpos);
		joueurpos.setTextFill(Color.web("#AAEAAE"));
		affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());
		modele.getCurrentNiveau().getEntites().add(modele.getPersonnagesACharger(1).get(0));
		modele.getAffichage().ajouterEntite(modele.getPersonnagesACharger(1).get(0));
		modele.getJoueur().setActive(true);
		play = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopJeu){
						System.out.println("JEU FINI");
						gameLoop.stop();
					}
					else {
						if(!jeuEnPause) {
							//test mode Frame par Frame activé
							if(debugMode && !jeuEnPause)
								jeuEnPause = true;

//								System.out.println("loop");
							
							modele.getJoueur().seDeplace(keymanager.getMovementInputsList());
							if(!cinematiqueDebut.isfinished())
								cinematiqueDebut.play();
//							debug position joueur
							
							joueurpos.setText(modele.getJoueur().getPosition().toString() + modele.getPersonnagesACharger(1).get(0).getPosition().toString());

							((Gobelin) modele.getEntitesACloner().get(0)).jouer();

							//rafraichissement de l'affichage

							//avec scrolling map
							if(affichage.isScrollingMapEnabled()) {
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(), new Coordonnee(96,96));
								affichage.mettreAJourPositionPersonnage((Personnage) modele.getEntitesACloner().get(0), modele.getEntitesACloner().get(0).getPosition());
								affichage.centerPanetoPosition(tuiles,modele.getJoueur().getPosition());
								affichage.centerPanetoPosition(entites,modele.getJoueur().getPosition());
							}
							//sans scrolling map
							else {
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());	
							}
						}

					}
					//					temps++;
				}));
		gameLoop.getKeyFrames().add(play);
	}

	//		private KeyFrame getCurrentKeyFrame() {
	//			return cinematique;
	//		}


}