package modele.animation;

import vue.sprite.AnimatedSprite;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Animation {

	private AnimatedSprite spr;
	private int animIndex;
	private int ligneIndex;
	private int framesBetweenSprites;
	private int counter;
	private int numberOfFrames;

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

	public void nextFrame() {
		counter++;
		if(counter%framesBetweenSprites==0) {
			next();
		}
	}
	
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
}
