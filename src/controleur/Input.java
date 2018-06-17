package controleur;

import java.util.Random;

public enum Input{

	HAUT(0,-1,true,true,false),
	BAS(0,1,true,true,false),
	GAUCHE(-1,0,true,true,false),
	DROITE(1,0,true,true,false),
	ENTREE(0,0,false,false,false),
	UTILISER(0,0,false,false,false),
	AHAUT(0,-1,false,false,true),
	ABAS(0,1,false,false,true),
	AGAUCHE(-1,0,false,false,true),
	ADROITE(1,0,false,false,true),
	EMPTY(0,0,true,true,false);

	private int x; 
	private int y;
	private final boolean isAddable;
	private final boolean isMovement;
	private final boolean isAttack;
	public final static Random rand = new Random();

	private Input(int x,int y, boolean movement, boolean addable, boolean isAttack) {
		if(x > 1 || x < -1)
			throw new Error("x value out of bounds");
		if(y > 1 || y < -1)
			throw new Error("y value out of bounds");
		this.x = x;
		this.y = y;
		this.isMovement = movement;
		this.isAddable = movement;
		this.isAttack = isAttack;
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
		if (this.isAddable && other.isAddable) {
			this.x = this.x+other.x;
			this.y = this.y+other.y;
		}
	}

	public boolean isMovement() {
		return isMovement;
	}

	public boolean isAttack() {
		return isAttack;
	}

	public String toString() {
		return this.name() + " " + x + ";" + y;
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

	public static int directionToInt(Input in) {
		if(in != null) {
			switch (in) {
			case DROITE:
				return 0;
			case BAS:
				return 1;
			case GAUCHE:
				return 2;
			case HAUT:
				return 3;
			default:
				throw new Error("pas normal");

			}
		}
		return 0;
	}
}