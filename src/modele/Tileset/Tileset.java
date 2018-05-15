package modele.Tileset;

import javafx.scene.image.Image;

public class Tileset {
	
	String url;
	Image img;
	public Tileset(String url, int scale) {
		this.img = new Image(url);
		double x, y;
		x = this.img.getWidth();
		y = this.img.getHeight();
		this.img = new Image(url,x*scale,y*scale,true,true);
	}
}