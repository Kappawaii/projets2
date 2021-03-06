package vue.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import vue.tileset.Tileset;

public class Sprite {

	protected ImageView view;
	private int id;
	private Tileset tileset;
	private int scale;
	
	/**
	 * sprite statique pour les tuiles
	 * @param tileset
	 * @param scale
	 * @param id
	 */
	public Sprite(Tileset tileset, int scale, int id) {
		this.id = id;
		this.tileset = tileset;
		this.scale = scale;
		view = new ImageView(tileset.getImage());
		setViewPort(new Rectangle2D(
				id%((tileset.getLongueurImg()+1)/17)*17*scale,
				id/(tileset.getCasesParLigne())*17*scale,
				16*scale,
				16*scale));
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	
	public void setId(int i) {
		this.id = i;
		setViewPort(new Rectangle2D(
				id%((tileset.getLongueurImg()+1)/17)*17*scale,
				id/(tileset.getCasesParLigne())*17*scale,
				16*scale,
				16*scale));
	}
	
	/**
	 * sprite pour les animations
	 * @param tileset
	 * @param scale
	 * @param xLength
	 * @param yLength
	 * @param ligne
	 */
	public Sprite(Tileset tileset, int scale, int xLength, int yLength, int ligne) {
		view = new ImageView(tileset.getImage());
		setViewPort(new Rectangle2D(
				scale*xLength, 	//minX
				0,		//minY
				(xLength-1)*scale,	//largeur
				yLength*scale));	//hauteur
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}

	/**
	 * utilisé pour les objets invisibles, ou déclencheurs
	 */
	public Sprite() {
		view = new ImageView();
	}

	public ImageView getView() {
		return view;
	}
	
	public void setViewPort(Rectangle2D rect) {
		view.setViewport(rect);
	}
	
	public void setVisible(boolean b) {
		getView().setVisible(b);
	}
}
