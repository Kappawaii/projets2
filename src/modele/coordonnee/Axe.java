package modele.coordonnee;

import java.util.Random;

public enum Axe{

	HAUT(0,-1,true,true),
	BAS(0,1,true,true),
	GAUCHE(-1,0,true,true),
	DROITE(1,0,true,true),
	ENTREE(0,0,false,false),
	UTILISER(0,0,false,false),
	AHAUT(0,-1,false,false),
	ABAS(0,1,false,false),
	AGAUCHE(1,0,false,false),
	ADROITE(-1,0,false,false),
	EMPTY(0,0,true,true);

	private int x; 
	private int y;
	private final boolean addable;
	private final boolean movement;
	public final static Random rand = new Random();
	
	private Axe(int x,int y, boolean movement, boolean addable) {
		if(x > 1 || x < -1)
			throw new Error("x value out of bounds");
		if(y > 1 || y < -1)
			throw new Error("y value out of bounds");
		this.x = x;
		this.y = y;
		this.movement = movement;
		this.addable = movement;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
	/**
	 * Résultat de l'addition stocké dans le premier vecteur
	 * N'additionne que les vecteurs de mouvement et n'aura aucun effet si movement est false
	 * @param other
	 */
	public void add (Axe other) {
		if (this.addable && other.addable) {
			this.x = this.x+other.x;
			this.y = this.y+other.y;
		}
	}

	public boolean isMovement() {
		return movement;
	}

	public String toString() {
		return this.name() + " " + x + ";" + y;
	}

	/**
	 * à utiliser seulement sur empty
	 */
	public void clear() {
		if (!this.name().equals("EMPTY"))
			throw new Error("Only use on empty");
		this.x = 0;
		this.y = 0;
	}

	public int[] directiontoArray() {
		return new int[] {x,y};
	}
	
	public static Axe arrayToDirection(int[] tab) {
		if(tab[0] == 0) {
			if(tab[1] == -1)
				return Axe.HAUT;
			if(tab[1] == 1)
				return Axe.BAS;
		}
		if(tab[1] == 0) {
			if(tab[0] == 1)
				return Axe.DROITE;
			if(tab[0] == -1)
				return Axe.GAUCHE;			
		}
		return null;
	}
	
	public static Axe getRandomMovement() {
		int val = 0;
		val = rand.nextInt(4);
		switch (val) {
		case 0:
			return Axe.GAUCHE;
		case 1:
			return Axe.HAUT;
		case 2:
			return Axe.DROITE;
		case 3:
			return Axe.BAS;
		}
		throw new Error("Something very weird happened here");
	}
}


