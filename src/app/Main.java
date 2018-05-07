package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@FXML
	BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		try {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../vue/VueZelda.fxml"));
		
		
		root=loader.load();
     	Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
