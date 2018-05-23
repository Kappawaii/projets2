package modele.animation;
import vue.sprite.Sprite;

public class Animation {

	private final Sprite[] sprites;
	
	public Animation(int length) {
		sprites = new Sprite[length];
	}
	
	public void addFrame(Sprite spr) {
		boolean spriteAdded = false;
		for(int i=0;i<sprites.length;i++) {
			if(sprites[i] != null) {
				sprites[i] = spr;
				spriteAdded = true;
			}
		}
		if(!spriteAdded)
			throw new Error("sprite non ajouté : tableau plein");
	}

}
