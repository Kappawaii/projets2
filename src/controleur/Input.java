package controleur;

import java.util.Random;

public enum Input{

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
	
	private Input(int x,int y, boolean movement, boolean addable) {
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
	public void add (Input other) {
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
	
	public static Input arrayToDirection(int[] tab) {
		if(tab[0] == 0) {
			if(tab[1] == -1)
				return Input.HAUT;
			if(tab[1] == 1)
				return Input.BAS;
		}
		if(tab[1] == 0) {
			if(tab[0] == 1)
				return Input.DROITE;
			if(tab[0] == -1)
				return Input.GAUCHE;			
		}
		return null;
	}
	
	public static Input randomMovement() {
		int val = 0;
		val = rand.nextInt(4);
		switch (val) {
		case 0:
			return Input.GAUCHE;
		case 1:
			return Input.HAUT;
		case 2:
			return Input.DROITE;
		case 3:
			return Input.BAS;
		}
		throw new Error("Something very weird happened here");
	}
}


