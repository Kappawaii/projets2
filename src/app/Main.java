package app;

import controleur.Controleur;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


	@FXML
	BorderPane root;
	public static Scene scene;
	private int scale;
	@Override
	public void start(Stage primaryStage) {	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/VueJeu.fxml"));
			root=loader.load();
			Controleur controller = loader.<Controleur>getController();			
			scale = loader.<Controleur>getController().getScale();
			scene = new Scene(root,12*16*scale,12*16*scale);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.show();
			controller.initInputs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Scene getScene()  {
		return scene;

	}
	

}
