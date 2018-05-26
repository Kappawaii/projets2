package vue.tileset;

import java.io.File;

import javafx.scene.image.Image;

public class Tileset {
	
	String url;
	Image img;
	int longueurImg;
	int hauteurImg;
	
	public Tileset(String path, int scale, int x, int y) {
		this.url = new File(path).toURI().toString();
		System.out.println(x + "" + y);
		this.img = new Image(url,x*scale,y*scale,true,true);
		longueurImg = x;
		hauteurImg = y;
	}
	
	public int getLongueurImg() {
		return longueurImg;
	}

	public int getHauteurImg() {
		return hauteurImg;
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