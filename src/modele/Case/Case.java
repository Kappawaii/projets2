package modele.Case;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import modele.Coordonnee.Coordonnee;
import modele.Tileset.Tileset;

public class Case{

	Tileset tileset;
	int id;
	ImageView view;
	Coordonnee pos;
	int scale;
	
	public Case(Tileset tileset,int id, int scale) {
		this.tileset = tileset;
		this.scale = scale;
		this.id = id-1;
		view = new ImageView(tileset.getImage());
		pos = new Coordonnee(id%57*17*scale, id/57*17*scale);
		view.setViewport(new Rectangle2D(pos.getX(), pos.getY(), 16*scale, 16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	public Coordonnee getPosition() {
		return pos;
	}
	
	public boolean positionDansLaCase(Coordonnee c) {
		if (c.getX() < pos.getX() || c.getX() <  pos.getX())
			return false;
		if (c.getX() > (pos.getX()+16*scale) || c.getY() > (pos.getY()+16*scale))
			return false;
		return true;
	}

	public ImageView getImageView() {
		return view;
	}
}