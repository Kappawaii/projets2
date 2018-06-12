package modele.coordonnee;


public class Coordonnee {	

	private int x;
	private int y;
	
	public Coordonnee (int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Coordonnee() {
		this.x = 0;
		this.y = 0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return x + ";" + y + ";";
	}
}
