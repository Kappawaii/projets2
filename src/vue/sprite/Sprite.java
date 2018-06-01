package vue.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import vue.tileset.Tileset;

public class Sprite {

	ImageView view;
	
	//sprite pour les tuiles
	public Sprite(Tileset tileset, int scale, int id) {
		view = new ImageView(tileset.getImage());
		view.setViewport(
				new Rectangle2D(id%((tileset.getLongueurImg()+1)/17)*17*scale,
								id/(tileset.getCasesParLigne())*17*scale,
								16*scale,
								16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	//sprite pour les animations
	public Sprite(Tileset tileset, int scale, int id, int xlength, int yLength) {
		view = new ImageView(tileset.getImage());
		view.setViewport(
				new Rectangle2D(id*scale*xlength,
								id/(tileset.getCasesParLigne())*scale,
								xlength*scale,
								yLength*scale));
		//view.setViewport(new Rectangle2D(id%((tileset.getImage().getWidth()+1)/17)*17, id/57*17*scale, 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	public ImageView getView() {
		return view;
	}
	
	public void setView(ImageView other) {
		view = other;
	}
	
}
