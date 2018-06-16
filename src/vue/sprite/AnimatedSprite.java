package vue.sprite;

import javafx.geometry.Rectangle2D;
import vue.tileset.Tileset;

public class AnimatedSprite extends Sprite {
	
	private int xLength;
	private int yLength;
	
	public AnimatedSprite(Tileset tileset, int scale, int xlength, int ylength, int ligne) {
		super(tileset, scale, xlength, ylength/2, ligne);
		xLength = scale*xlength;
		yLength = ylength/2;
	}
	
	public void setFrame(int animIndex, int ligne) {
		setViewPort(
				new Rectangle2D(
						xLength*animIndex,
						yLength*ligne,
						super.getView().getViewport().getWidth(),
						yLength));
	}

	public void fadeDown() {
		if(view.getOpacity() >= 0.1) {
			view.setOpacity(view.getOpacity()-0.1);
		}
	}

}