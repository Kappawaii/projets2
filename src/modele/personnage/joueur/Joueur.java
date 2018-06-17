
package modele.personnage.joueur;

import java.util.ArrayList;

import controleur.Input;
import modele.Modele;
import modele.Objet;
import modele.animation.Animation;
import modele.arme.Arme;
import modele.arme.LanceurProjectile;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Joueur extends Personnage{

	private ArrayList<Objet> inventaire;
	private ArrayList<Input> inputs;
	private boolean isControllable;
	
	private LanceurProjectile arc;
	private Arme epee;

	//true = arc, false = epee
	private boolean switchArme;
	private boolean hasSwitched = false;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Animation a, Modele modele, ArrayList<Input> inputs) {
		super(pv, position, 16, vitesse,a, modele);
		this.inventaire = new ArrayList<>(); 
		this.inputs = inputs;
		isControllable = false;
		epee = new Arme(modele, 20, this);
	}

	@Override
	public void jouer() {
//		System.out.println(switchArme);
		if(isActive && isControllable && inputs != null) {
			if(inputs.contains(Input.UTILISER)) {
				if(!hasSwitched) {
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

	public Arme currentArme() {
		if(switchArme)
			return arc;
		else
			return epee;
	}

	public void obtenirArc() {
		if(arc == null)
			arc = new LanceurProjectile(modele, 10, this);		
	}

	public void obtenirEpee() {
		System.out.println("epee");
		if(epee == null)
			epee = new Arme(modele, 25, this);
		
	}
	
	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}

	public boolean isControllable() {
		return isControllable;
	}

	public void setControllable(boolean isControllable) {
		this.isControllable = isControllable;
	}


}