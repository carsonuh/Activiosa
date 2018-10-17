package fitnessApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

	Stage window;

	//Buttons
	private Button logActivity;
	private Button newWeight;
	private Button viewData;



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		window  = primaryStage;

		BorderPane mainLayout = new BorderPane();

		// left: controls and buttons
		VBox controlsLayout = new VBox(20);
		logActivity = new Button("Log Activity");
		newWeight = new Button("New Weight");
		viewData = new Button("View Data");
		controlsLayout.getChildren().addAll(logActivity, newWeight, viewData);
		controlsLayout.setStyle("-fx-background-color: #dfe6e9; -fx-padding: 5px");
		logActivity.setStyle("-fx-min-width: 115px; -fx-padding:4px 8px; -fx-background-color: white; " +
				"-fx-border-color: gray; -fx-border-radius: 5px; -fx-font-size: 13px");
		newWeight.setStyle("-fx-min-width: 115px; -fx-padding:4px 8px; -fx-background-color: white; " +
				"-fx-border-color: gray; -fx-border-radius: 5px; -fx-font-size: 13px");
		viewData.setStyle("-fx-min-width: 115px; -fx-padding:4px 8px; -fx-background-color: white; " +
				"-fx-border-color: gray; -fx-border-radius: 5px; -fx-font-size: 13px");
		// center: main content
		VBox centerView = new VBox(10);

		Label title = new Label("Enter Activity");
		title.setFont(new Font("Arial", 25));
		centerView.getChildren().addAll(title);
		centerView.setStyle("-fx-border-color: #e1e1e1; -fx-border-width: 1px; -fx-background-color: white; " +
				"-fx-padding:5px 10px");

		mainLayout.setLeft(controlsLayout);
		mainLayout.setCenter(centerView);

		Scene scene = new Scene(mainLayout, 1000, 600);


		window.setScene(scene);
		window.setTitle("Fitness App");
		window.show();
	}

}
