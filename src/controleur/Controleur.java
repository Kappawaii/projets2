package controleur;

import controleur.inputManager.KeyManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
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
	private Pane rootpane = new Pane();
	@FXML
	private Pane tuiles = new Pane();
	@FXML
	private Pane entites = new Pane();

	int testAnimationManager = 0;
	private Timeline gameLoop;
	private int temps;
	private Modele modele = new Modele();
	private Affichage affichage;
	boolean stopJeu = false;
	static int displayScale = 4;
	private KeyManager keymanager;
	int marqueur;
	int remind;
	int remindend;
	boolean jeuEnPause = false;
	boolean debugMode = false;
	boolean scrollingMap = false;
	int idNiveautest;
	
	@FXML
	public void initialize() {
		affichage = new Affichage(modele, tuiles, entites, displayScale);
		//initialisation du joueur
		modele.setJoueur(new Joueur("test", 0, 
				new Coordonnee(100,100),1,
				new Tileset("sprites/personnages/joueur/personnage.png", displayScale)));
		modele.addTileset(new Tileset("sprites/tilesets/tileset0.png",displayScale));
		modele.addTileset(new Tileset("sprites/personnages/joueur/walking.png", displayScale));
		initRessources();
		initAnimation();
		gameLoop.play();
	}
	
	public int getScale() {
		return displayScale;
	}

	public void initInputs() {
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
		modele.getJoueur().getAnimations().add(walking);
//		modele.getJoueur().getAnimations().add(walking_right);
//		modele.getJoueur().getAnimations().add(walking_down);
//		modele.getJoueur().getAnimations().add(walking_left);
//		modele.getJoueur().getAnimations().add(walking_up);
		modele.addNiveau(new Niveau("maps/level0.tmx", modele.getTileset(0), null, displayScale));
		modele.addNiveau(new Niveau("maps/sans titre.tmx", modele.getTileset(0), null, displayScale));
	}
	
	public void mouseClicked() {
//		System.out.println(keymanager);
//		modele.getJoueur().seDeplace(Axe.GAUCHE);
//
//		modele.getJoueur().getAnimationManager().setCurrentAnimation(testAnimationManager++%modele.getJoueur().getAnimations().size());
//		tuiles.getChildren().get(remind).setLayoutX(tuiles.getChildren().get(remind).getLayoutX()+1);
//		System.out.println(tuiles.getLayoutX());
		if(debugMode)
			jeuEnPause = false;
		
		idNiveautest++;
		idNiveautest = idNiveautest%2;
		changerMap(idNiveautest);
		//nettoyerEntites();
	}
	
	public void changerMap(int idNiveau) {
		nettoyerPlateau();
		modele.setIdNiveau(idNiveau);
		affichage.ajouterCarte(modele.getNiveau(idNiveau).getPlateau().get());
		//affichage.ajouterCarte(modele.getNiveau(idNiveau).getPlateau().get());
	}
	
	public void nettoyerPlateau() {
		affichage.nettoyerPane(tuiles);
	}
	
	public void nettoyerEntites() {
		affichage.nettoyerPane(entites);
	}
	
	private void initAnimation() {
		changerMap(0);
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);	
		entites.getChildren().add(modele.getJoueur().getSprite().getView());
		for (int i = 0; i < entites.getChildren().size(); i++) {
			entites.getChildren().get(i).equals(modele.getJoueur().getSprite().getView());
			marqueur = i;
		}
		
		entites.getChildren().get(entites.getChildren().size()-1).toFront();
		entites.getChildren().set(marqueur, modele.getJoueur().getSprite().getView());
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev ->{
					if(stopJeu){
						System.out.println("fini");
						gameLoop.stop();
					}
					else {
						if(temps%10==0) {
							//Animations
							//modele.getJoueur().getSprite().setView(modele.getJoueur().getAnimationManager().nextFrame().getView());
							
							
						}if(!jeuEnPause) {
							if(debugMode && !jeuEnPause)
								jeuEnPause = true;
							modele.getJoueur().seDeplace(keymanager.getMovementInputs(temps),modele);
							//non-scrolling map
//							System.out.print("Joueur :");
//							modele.getJoueur().getCollider().sysout();

							if(scrollingMap) {
								affichage.centerMaptoPosition(modele.getJoueur().getPosition());
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(), new Coordonnee(100,100));
							}
							else {
								affichage.mettreAJourPositionPersonnage(modele.getJoueur(),modele.getJoueur().getPosition());	
								affichage.mettreAJourCarte(modele.getNiveau(modele.getIdNiveau()).getPlateau().get());							
							}
						}
						
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}

	
	
	
	

}

