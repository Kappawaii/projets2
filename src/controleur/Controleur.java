package controleur;

import java.util.ArrayList;
import java.util.Iterator;

import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
import vue.sprite.Sprite;
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
	private Pane vie;

	private Label saisieDialogue = new Label();
	private Label cliquezPourContinuer = new Label();
	private Label armeSelection = new Label();

	private ArrayList<Input> inputs = new ArrayList<Input>();
	private Timeline gameLoop;
	private Modele modele;
	private Affichage affichage;
	private KeyManager keymanager;
	private KeyFrame play;
	private Cinematique cinematiqueDebut;

	private static int displayScale = 4;
	private boolean jeuEnPause = false;
	private boolean debugMode = false;
	private boolean gameOver;

	private boolean wasEnterPressed = false;

	@FXML
	public void initialize() {
		//initialisation modele et Affichage
		modele = new Modele();
		affichage = new Affichage(modele, tuiles, entites, displayScale);
		modele.setAffichage(affichage);
		//initialisation de toutes les ressources
		initRessources();
		//initialisation gameLoop
		initAnimation();
		//démarrage gameLoop
		gameLoop.play();
	}

	public Sprite[] initGestionVie() {
		Tileset coeurs1 = new Tileset("sprites/gestionVie/coeursDeVie.png", displayScale); //1e coeur en partant de la gauche
		Sprite sprCoeurs1 = new Sprite(coeurs1, 5, 0);
		vie.getChildren().add(sprCoeurs1.getView());

		Tileset coeurs2 = new Tileset("sprites/gestionVie/coeursDeVie.png", displayScale);
		Sprite sprCoeurs2 = new Sprite(coeurs2, 5, 0);
		ImageView iv2 = sprCoeurs2.getView();
		vie.getChildren().add(iv2);
		vie.getChildren().get(1).setLayoutX(80);

		Tileset coeurs3 = new Tileset("sprites/gestionVie/coeursDeVie.png", displayScale);
		Sprite sprCoeurs3 = new Sprite(coeurs3, 5, 0);
		ImageView iv3 = sprCoeurs3.getView();
		vie.getChildren().add(iv3);
		vie.getChildren().get(2).setLayoutX(160);
		System.out.println();
		return new Sprite[] {sprCoeurs1,sprCoeurs2,sprCoeurs3};
	}

	public void mouseClicked() {
		jeuEnPause = false;
	}

	public void gestionEnter() {
		if(inputs.contains(Input.ENTREE)) {
			if(!wasEnterPressed) {
				cinematiqueDebut.unPause();
				if(gameOver)
					Runtime.getRuntime().exit(0);
				wasEnterPressed = true;
			}

		}
		else {
			wasEnterPressed = false;
		}
	}
	private void initAnimation() {
		modele.changerMap(0,debugMode);
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		joueurPane.getChildren().add(modele.getJoueur().getSprite().getView());

		affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());
		modele.getCurrentNiveau().getEntites().add(modele.getPersonnagesACharger(1).get(0));
		modele.getAffichage().ajouterPersonnage(modele.getPersonnagesACharger(1).get(0));

		modele.getJoueur().setActive(true);
		modele.getJoueur().setControllable(false);

		play = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					keymanager.updateInputs();

					if(jeuEnPause){
						System.out.println("Jeu en pause");
					}
					else {
						gestionEnter();

						if(!modele.getJoueur().isAlive()) {
							saisieDialogue.setText("Game Over, Click to quit game");
							gameOver = true;
						}
						else {
							//test mode debug F/F activé
							if(debugMode)
								jeuEnPause = true;

							//cinématique si activée
							if(!cinematiqueDebut.isfinished())
								cinematiqueDebut.play();

							if(modele.getJoueur().currentArme() != null)
								armeSelection.setText(modele.getJoueur().currentArme().toString());
								armeSelection.setTextFill(Color.web("#FEFEFE"));
							//action du joueur
							modele.getJoueur().unTour();
							//action des personnages
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
					}
				}));		
		gameLoop.getKeyFrames().add(play);
	}

	private void initTextAffichage() {
		armeSelection.setLayoutX(0);
		armeSelection.setLayoutY(100);
		saisieDialogue.setTextFill(Color.web("#FFFFFF"));
		joueurPane.getChildren().add(armeSelection);

		dialogueBox.getChildren().add(saisieDialogue);	
		saisieDialogue.setLayoutX(200);
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
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "...\n"
				+ "\n"
				+ "Cliquez pour commencer le jeu"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*baillement*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : J'ai passe une tres bonne nuit !"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*bruit bizarre*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new PassiveClip(Input.HAUT,25));
		cinematiqueDebut.addClip(new PassiveClip(Input.DROITE,60));
		cinematiqueDebut.addClip(new PassiveClip(Input.HAUT,10));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Le bruit vient de l'armoire je pense"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*ouvre l'armoire*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Il y a un truc bizarre sur \n mes vetements..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : C'est..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Une epee ?!"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*Joueur prend l'epee*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Je devrai peut-etre\n"
				+ "aller la donner a  l'aubergiste..."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "?????? : non !!"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : QUOI ?! QUI A PARLE ?!"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "?????? : Euh... Moi."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*lache l'epee de stupeur*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "?????? : Aie !!"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Tu... es vivant ? Qui es-tu ?"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "?????? : Je m'appelle Aedriel.\n"
				+ "\n"
				+ "Et oui, je suis vivant.\n"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Aedriel : Pourrais tu ... m'aider ?\n"
				+ "\n"
				+ "Je crois que je suis en danger."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue ,"J'aimerais retrouver mon proprietaire...\n"
				+ "\n"
				+ "je crois qu'il m'a oublié."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : Je viens d'arriver dans ce village,\n"
				+ "\n"
				+ "je suis en plein voyage."));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Joueur : On pourrait essayer de le retrouver\n"
				+ "\n"
				+ "ensemble si tu le souhaites !"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "Aedriel : Merci beaucoup !"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));

		cinematiqueDebut.addClip(new TextClip(saisieDialogue, "*prend l'Epee*"));
		cinematiqueDebut.addClip(new PauseClip(cinematiqueDebut));
		cinematiqueDebut.addClip(new TextClip(saisieDialogue, ""));
		affichage.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		affichage.addTileset(new Tileset("sprites/personnages/joueur/walking.png", displayScale));
		affichage.addTileset(new Tileset("sprites/personnages/gobelin/walking.png", displayScale));
		affichage.addTileset(new Tileset("sprites/personnages/plante/plantespr.png", displayScale));

		Animation walking = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(1),displayScale, 0);
		Animation walking2 = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(2),displayScale, 0);
		Animation walking3 = new Animation(6/*framesBetweenSprites*/, affichage.getTileset(3),displayScale, 0);

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
						modele,inputs, initGestionVie()));

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
		modele.addNiveau(
				new Niveau("maps/level2.tmx",
						affichage.getTileset(0),
						null,
						displayScale,
						2,
						modele));
		initTextAffichage();
	}

	public int getScale() {
		return displayScale;
	}


	//		private KeyFrame getCurrentKeyFrame() {
	//			return cinematique;
	//		}
}
