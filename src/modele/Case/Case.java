package modele.Case;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import modele.Tileset.Tileset;

public class Case {

	Tileset tileset;
	int id;

	public Case(Tileset tileset,int id) {
		this.tileset = tileset;
		this.id = id-1;
		ImageView temp = new ImageView(img);
		temp.setViewport(new Rectangle2D(id%57*17*scale, id/57*17*scale, 16*scale, 16*scale));
		temp.setFitWidth(16*scale);
		temp.setFitHeight(16*scale);
		temp.setSmooth(true);
	}
	
	
}