package vue.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import vue.tileset.Tileset;

public class Sprite {

	ImageView view;
	
	public Sprite(Tileset tileset, int scale, int id) {
		view = new ImageView(tileset.getImage());
		view.setViewport(new Rectangle2D(id%((tileset.getLongueurImg()+1)/17)*17*scale, id/57*17*scale, 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	public Sprite(Tileset tileset, int scale, int x, int y) {
		view = new ImageView(tileset.getImage());
		view.setViewport(new Rectangle2D(x, y, 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	public Sprite getSprite() {
		return this;
	}
	
	public ImageView getView() {
		return view;
	}
	
	public void setView(ImageView other) {
		view = other;
	}
	
}
