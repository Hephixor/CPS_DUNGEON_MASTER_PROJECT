package display;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainLauncher extends Application {

	private static Stage primaryStageObj;


	@Override
	public void start(Stage primaryStage) throws Exception {
	
		primaryStageObj = primaryStage;
		
		URL fxml = new File("src/resources/views/DungeonView.fxml").toURI().toURL();
		Parent root = FXMLLoader.load(fxml);  
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Dungeon Master");
		
		File img = new File("src/resources/images/chest.gif");
		primaryStage.getIcons().add(new Image(img.toURI().toString()));
		
		Scene mainScene = new Scene(root,900, 700);
		mainScene.setRoot(root);
		
		primaryStage.setResizable(true);
		primaryStage.setScene(mainScene);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
		
	}


	public static void main(String[] args) {

		launch(args);

	}

	public static Stage getPrimaryStage() {
		return primaryStageObj;
	}
}