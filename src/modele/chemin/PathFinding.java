package modele.chemin;

import java.util.ArrayList;

import modele.Modele;
import modele.cellule.Cellule;
import modele.plateau.Plateau;

public class PathFinding {

	Plateau map;
	Node depart;
	//	Node arrive;
	Cellule arrive;
	ArrayList <Node> aVisiter;
	ArrayList <Node> dejaVisiter;
	ArrayList<Cellule> chemin;
	ArrayList<Cellule> path; //chemin donné dans l'ordre
	int[] coordDepart;
	int[] coordArrive;
	int coordArriveX;
	int coordArriveY;
	int currentX;
	int currentY;
	Node current;

	public PathFinding(Modele modele, Cellule depart, Cellule arrive) {

		if (depart.getCollider().isTrigger() && arrive.getCollider().isTrigger()) {
			this.map = modele.getCurrentNiveau().getPlateau();
			this.coordDepart = map.getCellulePositionInPlateau(depart);
			this.depart = new Node(null, map.getCellule(coordDepart[0], coordDepart[1]));
			this.coordArrive = map.getCellulePositionInPlateau(arrive);
			this.coordArriveX = coordArrive[0];
			this.coordArriveY = coordArrive[1];
			this.aVisiter = new ArrayList<>();
			this.chemin  = new ArrayList<>();
			this.dejaVisiter = new ArrayList<>();
			this.path = new ArrayList<Cellule>();
		}
	}

	public ArrayList<Cellule> chemin() {
		
		if(coordDepart != null && coordDepart.equals(coordArrive)) {
			return null;
		}
		
		aVisiter.add(depart);
		Node victoire = null;
		
		do {
			current = aVisiter.get(0);
			current.setVisite();
			dejaVisiter.add(current);
			aVisiter.remove(0);


			victoire = checkAroundArrive(current);

			if(map.getCellulePositionInPlateau(current.getCord()) != null) {
				currentX = map.getCellulePositionInPlateau(current.getCord())[0];
				currentY = map.getCellulePositionInPlateau(current.getCord())[1];

				Node voisinD = new Node(current, map.getCellule(currentX+1, currentY));              //voisin Droite de Current
				if(voisinD!=null 
						&& voisinD.getCord().getCollider().isTrigger() 
						&& !voisinD.getDejaVisite() && !aVisiterContient(voisinD)){ 
					aVisiter.add(voisinD);
				}

				Node voisinG = new Node(current, map.getCellule(currentX-1, currentY));                 //voisin Gauche de Current
				if(voisinG!=null 
						&& voisinG.getCord().getCollider().isTrigger() 
						&& !voisinG.getDejaVisite() && !aVisiterContient(voisinG)){ 

					aVisiter.add(voisinG);
				}

				Node voisinH = new Node(current, map.getCellule(currentX, currentY-1));                 //voisin Haut de Current
				if(voisinH!=null 
						&& voisinH.getCord().getCollider().isTrigger() 
						&& !voisinH.getDejaVisite() && !aVisiterContient(voisinH)){ 

					aVisiter.add(voisinH);
				}

				Node voisinB = new Node(current, map.getCellule(currentX, currentY+1));                //voisin Bas de Current
				if(voisinD!=null 
						&& voisinB.getCord().getCollider().isTrigger() 
						&& !voisinB.getDejaVisite() && !aVisiterContient(voisinB)){ 

					aVisiter.add(voisinB);
				}
			}


		} while (victoire == null && !aVisiter.isEmpty());

		if(victoire!=null) {
			while(victoire.getAvant()!=null) {
				chemin.add(victoire.getCord());
				victoire = victoire.getAvant();
			}
		}


		//remets l'arraylist dans le bon ordre
		for (int i = 0; i < chemin.size(); i++) {
			int cell = chemin.size()-i-1;
			path.add(chemin.get(cell));
		}


		return path;
	}

	// retourne le node si l'un des voisins est la case d'arrivé
	public Node checkAroundArrive(Node current) {
		if(map.getCellulePositionInPlateau(current.getCord()) != null) {
			int currentX = map.getCellulePositionInPlateau(current.getCord())[0];
			int currentY = map.getCellulePositionInPlateau(current.getCord())[1];

			if (currentX+1 == coordArriveX &&  currentY == coordArriveY) {              //droite
				return new Node(current, map.getCellule(currentX+1,currentY));		  
			}
			if (currentX-1 == coordArriveX &&  currentY == coordArriveY) {			  //gauche
				return new Node(current, map.getCellule(currentX-1,currentY));
			}
			if (currentX == coordArriveX &&  currentY-1 == coordArriveY) {			 //haut
				return new Node(current, map.getCellule(currentX,currentY-1));
			}
			if (currentX == coordArriveX &&  currentY+1 == coordArriveY) {			 //bas
				return new Node(current, map.getCellule(currentX,currentY+1));
			}
		}

		return null;
	}

	public boolean aVisiterContient(Node node) {
		for (int i = 0; i < aVisiter.size(); i++) {
			if(node.getCord().getPosition().getX() == aVisiter.get(i).getCord().getPosition().getX() && 
					node.getCord().getPosition().getY() == aVisiter.get(i).getCord().getPosition().getY()) {
				return true;
			}

		}
		return false;
	}

	public boolean dejaVisiterContient(Node node) {
		for (int i = 0; i < dejaVisiter.size(); i++) {
			if(node.getCord().getPosition().getX() == dejaVisiter.get(i).getCord().getPosition().getX() && 
					node.getCord().getPosition().getY() == dejaVisiter.get(i).getCord().getPosition().getY()) {
				return true;
			}

		}
		return false;
	}


}

