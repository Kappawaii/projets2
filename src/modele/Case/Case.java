package modele.Case;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import modele.Tileset.Tileset;

public class Case{

	Tileset tileset;
	int id;
	ImageView view;

	public Case(Tileset tileset,int id, int scale) {
		this.tileset = tileset;
		this.id = id-1;
		view = new ImageView(tileset.getImage());
		view.setViewport(new Rectangle2D(id%57*17*scale, id/57*17*scale, 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}

	public ImageView getImageView() {
		return view;
	}
}