
package modele.personnage.joueur;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.animation.Animation;
import modele.arme.Arme;
import modele.arme.LanceurProjectile;
import modele.coordonnee.Coordonnee;
import modele.objet.Objet;
import modele.personnage.Personnage;
import vue.sprite.Sprite;

public class Joueur extends Personnage{

	private ArrayList<Input> inputs;
	private boolean isControllable;

	private ArrayList<Arme> armes;
	
	//inventaire
	private ArrayList<Objet> inventaire;

	//true = arc, false = epee
	private boolean switchArme;
	private boolean hasSwitched = false;
	private Sprite sprCoeurs3;
	private Sprite sprCoeurs2;
	private Sprite sprCoeurs1;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Animation a, Modele modele, ArrayList<Input> inputs, Sprite[] coeurs) {
		super(pv, position, 16, vitesse,a, modele);
		sprCoeurs2 = coeurs[0];
		sprCoeurs1 = coeurs[1];
		System.out.println(coeurs[1]);
		sprCoeurs3 = coeurs[2];
		armes = new ArrayList<Arme>(); 
		inventaire = new ArrayList<Objet>();
		sprCoeurs2 = coeurs[2];
		this.inputs = inputs;
		isControllable = false;
		armes.add(new Arme(modele, 20, this));
		super.collider.setParent(this);
	}

	public void gererVie() {
		switch(pv) {
		case 0:
			
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(4);
			sprCoeurs1.setId(4);
			break;
		case 1:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(4);
			sprCoeurs1.setId(3);
			break;
		case 2:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(4);
			sprCoeurs1.setId(2);
			break;
		case 3:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(4);
			sprCoeurs1.setId(1);
			break;
		case 4:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(4);
			sprCoeurs1.setId(0);
			break;
		case 5:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(3);
			sprCoeurs1.setId(0);
			break;
		case 6:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(2);
			sprCoeurs1.setId(0);
			break;
		case 7: 
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(1);
			sprCoeurs1.setId(0);
			break;
		case 8:  
			sprCoeurs3.setId(4);
			sprCoeurs2.setId(0);
			sprCoeurs1.setId(0);
			break;
		case 9:  
			sprCoeurs3.setId(3);
			sprCoeurs2.setId(0);
			sprCoeurs1.setId(0);
			break;
		case 10: 
			sprCoeurs3.setId(2);
			sprCoeurs2.setId(0);
			sprCoeurs1.setId(0);
			break;
		case 11: 
			sprCoeurs3.setId(1);
			sprCoeurs2.setId(0);
			sprCoeurs1.setId(0);
			break;
		default:
			System.out.println("Problème gestion de vie");;
			break;
		}
	}
	
	@Override
	public void jouer() {
		if(isActive && isControllable && inputs != null) {
			if(inputs.contains(Input.UTILISER)) {
				if(!hasSwitched &&armes.size() > 1) {
					hasSwitched = true;
					switchArme = !switchArme;
				}
			}
			else
				hasSwitched = false;

			int[] movInputs = getMovements(inputs, vitesse);

			if(currentArme() != null) {
				super.attaquer(inputs,currentArme());
			}
			//on passe le déplacement si il n'y a pas de mouvement
			if (movInputs[0] != 0 || movInputs[1] != 0) {
				//calcul de la prochaine position
				int[] nextPosXetY = getNextPos(movInputs[0], movInputs[1]);
				moveAndAnimate(movInputs[0], movInputs[1], nextPosXetY[0], nextPosXetY[1]);
			}
			else if(didAttack) {
				updateAnimation();
			}
		}
	}

	@Override
	public void receiveDamage(int dmg, long id) {
		super.receiveDamage(dmg, id);
		gererVie();
	}

	public Arme currentArme() {
		if(switchArme)
			return armes.get(1);
		else
			return armes.get(0);
	}

	public boolean isControllable() {
		return isControllable;
	}

	public void setControllable(boolean isControllable) {
		this.isControllable = isControllable;
	}

	public void obtenirArc() {
		if(armes.size() < 2)
			armes.add(new LanceurProjectile(modele, 10, this));		
	}
	
	public void obtenirObjet(Objet obj) {
		if(!inventaire.contains(obj))
			inventaire.add(obj);
	}

	public ArrayList<Objet> getInventaire() {
		return inventaire;
	}

}