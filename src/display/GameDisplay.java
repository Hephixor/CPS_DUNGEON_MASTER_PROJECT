package display;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameDisplay extends Application {
	//UMInitializerThread sp = null;
	Runnable mf = null;
	int[] program = null;

	/**
	 *  Lance la methode start
	 * @param args
	 * init
	 */
	public static void main(String[] args) {
		launch(args);
	}


	/**
	 * Creer la fenetre, les champs de texte, les boutons et les dispose dedans.
	 * @param primaryStage
	 * Fenetre utilisee pour creer l'application
	 */
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Dungeon Master");
		Group root = new Group();
		Scene scene = new Scene(root, 650, 480, Color.BLACK);
		
		//Construction VerticalBox 1

		//			HBox h1 = new HBox();
		//			h1.setAlignment(Pos.TOP_CENTER);
		//			h1.setSpacing(10);
		//
		//			Label l1 = new Label("Renommer les parties: ");
		//			
		//			TextField t = new TextField("/user/test/path");
		//			
		//			RadioButton rb1Src = new RadioButton("Oui");
		//			RadioButton rb2Src = new RadioButton("Non");
		//			final ToggleGroup radioGroupSrc = new ToggleGroup(); 
		//			rb1Src.setToggleGroup(radioGroupSrc);
		//			rb2Src.setToggleGroup(radioGroupSrc);
		//			rb2Src.setSelected(true);
		//
		//			VBox v = new VBox();
		//			v.setFillWidth(true);
		//			v.autosize();
		//			v.setAlignment(Pos.CENTER_LEFT);
		//			v.setSpacing(30);
		//
		//			
		//			Button btnsrc = new Button();
		//			btnsrc.setLayoutX(scene.getWidth()/2);
		//			btnsrc.setLayoutY(20);
		//			btnsrc.setText("Choisir le fichier d'origine");
		//			btnsrc.setOnAction((event) -> {
		//
		//				FileChooser fileChooser = new FileChooser();
		//				File selectedFile = fileChooser.showOpenDialog(null);
		//
		//				if (selectedFile != null) {
		//					t.setText(selectedFile.getAbsolutePath());
		//				}
		//
		//			});
		
		BorderPane border = new BorderPane();
	
		HBox hbox = DisplayTools.addHBox();
		border.setTop(hbox);
		//border.setLeft(DisplayTools.addVBox());
		//DisplayTools.addStackPane(hbox);         // Add stack to HBox in top region

		border.setCenter(DisplayTools.addGridPane());
		border.setRight(DisplayTools.addMenu());

		VBox gameDisplay = new VBox();
		gameDisplay.setFillWidth(true);
		gameDisplay.autosize();
		gameDisplay.setAlignment(Pos.CENTER_LEFT);
		gameDisplay.setSpacing(30);
		
		//Disposition des elements dans la fenêtre
		root.getChildren().add(border);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}


