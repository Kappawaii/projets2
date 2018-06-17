package modele.plateau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import modele.Modele;
import modele.Event.Event;
import modele.Event.GetArcEvent;
import modele.Event.LoadEntitiesEvent;
import modele.Event.LoadLevelEvent;
import modele.Event.SetScrollingMapEvent;
import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import vue.tileset.Tileset;

public class BuilderPlateau {

	private int notreMap[][];
	private int width;
	private int height;

	public void remplirPlateau(Plateau plateau, Tileset tileset, Modele modele, int id) {
		int tailleX;
		int tailleY;
		int offsetX;
		int offsetY;
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Coordonnee> pos = new ArrayList<Coordonnee>();
		boolean isTrigger;
		plateau.initCellules(notreMap.length, notreMap[0].length);
		for (int x = 0; x < plateau.get().length; x++) {
			for (int y = 0; y < plateau.get()[x].length; y++) {
				tailleX = 0;
				tailleY = 0;
				offsetX = 0;
				offsetY = 0;
				isTrigger = true;
				events.clear();
				

				if(notreMap[x][y] == 348) {
					tailleX = 16;
					tailleY = 16;
					isTrigger = false;
				}
				if(notreMap[x][y] == 1246) {
					tailleX = 1;
					tailleY = 1;
					offsetX = 3;
					offsetY = 4;
					isTrigger = false;
				}
				if(notreMap[x][y] == 1244) {
					tailleX = 1;
					tailleY = 1;
					offsetX = 3;
					offsetY = 4;
					isTrigger = false;
				}
				if(notreMap[x][y] == 1245) {
					tailleX = 1;
					tailleY = 1;
					offsetX = 3;
					offsetY = 4;
					isTrigger = false;
				}


				//Bibliothèques
				if(notreMap[x][y] == 1074) {
					tailleX = 8;
					tailleY = 8;
					offsetX = 1;
					offsetY = 3;
					isTrigger = false;
				}
				if(notreMap[x][y] == 1075) {
					tailleX = 8;
					tailleY = 8;
					offsetX = 2;
					offsetY = 3;
					isTrigger = false;
				}


				//Lit
				if(notreMap[x][y] == 131) {
					tailleX = 1;
					tailleY = 1;
					offsetX = 5;
					offsetY = 3;
					isTrigger = false;
				}
				if(notreMap[x][y] == 130) {
					tailleX = 1;
					tailleY = 1;
					offsetX = 3;
					offsetY = 3;
					isTrigger = false;
				}

				//Armoire
				if(notreMap[x][y] == 1188) {
					tailleX = 8;
					tailleY = 8;
					offsetX = 7;
					offsetY = 3;
					isTrigger = false;
				}

				//Mur
				if(notreMap[x][y] == 1186) {
					tailleX = 8;
					tailleY = 4;
					offsetX = 3;
					offsetY = 2;
					isTrigger = false;
				}
				
				//Porte
				if(notreMap[x][y] == 90) {
					tailleX = 4;
					tailleY = 1;
					offsetX = 0;			//Armoire3;
					offsetY = 0;
					isTrigger = false;
					events.add(new LoadLevelEvent(modele, id+1,new Coordonnee(112,160)));
					events.add(new SetScrollingMapEvent(modele));
				}
				//Arc
				if(notreMap[x][y] == 67) {
					tailleX = 4;
					tailleY = 1;
					offsetX = 0;			//Armoire3;
					offsetY = 0;
					isTrigger = true;
					Coordonnee positionSpr = new Coordonnee(x*16,y*16);
//					pos.add(positionSpr);
					events.add(new GetArcEvent(modele,positionSpr));
				}
				
				//Déclencheur entités Niveau 1
				if(notreMap[x][y] == 63) {
					tailleX = 16;
					tailleY = 16;
					offsetX = 0;
					offsetY = 0;
					isTrigger = true;
					pos.add(new Coordonnee(300,150));
					pos.add(new Coordonnee(150,150));
					events.add(new LoadEntitiesEvent(modele,modele.getPersonnagesACharger(1),pos));
				}
				
				plateau.get()[x][y] = 
						new Cellule(tileset,
								notreMap[x][y]-1,
								x*16,	 y*16,
								isTrigger,
								4,
								offsetX, offsetY, 
								tailleX, tailleY, 
								events);
			}
		}
	}

	public void fileReader(String url){




		FileInputStream fis = null;

		String ligne = "";
		try {
			fis = new FileInputStream(new File(url));
		}
		catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		try {
			ligne = br.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		try {
			ligne=br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ligne=ligne.substring(ligne.indexOf("width"), ligne.indexOf(" tilewidth"));
		String[]taille=ligne.split("\"");
		this.width=Integer.parseInt(taille[1]);
		this.height=Integer.parseInt(taille[3]);
		int[][] plateau = new int[this.width][this.height];
		String[] sampleString = new String[this.width];


		try {
			int i = 0;
			if(url.contains(".tmx")) {

			}
			//System.out.println("Fichier TMX détecté !");
			while(!Character.isDigit(ligne.charAt(0))) {
				try {
					ligne = br.readLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			//System.out.println("ligne " + ligne );
			while (ligne  != null && Character.isDigit(ligne.charAt(0))) {
				//System.out.println(ligne);
				//System.out.println("ligne ++  " + ligne );
				sampleString = ligne.split(",");
				plateau[i] = stringTableCaster(sampleString);
				i++;
				ligne= br.readLine(); 
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fis != null) {
				try {
					fis.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		int[][] returntableau = new int[this.width][this.height];
		for (int i = 0; i < returntableau.length; i++) {
			for (int j = 0; j < returntableau[i].length; j++) {
				//System.out.println(returntableau[i].length);
				//System.out.println(plateau[i].length);
				returntableau[i][j] = plateau[j][i];
			}
		}
		notreMap =  returntableau;
	}


	public int[] stringTableCaster(String[] tab) {
		int[] res = new int[tab.length];
		for(int i = 0; i < tab.length; i++) {
			res[i] = Integer.parseInt(tab[i]);
		}
		return res;
	}

	public void affichePlateau(int[][] tab) {
		for(int i = 0; i < tab.length; i++) {
			System.out.print("{");
			for(int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j]);
				if(j < tab.length-1) {
					System.out.print(",");
				}
			}
			System.out.println("},");
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
