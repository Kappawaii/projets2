package vue.sprite;

import javafx.geometry.Rectangle2D;
import vue.tileset.Tileset;

public class AnimatedSprite extends Sprite {
	
	private int xLength;
	private int yLength;

	public AnimatedSprite(Tileset tileset, int scale, int xlength, int ylength, int ligne) {
		super(tileset, scale, xlength, ylength, ligne);
		xLength = scale*xlength;
		yLength = ylength;
	}
	
	public void setFrame(int animIndex, int ligne) {
		setViewPort(
				new Rectangle2D(
						xLength*animIndex,
						yLength*ligne,
						super.getView().getViewport().getWidth(),
						yLength));
	}


}
