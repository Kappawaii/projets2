import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test {

	public static void main(String[] args){
		fileReader("maps/level0.tmx");
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
			affichePlateau(plateau);
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
