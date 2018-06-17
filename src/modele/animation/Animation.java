package modele.animation;

import vue.sprite.AnimatedSprite;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Animation {

	protected AnimatedSprite spr;
	private int animIndex;
	private int ligneIndex;
	private int framesBetweenSprites;
	private int counter;
	private int numberOfFrames;
	private int flashcounter;

	public Animation(int framesBetweenSprites, Tileset tileset, int displayScale, int ligne) {
		if (framesBetweenSprites <= 0) {
			throw new IllegalArgumentException("Valeur de framesBetweenSprites <= 0");
		}
		else if (tileset == null) {
			throw new NullPointerException("Valeur de tileset : null");
		}
		else if (displayScale <= 0) {
			throw new IllegalArgumentException("Valeur de displayScale <= 0");
		}
		this.animIndex = 0;
		this.counter = 0;
		this.ligneIndex = ligne;
		this.framesBetweenSprites = framesBetweenSprites;
		numberOfFrames = tileset.getCasesParLigne();
		spr = new AnimatedSprite(tileset,displayScale, tileset.getPxParImage(), (int)tileset.getHauteurImg(), ligne);
		next();
	}

	/**
	 * retourne l'état actuel de l'animation
	 * @return
	 */
	protected int getAnimIndex() {
		return animIndex;
	}
	
	/**
	 * anime l'animation à la ligne correspondante
	 * @param i
	 */
	public void animate(int i) {
		if(getCurrentLigne() != i)
			setCurrentAnimation(i);
		else 
			nextFrame();
	}

	private void setCurrentAnimation(int i) throws ArrayIndexOutOfBoundsException {

		counter = 0;
		animIndex = 0;
		ligneIndex = i;
		next();
	}
	
	/**
	 * passe à la prochaine frame de l'animation
	 */
	public void nextFrame() {
		counter++;
		if(counter%framesBetweenSprites==0) {
			next();
		}
	}
	
	/**
	 * itérateur
	 */
	private void next() {
		counter = 0;
		animIndex = (animIndex+1)%numberOfFrames;
		spr.setFrame(animIndex,ligneIndex);
	}

	public Sprite getSprite() {
		return spr;
	}

	public int getCurrentLigne() {
		return ligneIndex;
	}
	
	/**
	 * Définit l'état visible ou non de l'animation
	 * @param b
	 */
	public void setVisible(boolean b) {
		spr.setVisible(b);

	}

	/**
	 * baisse l'opacité du sprite jusqu'à qu'il ne soit plus affiché
	 */
	public void die() {
		spr.fadeDown();
	}
	
	/**
	 * fait clignoter le personnage
	 */
	public void flash() {
		flashcounter = 10;
		spr.getView().setOpacity(0.5);
	}

	public void unflash() {
		if(!(--flashcounter > 0))
			spr.getView().setOpacity(1);
	}
}
