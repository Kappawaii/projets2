package modele.Plateau;

import javafx.scene.image.ImageView;

public class PlateauInt {

	private int notreMap[][] = {
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 1186, 90, 1074, 1075, 1188, 1186, 348, 348, 348},
							{348, 348, 348, 1071, 1243, 1131, 1132, 1189, 1127, 348, 348, 348},
							{348, 348, 348, 1125, 1126, 1126, 1246, 1244, 1245, 348, 348, 348},
							{348, 348, 348, 130, 131, 1126, 1126, 1126, 1127, 348, 348, 348},
							{348, 348, 348, 1182, 1183, 1183, 1183, 1183, 1184, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348},
							{348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348, 348}
						};
	
	public int[][] getPlateauInt() {
		return this.notreMap;
	}
}
