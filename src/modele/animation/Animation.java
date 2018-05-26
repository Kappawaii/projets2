package modele.animation;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public class Animation {

	private final Sprite[] sprites;
	
	// à -2 quand l'animation n'est pas démarrée, sinon est l'index de l'animation
	private int animIndex;
	
	public int getAnimIndex() {
		return animIndex;
	}

	public Animation(int length) {
		sprites = new Sprite[length];
		animIndex = -1;
	}
	
	private void addFrame(Sprite spr, int index) {
				sprites[index] = spr;
	}
	
	public Sprite nextSprite() {
		animIndex = (animIndex+1)%sprites.length;
		
		return sprites[animIndex];
	}
	
	public void resetAnimation() {
		animIndex = 0;
	}
	
	public void stopAnimation() {
		animIndex = -1;
	}
	
	public int size() {
		return sprites.length;
	}
	
	public void startAnimation() {
		if(!isFull())
			throw new Error("Ne peut pas démarrer l'animation : animation non pleine");
		animIndex = -1;
	}
	
	public boolean isFull() {
		for(int i=0;i<sprites.length;i++) {
			if(sprites[i] == null) {
				return true;
			}
		}
		return false;
	}

	public void genererAnimation(Tileset tileset, int scale) {
		for (int i = 0; i < sprites.length; i++) {
			addFrame(new Sprite(tileset, scale, i),i);
		}
		
	}
}
