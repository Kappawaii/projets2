package vue.tileset;

import java.io.File;
import javafx.scene.image.Image;

public class Tileset {
	
	String url;
	Image img;
	double longueurImg;
	double hauteurImg;
	int casesParLigne;
	int pxParImage;
	
	public int getPxParImage() {
		return pxParImage;
	}

	public Tileset(String path, int scale) {
		this.url = new File(path).toURI().toString();	
		double[] tempDimensions = getImageDimensions(this.url);
		this.img = new Image(url,tempDimensions[0]*scale,tempDimensions[1]*scale,true,true);
		longueurImg = tempDimensions[0];
		hauteurImg = tempDimensions[1];
		casesParLigne =(int) tempDimensions[0]/17 + 1;	
		//TODO risque d'erreur sur de très grands tilesets (+ de 1000 de large)
		pxParImage = (int) Math.floor((longueurImg/casesParLigne)+1);
	}
	
	private double[] getImageDimensions(String path) {
		Image tmp = new Image(url);
		return new double[] {tmp.getWidth(), tmp.getHeight()};
	}
	
	public int getCasesParLigne() {
		return casesParLigne;
	}
	
	public double getLongueurImg() {
		return longueurImg;
	}

	public double getHauteurImg() {
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