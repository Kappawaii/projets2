package modele.Tileset;

import java.io.File;

import javafx.scene.image.Image;

public class Tileset {
	
	String url;
	Image img;
	
	public Tileset(String path, int scale, int x, int y) {
		this.url = new File(path).toURI().toString();
//		this.img = new Image(url);
//		double x, y;
//		x = this.img.getWidth();
//		y = this.img.getHeight();
		System.out.println(x + "" + y);
		this.img = new Image(url,x*scale,y*scale,true,true);
	}
	
	/***
	 * Retourne l'url de l'image ressource du tileset
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	public Image getImage() {
		return img;
	}
}