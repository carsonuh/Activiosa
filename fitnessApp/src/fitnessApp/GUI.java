package fitnessApp;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

	Stage window;
	private Scene scene;

	//Layouts
	private VBox controlsLayout, homeLayout, bmiLayout;
	protected VBox dataLayout;
	private VBox mealsLayout;
	private VBox weightLayout;
	private VBox activityLayout;
	private BorderPane mainLayout;

	//Buttons
	private Button activityButton;
	private Button weightButton;
	private Button dataButton;
	private Button mealsButton;
	private Button bmiButton;
	private Button bmiSubmit;

	//Labels
	private Label bmiTitle;
	private Label weightTitle;
	private Label activityTitle;
	private Label mealsTitle;
	protected Label dataTitle;
	private Label homeTitle;
	
	public static void main(String[] args) throws NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		
	launch(args);
	db DB = new db();
	DB.connect();
	
	//DB.insertUsers("user1", "begin");
	DB.updateUsers(1, "Carson Uecker-Herman", "Cookie28");
	
	DB.shutdown();
	
	}

	public void start(Stage primaryStage) throws Exception {
		window  = primaryStage;
		

		mainLayout = new BorderPane();

		// left: controls and buttons
		controlsLayout = new VBox(20);
		activityButton = new Button();
		activityButton.setId("activity");
		activityButton.setTooltip(new Tooltip("Activities"));
		weightButton = new Button();
		weightButton.setId("weight");
		weightButton.setTooltip(new Tooltip("Weight"));
		mealsButton = new Button();
		mealsButton.setId("meals");
		mealsButton.setTooltip(new Tooltip("Meals"));
		dataButton = new Button();
		dataButton.setId("activitylog");
		dataButton.setTooltip(new Tooltip("Activity Log"));
		bmiButton = new Button("BMI");
		controlsLayout.getChildren().addAll(activityButton, weightButton, mealsButton, dataButton);


		
		//Data Layout
		 dataLayout = new VBox(10);
		 dataTitle = new Label("View Data");
		 dataLayout.getChildren().addAll(dataTitle);
		 

		//Activity Layout
		activityLayout = new VBox(10);
		activityTitle = new Label("Activities");
		activityLayout.getChildren().addAll(activityTitle);

		//BMI Layout
		bmiLayout = new VBox(10);
		bmiTitle = new Label("Calculate BMI");
		Label currentWeight = new Label("Weight");
		TextField bmiText = new TextField();
		Label heightLbl = new Label("Height");
		TextField heightFeetText = new TextField();
		TextField heightInchText = new TextField();
		heightInchText.setId("inch");
		heightFeetText.setPromptText("Feet");
		heightInchText.setPromptText("Inches");
		bmiText.setPromptText("Lbs");
		currentWeight.setLabelFor(bmiText);
		bmiSubmit = new Button("Submit");
		bmiSubmit.setId("submit");
		bmiLayout.getChildren().addAll(bmiTitle, currentWeight, bmiText, heightLbl,
				heightFeetText, heightInchText, bmiSubmit);


		//Meals Layout
		mealsLayout = new VBox(10);
		mealsTitle = new Label("Meals");
		mealsLayout.getChildren().addAll(mealsTitle);

		//Weight Layout
		weightLayout = new VBox(10);
		weightTitle = new Label("Weight");
		weightLayout.getChildren().addAll(weightTitle);




		//Home Page
		homeLayout = new VBox(10);
		homeTitle = new Label("Welcome");
		homeTitle.setFont(new Font("Arial", 25));
		homeLayout.getChildren().addAll(homeTitle);



		mainLayout.setLeft(controlsLayout);
		mainLayout.setCenter(homeLayout);

		scene = new Scene(mainLayout, 1200, 600);


		window.setScene(scene);
		window.setTitle("Fitness App");
		window.show();

		associateStyles();
		actions();
	}

		public void associateStyles() {
			//link stylesheet
			scene.getStylesheets().add("main.css");

			//layouts
			bmiLayout.getStyleClass().add("centralView");
			homeLayout.getStyleClass().add("centralView");
			dataLayout.getStyleClass().add("centralView");
			mealsLayout.getStyleClass().add("centralView");
			weightLayout.getStyleClass().add("centralView");
			activityLayout.getStyleClass().add("centralView");
			controlsLayout.getStyleClass().add("controlsNav");

			//titles
			bmiTitle.getStyleClass().add("title");
			weightTitle.getStyleClass().add("title");
			activityTitle.getStyleClass().add("title");
			mealsTitle.getStyleClass().add("title");
			dataTitle.getStyleClass().add("title");
			homeTitle.getStyleClass().add("title");
		}

		public void actions() {
			dataButton.setOnAction(e -> mainLayout.setCenter(dataLayout));
			bmiButton.setOnAction(e -> mainLayout.setCenter(bmiLayout));
			mealsButton.setOnAction(e -> mainLayout.setCenter(mealsLayout));
			weightButton.setOnAction(e -> mainLayout.setCenter(weightLayout));
			activityButton.setOnAction(e -> mainLayout.setCenter(activityLayout));

		}
	}

