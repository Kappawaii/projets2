package modele.Coordonnee;

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
}


