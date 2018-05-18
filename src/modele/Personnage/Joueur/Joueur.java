package modele.Personnage.Joueur;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Coordonnee.Coordonnee;
import modele.Personnage.Personnage;

public class Joueur extends Personnage{
	String url;
	private Image img;
	ImageView view;
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	
	public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public Joueur(String nom, int pv, Coordonnee position, int vitesse) {
		super(nom, pv, position, vitesse);
	}

	public void setImage(String path, int scale) {
		this.url = new File(path).toURI().toString();
		this.img = new Image(url);
		double x, y;
		x = this.img.getWidth();
		y = this.img.getHeight();
		System.out.println(x + "" + y);
		this.img = new Image(url,x*scale,y*scale,true,true);
		view = new ImageView(img);
		view.setFitWidth(16*scale);
		view.setFitHeight(16*scale);
		view.setSmooth(true);
	}
	

}
