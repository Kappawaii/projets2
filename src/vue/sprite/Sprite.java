package vue.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import vue.tileset.Tileset;

public class Sprite {

	ImageView view;
	
	public Sprite(Tileset tileset, int scale, int id, int x, int y) {
		view = new ImageView(tileset.getImage());
		view.setViewport(new Rectangle2D(x, y, 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
//	public Sprite(ImageView oldview, int scale) {
//		view = new ImageView(oldview.getImage());
//		view.setViewport(new Rectangle2D(oldview.getLayoutX(),oldview.getLayoutY(),16*scale, 16*scale));
//		view.setFitWidth(16*scale);
//		view.setFitHeight(16*scale);
//		view.setSmooth(true);
//	}
	
//	public Sprite(String path, int scale, int x, int y) {
//		view = new ImageView(path);
//		view.setFitWidth(16*scale);
//		view.setFitHeight(16*scale);
//		view.setViewport(new Rectangle2D(x, y, 16, 16));
//		view.setSmooth(true);
//	}
	
	public Sprite getSprite() {
		return this;
	}
	
	public ImageView getView() {
		return view;
	}
	
}
