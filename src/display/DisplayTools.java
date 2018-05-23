package display;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DisplayTools {

	public static HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: black;");

		File file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/dm.png");
		Image image = new Image(file.toURI().toString());
		ImageView title = new ImageView(image);
		title.setFitHeight(80);
		title.setFitWidth(640);
		hbox.getChildren().addAll(title);

		return hbox;
	}

	public static VBox addVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Text title = new Text("Data");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(title);


		return vbox;
	}

	public static GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));


		// Category in column 2, row 1
		Text category = new Text("Sales:");
		category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(category, 1, 0); 

		// Title in column 3, row 1
		Text chartTitle = new Text("Current Year");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(chartTitle, 2, 0);

		// Subtitle in columns 2-3, row 2
		Text chartSubtitle = new Text("Goods and Services");
		grid.add(chartSubtitle, 1, 1, 2, 1);

		// House icon in column 1, rows 1-2
		File file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/wll.png");
		Image image = new Image(file.toURI().toString());

		ImageView imageHouse = new ImageView(image);
		grid.add(imageHouse, 0, 0, 1, 2); 
		// Left label in column 1 (bottom), row 3
		Text goodsPercent = new Text("Goods\n80%");
		GridPane.setValignment(goodsPercent, VPos.BOTTOM);
		grid.add(goodsPercent, 0, 2); 

		// Chart in columns 2-3, row 3
		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/wll.png");
		image = new Image(file.toURI().toString());
		ImageView imageChart = new ImageView(image);
		grid.add(imageChart, 1, 2, 2, 1); 

		// Right label in column 4 (top), row 3
		Text servicesPercent = new Text("Services\n20%");
		GridPane.setValignment(servicesPercent, VPos.TOP);
		grid.add(servicesPercent, 3, 2);
		grid.setStyle("-fx-background-color: black;");
		return grid;
	}

	public static void addStackPane(HBox hb) {
		//		StackPane stack = new StackPane();
		//		Rectangle helpIcon = new Rectangle(30.0, 25.0);
		//		helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		//				new Stop[]{
		//						new Stop(0,Color.web("#4977A3")),
		//						new Stop(0.5, Color.web("#B0C6DA")),
		//						new Stop(1,Color.web("#9CB6CF")),}));
		//		helpIcon.setStroke(Color.web("#D0E6FA"));
		//		helpIcon.setArcHeight(3.5);
		//		helpIcon.setArcWidth(3.5);
		//
		//		Text helpText = new Text("?");
		//		helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		//		helpText.setFill(Color.WHITE);
		//		helpText.setStroke(Color.web("#7080A0")); 
		//
		//		stack.getChildren().addAll(helpIcon, helpText);
		//		stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
		//		StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"
		//
		//		hb.getChildren().add(stack);            // Add to HBox from Example 1-2
		//		HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
	}

	public static FlowPane addMenu() {
		File file;
		Image image;
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(0, 0, 0, 0));
		flow.setVgap(0);
		flow.setHgap(0);
		flow.setPrefWrapLength(200); // preferred width allows for two columns
		flow.setStyle("-fx-background-color: black;");


		GridPane grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		

		//Life display
		HBox life = new HBox();
		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/heart.gif");
		image = new Image(file.toURI().toString());

		ImageView[] lifes = new ImageView[10];
		for (int i = 0; i < lifes.length; i++) {
			lifes[i] = new ImageView(image);
			life.getChildren().add(lifes[i]);
		}

		grid.add(life, 1, 0);


		//Actions display
		HBox actions = new HBox();
		actions.setSpacing(2);
		
		Button fight = new Button();
		Button inventory = new Button();
		fight.setStyle("-fx-background-color: transparent");
		inventory.setStyle("-fx-background-color: transparent");
		setEffects(fight);
		setEffects(inventory);

		//Figth
		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/axe.gif");
		image = new Image(file.toURI().toString());
		fight.setGraphic(new ImageView(image));
		actions.getChildren().add(fight);
		

		//Inventory
		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/chest.gif");
		image = new Image(file.toURI().toString());
		inventory.setGraphic(new ImageView(image));
		actions.getChildren().add(inventory);

		grid.add(actions, 1, 1);

		//Controls display

		//Top Controls
		HBox ctrlSup = new HBox();
		ctrlSup.setSpacing(2);
		
		Button strafeLeft = new Button();
		Button forward = new Button();
		Button strafeRight = new Button();
		setEffects(strafeLeft);
		setEffects(forward);
		setEffects(strafeRight);
		strafeLeft.setStyle("-fx-background-color: transparent");
		forward.setStyle("-fx-background-color: transparent");
		strafeRight.setStyle("-fx-background-color: transparent");

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/strafeLeft.gif");
		image = new Image(file.toURI().toString());
		strafeLeft.setGraphic(new ImageView(image));

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/forward.gif");
		image = new Image(file.toURI().toString());
		forward.setGraphic(new ImageView(image));

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/strafeRight.gif");
		image = new Image(file.toURI().toString());
		strafeRight.setGraphic(new ImageView(image));


		ctrlSup.getChildren().add(strafeLeft);
		ctrlSup.getChildren().add(forward);
		ctrlSup.getChildren().add(strafeRight);

		grid.add(ctrlSup, 1, 2);

		//Bottom Controls
		HBox ctrlBot = new HBox();
		ctrlBot.setSpacing(2);
		
		Button left = new Button();
		Button back = new Button();
		Button right = new Button();
		setEffects(left);
		setEffects(back);
		setEffects(right);
		left.setStyle("-fx-background-color: transparent");
		back.setStyle("-fx-background-color: transparent");
		right.setStyle("-fx-background-color: transparent");

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/left.gif");
		image = new Image(file.toURI().toString());
		left.setGraphic(new ImageView(image));

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/back.gif");
		image = new Image(file.toURI().toString());
		back.setGraphic(new ImageView(image));

		file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/right.gif");
		image = new Image(file.toURI().toString());
		right.setGraphic(new ImageView(image));

		ctrlBot.getChildren().add(left);
		ctrlBot.getChildren().add(back);
		ctrlBot.getChildren().add(right);

		grid.add(ctrlBot, 1, 3);




		//			// Category in column 2, row 1
		//			Text category = new Text("Sales:");
		//			category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		//			grid.add(category, 1, 0); 

		// Title in column 3, row 1
		//			Text chartTitle = new Text("Current Year");
		//			chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		//			grid.add(chartTitle, 2, 0);

		// Subtitle in columns 2-3, row 2
		//			Text chartSubtitle = new Text("Goods and Services");
		//			grid.add(chartSubtitle, 1, 1, 2, 1);

		// House icon in column 1, rows 1-2
		//			file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/wll.png");
		//		    image = new Image(file.toURI().toString());
		//			imageview = new ImageView(image);
		//			grid.add(imageview, 0, 0, 1, 2); 

		// Left label in column 1 (bottom), row 3
		//			Text goodsPercent = new Text("Goods\n80%");
		//			GridPane.setValignment(goodsPercent, VPos.BOTTOM);
		//			grid.add(goodsPercent, 0, 2); 

		// Chart in columns 2-3, row 3
		//			file = new File("/home/skylab/UPMC/M1S2/CPS/TME7/CPS_DUNGEON_MASTER_PROJECT/data/wll.png");
		//		    image = new Image(file.toURI().toString());
		//			imageview = new ImageView(image);
		//			grid.add(imageview, 1, 2, 2, 1); 

		// Right label in column 4 (top), row 3
		//			Text servicesPercent = new Text("Services\n20%");
		//			GridPane.setValignment(servicesPercent, VPos.TOP);
		//			grid.add(servicesPercent, 3, 2);

		flow.getChildren().add(grid);


		return flow;
	}

	public static void setEffects(Button btn) {
		DropShadow shadow = new DropShadow();
		//Adding the shadow when the mouse cursor is on
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				btn.setEffect(shadow);
				btn.setStyle("-fx-border-color:green;-fx-border-width: 1 1 1 1;-fx-background-color:transparent");
			}
		});
		//Removing the shadow when the mouse cursor is off
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, 
				new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				btn.setEffect(null);
				btn.setStyle("-fx-background-color:transparent");
			}
		});
	}


}



