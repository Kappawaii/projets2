package modele.animation;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Animation {

	private Sprite[] sprites;
	private int animIndex;
	private int framesBetweenSprites;
	private int counter;

	public Animation(int framesBetweenSprites, Tileset tileset, int displayScale) {
		if (framesBetweenSprites == 0) {

		}
		else if (tileset == null) {
			throw new NullPointerException("Valeur de tileset : null");
		}
		else if (displayScale == 0) {
			throw new IllegalArgumentException("Valeur de displayScale : 0");
		}
		this.sprites = new Sprite[tileset.getCasesParLigne()];
		this.animIndex = 0;
		this.counter = 0;
		this.framesBetweenSprites = framesBetweenSprites;
		genererAnimation(tileset,displayScale, tileset.getPxParImage(),(int) tileset.getHauteurImg());
	}

	/**
	 * retourne l'état actuel de l'animation
	 * @return
	 */
	protected int getAnimIndex() {
		return animIndex;
	}

	private void genererAnimation(Tileset tileset, int scale, int xLength, int yLength) {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(tileset,4,i,xLength,yLength);
		}
	}
	
	protected void resetAnimation() {
		counter = 0;
		animIndex = 0;
	}

	public Sprite next() {
		if(counter++%framesBetweenSprites==0) {
			counter = 0;
			animIndex = (animIndex+1)%(sprites.length);
		}
		return sprites[animIndex];
	}
	
	protected Sprite[] getAllSprites() {
		return sprites;
	}

	@SuppressWarnings("unused")
	private boolean isFull() {
		for(int i=0;i<sprites.length;i++) {
			if(sprites[i] == null) {
				return false;
			}
		}
		return true;
	}

}
