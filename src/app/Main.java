package app;

import controleur.ControleurZelda;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/VueZelda.fxml"));
			root=loader.load();
			ControleurZelda controller = loader.<ControleurZelda>getController();			
			scale = loader.<ControleurZelda>getController().getScale();
			scene = new Scene(root,12*16*scale,12*16*scale);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.show();
			controller.init();
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
