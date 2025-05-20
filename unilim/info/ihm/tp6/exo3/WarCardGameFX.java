package unilim.info.ihm.tp6.exo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WarCardGameFX extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/WarCardGameView.fxml"));
		Scene scene = new Scene(loader.load());

		primaryStage.setScene(scene);
		primaryStage.setTitle("WarCardGame");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
