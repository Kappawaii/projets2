package modele.plateau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import vue.tileset.Tileset;

public class BuilderPlateau {
	
//	public void lireFichier(){
//		ArrayList<Integer> entiers = new ArrayList<Integer>();
//		Scanner scan = null;
//		try {
//			scan = new Scanner(new File("maps/level0.tmx"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		while (scan.hasNextInt()){
//			 entiers.add(scan.nextInt());
//		}
//		for(int i=0;i<entiers.size();i++) {
//			System.out.println(entiers.get(i));
//		}
//	}

	private int notreMap[][] = fileReader("maps/level0.tmx");

	public int[][] getPlateauInt() {
		return this.notreMap;
	}
	
	public void remplirPlateau(Plateau plateau, Tileset tileset, int scale) {
		plateau.initCellules(notreMap.length, notreMap[0].length);
		for (int x = 0; x < plateau.getPlateau().length; x++) {
			for (int y = 0; y < plateau.getPlateau()[x].length; y++) {
				boolean walkable = notreMap[x][y] == 348;
				plateau.getPlateau()[x][y] = new Cellule(tileset, notreMap[x][y]-1, scale,x*16,y*16, walkable);
			}
		}
	}
	
	private static int[][] fileReader(String url){
		int[][] plateau = new int[12][12];
		FileInputStream fis = null;
		String[] sampleString = new String[12];
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
			int i = 0;
			if(url.contains(".tmx")) {
				//System.out.println("Fichier TMX détecté !");
				while(!Character.isDigit(ligne.charAt(0))) {
					try {
						ligne = br.readLine();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
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
		return plateau;
	}


	public static int[] stringTableCaster(String[] tab) {
		int[] res = new int[tab.length];
		for(int i = 0; i < tab.length; i++) {
			res[i] = Integer.parseInt(tab[i]);
		}
		return res;
	}

	public static void affichePlateau(int[][] tab) {
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
}
