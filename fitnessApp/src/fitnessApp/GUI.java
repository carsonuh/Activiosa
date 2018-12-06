package fitnessApp;

import java.awt.Dialog;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import java.lang.Object;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
/*****
 * This class creates the GUI that interacts with the database and allows to user to visualize their data
 * @author Carson Uecker-Herman
 * @version 12/5/2018
 *
 */
public class GUI extends Application {
	
	/** Main stage for GUI */
	Stage window;
	
	/** Global Scenes */
	private Scene mainScene, loginScene;

	/** Global VBox layouts */
	protected static VBox activityTopBar, bmiLayout, dataLayout, controlsLayout, activityStepsLayout, activityRunningLayout, activityBikingLayout, activitySwimmingLayout;
	
	/** Series for Line Charts */
	protected static Series<String, Number> seriesSteps, seriesRunning, seriesSwimming, seriesBiking, seriesWeight;
	
	/** Global Line Charts */
	protected static LineChart<String,Number> stepsChart, runningChart, bikingChart, swimmingChart, weightChart;
	
	/** Global HBox Layouts */
	protected static HBox activityMenuLayout;
	
	/** Global BorderPane Layouts */
	protected static BorderPane mainLayout,activityLayout, homeLayout, mealsLayout, weightLayout, accountLayout;
	
	/** Global Buttons */
	private Button activityButton, weightButton, mealsButton, profileButton, runningButton, stepsButton, swimmingButton, bikingButton, homeButton, activityAddButton, weightAddButton, mealsAddButton;
	
	/** Global Labels */
	protected static Label caloriesNum, breakfastCalNum, lunchCalNum, dinnerCalNum, snacksCalNum, stepsNum, calBurnedNum, activeMinsNum, stepsDistNum, stepsFloorsNum ;
	
	/** Modal Windows */
	static Stage activityModal, weightModal, mealsModal;
	
	/** Current Layout Value */
	private static String currentLayout;

	/****
	 * Makes the GUI visible. All of the layouts are created in this method
	 * @param primaryStage the default stage that is used 
	 */
	public void start(Stage primaryStage) throws Exception {
		window  = primaryStage;
		window.getIcons().add(new Image(GUI.class.getResourceAsStream("../images/icon.png")));
		activityModal = new activityDialog(primaryStage);	
		weightModal = new weightDialog(primaryStage);
		mealsModal = new mealsDialog(primaryStage);
		
		
		// login --------------------------------------
		BorderPane loginLayout = new BorderPane();
		
		VBox loginPage = new VBox(10);
		Label logo = new Label();
		logo.setId("logo");
		TextField username = new TextField();
		username.getStylesheets().add("login-textfield");
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.getStylesheets().add("login-textfield");
		password.setPromptText("Password");
		Button login = new Button("Login");
		login.setId("login");
		login.getStylesheets().add("login-button");
		Button signup = new Button("signup");
		signup.getStylesheets().add("login-button");
		
		loginPage.getChildren().addAll(username, password, login, signup);
		
		//sign up ---------------------------------------
		GridPane signupLayout = new GridPane();
		
		TextField fullName = new TextField();
		fullName.setPromptText("Full Name");
		fullName.getStylesheets().add("login-textfield");
		
		TextField usernameNew = new TextField();
		usernameNew.setPromptText("Username");
		usernameNew.getStylesheets().add("login-textfield");
		
		PasswordField passwordNew = new PasswordField();
		passwordNew.setPromptText("Password");
		passwordNew.getStylesheets().add("login-textfield");
		PasswordField passwordNewConfirm = new PasswordField();
		passwordNewConfirm.setPromptText("Confirm Password");
		passwordNewConfirm.getStylesheets().add("login-textfield");
		
		TextField heightInches = new TextField();
		heightInches.setPromptText("Height IN");
		heightInches.getStylesheets().add("login-textfield");
		
		TextField heightFeet = new TextField();
		heightFeet.setPromptText("Height FT");
		heightFeet.getStylesheets().add("login-textfield");
		
		TextField startWeight = new TextField();
		startWeight.setPromptText("Weight");
		startWeight.getStylesheets().add("login-textfield");
		
		Button signUpNew = new Button("Sign Up");
		signUpNew.getStylesheets().add("login-button");
		
		loginLayout.setCenter(loginPage);
		
		login.setOnAction(e -> {
			users.checkIfExists(username.getText());
			
			try {
				if(users.exists) {
					users.getPassword(username.getText());
					if(users.passwordEncrypt(password.getText()) == users.password) {
					users.loginSuccessGet(username.getText());
					data.get();
					data.update();
					window.setScene(mainScene);
					
					}
				
				}
				else {
					
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		
		// Main layout -------------------------------------------------------------------
		mainLayout = new BorderPane();

		// left: controls and buttons
		controlsLayout = new VBox(30);
		controlsLayout.setId("controlNav");
		activityButton = new Button();
		activityButton.setId("activity");
		activityButton.getStyleClass().add("nav-menu-button");
		weightButton = new Button();
		weightButton.setId("weight");
		weightButton.getStyleClass().add("nav-menu-button");
		mealsButton = new Button();
		mealsButton.setId("meals");
		mealsButton.getStyleClass().add("nav-menu-button");
		profileButton = new Button();
		profileButton.setId("profile");
		profileButton.getStyleClass().add("nav-menu-button");
		homeButton = new Button();
		homeButton.setId("home");
		homeButton.getStyleClass().add("nav-menu-button");
		controlsLayout.getChildren().addAll(homeButton, activityButton, weightButton, mealsButton, profileButton);
		
		 
		//Activity Layout ---------------------------------------------------
		activityLayout = new BorderPane();
		
		 //Activity Menu
		 activityMenuLayout = new HBox(20);
		 activityMenuLayout.setId("activitiesMenu");
		 runningButton = new Button("running");
		 runningButton.setId("runningButton");
		 runningButton.getStyleClass().add("activity-menu-buttons");
		 stepsButton = new Button("steps");
		 stepsButton.setId("stepsButton");
		 stepsButton.getStyleClass().add("activity-menu-buttons");
		 bikingButton = new Button("biking");
		 bikingButton.setId("bikingButton");
		 bikingButton.getStyleClass().add("activity-menu-buttons");
		 swimmingButton = new Button("swimming");
		 swimmingButton.setId("swimmingButton");
		 swimmingButton.getStyleClass().add("activity-menu-buttons");
		 activityAddButton = new Button();
		 activityAddButton.getStyleClass().add("add-button");
		 activityAddButton.setAlignment(Pos.TOP_RIGHT);
		 activityMenuLayout.getChildren().addAll(stepsButton, runningButton, bikingButton, swimmingButton);
		 
		 HBox activityAddLayout = new HBox(0);
		 activityAddLayout.setId("activityAdd");
		 activityAddLayout.getChildren().add(activityAddButton);
		 
		 BorderPane activityTopLayout = new BorderPane();
		 Label activitiesLbl = new Label("Activities");
		 activitiesLbl.setId("activitiesLbl");
		 
		 activityTopLayout.setTop(activitiesLbl);
		 activitiesLbl.setAlignment(Pos.CENTER);
		 activityTopLayout.setAlignment(activitiesLbl, Pos.CENTER);
		 activityTopLayout.setCenter(activityMenuLayout);
		 activityTopLayout.setRight(activityAddLayout);
		 activityTopLayout.setId("activityTopLayout");
		 
		 // steps --------------------------------------------------------------
		 activityStepsLayout = new VBox(5);
		 activityStepsLayout.setId("activityStepsLayout");
		 stepsButton.requestFocus();
		 CategoryAxis xAxis = new CategoryAxis();
	        
	      //Defining the y axis   
	      NumberAxis yAxis = new NumberAxis(0, 25, 5); 
	      yAxis.setLabel("Steps (Thousands)"); 
	        
	      //Creating the line chart 
	      stepsChart = new LineChart<String, Number>(xAxis, yAxis);  
	      
	      
	      //Prepare XYChart.Series objects by setting data 
	      seriesSteps = new XYChart.Series(); 
	      seriesSteps.setName(DateMaker.weekRange());
	      
	      stepsTable.getWeek();
	      seriesSteps.getData().add(new XYChart.Data("SUN", stepsPerThousand(stepsTable.weeklySteps.get("SUNDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("MON", stepsPerThousand(stepsTable.weeklySteps.get("MONDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("TUE", stepsPerThousand(stepsTable.weeklySteps.get("TUESDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("WED", stepsPerThousand(stepsTable.weeklySteps.get("WEDNESDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("THU", stepsPerThousand(stepsTable.weeklySteps.get("THURSDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("FRI", stepsPerThousand(stepsTable.weeklySteps.get("FRIDAY")))); 
	      seriesSteps.getData().add(new XYChart.Data("SAT", stepsPerThousand(stepsTable.weeklySteps.get("SATURDAY"))));
	      
	      //Setting the data to Line chart  
	      
	      stepsChart.getData().add(seriesSteps);        
	      stepsChart.setId("stepsChart");
	      stepsChart.getStyleClass().add("activity-chart");
	      stepsChart.setAnimated(false);

	      
	      activityStepsLayout.getChildren().add(stepsChart);
	      
	      if(currentLayout == activityStepsLayout.getId()) {
	    	  stepsButton.getStyleClass().add("sub-menu-active");  
	      }
	      else {
	    	  stepsButton.getStyleClass().add("sub-menu-passive");
	      }
	      
	      stepsButton.setOnAction(e -> {
				 activityLayout.setCenter(activityStepsLayout);
				 currentLayout = activityStepsLayout.getId();
			 });
		 
		 
		 // running --------------------------------------------
		 activityRunningLayout = new VBox(5);
		 activityRunningLayout.setId("activityRunningLayout");
		 CategoryAxis xAxisRunning = new CategoryAxis();
		 xAxisRunning.setLabel(DateMaker.weekRange()); 
	        
	      //Defining the y axis   
	      NumberAxis yAxisRunning = new NumberAxis(0, 10, 2); 
	      yAxisRunning.setLabel("Miles"); 
	        
	      //Creating the line chart 
	      runningChart = new LineChart<String, Number>(xAxisRunning, yAxisRunning);  
	      runningChart.setLegendVisible(false);
	      //Prepare XYChart.Series objects by setting data 
	      seriesRunning = new XYChart.Series(); 
	      activity.getWeek("Running");
	      seriesRunning.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
	      seriesRunning.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
	      
	     
	      runningChart.getData().add(seriesRunning);        
	      runningChart.setId("runningChart");
	      runningChart.getStyleClass().add("activity-chart");
	      runningChart.setAnimated(false);


	      
	      activityRunningLayout.getChildren().add(runningChart);
	      
	      runningButton.setOnAction(e -> {
				 activityLayout.setCenter(activityRunningLayout);
				 currentLayout = activityRunningLayout.getId();
			 });
		 
		 
		 
		 // biking ------------------------------------------------------------
		 activityBikingLayout = new VBox(5);
		 xAxis = new CategoryAxis();
	     xAxis.setLabel(DateMaker.weekRange()); 
	        
	      //Defining the y axis   
	      yAxis = new NumberAxis   (0, 100, 10); 
	      yAxis.setLabel("Miles"); 
	        
	      //Creating the line chart 
	      bikingChart = new LineChart<String, Number>(xAxis, yAxis);  
	      bikingChart.setLegendVisible(false);
	      //Prepare XYChart.Series objects by setting data 
	      seriesBiking = new XYChart.Series(); 
	      
	      activity.getWeek("Biking");
	      seriesBiking.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
	      seriesBiking.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
	      
	      //Setting the data to Line chart    
	      bikingChart.getData().add(seriesBiking);        
	      bikingChart.setId("stepsChart");
	      bikingChart.getStyleClass().add("activity-chart");
	      bikingChart.setAnimated(false);
	      
	      
	      activityBikingLayout.getChildren().add(bikingChart);
	      bikingButton.setOnAction(e -> {
				 activityLayout.setCenter(activityBikingLayout);
			 });
		 
		 // swimming
		 activitySwimmingLayout = new VBox(5);
		 xAxis = new CategoryAxis();
	     xAxis.setLabel(DateMaker.weekRange()); 
	        
	      //Defining the y axis   
	      yAxis = new NumberAxis   (0, 100, 10); 
	      yAxis.setLabel("Miles"); 
	        
	      //Creating the line chart 
	      swimmingChart = new LineChart<String, Number>(xAxis, yAxis);  
	      swimmingChart.setLegendVisible(false);
	      //Prepare XYChart.Series objects by setting data 
	      seriesSwimming = new XYChart.Series(); 
	      
	      activity.getWeek("Swimming");
	      seriesSwimming.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
	      seriesSwimming.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
	      
	      //Setting the data to Line chart    
	      swimmingChart.getData().add(seriesSwimming);        
	      swimmingChart.setId("stepsChart");
	      swimmingChart.getStyleClass().add("activity-chart");
	      swimmingChart.setAnimated(false);

	      
	      activitySwimmingLayout.getChildren().add(swimmingChart);
	      swimmingButton.setOnAction(e -> {
				 activityLayout.setCenter(activitySwimmingLayout);
			 });
		 
		 
		 activityLayout.setTop(activityTopLayout);
		 activityLayout.setCenter(activityStepsLayout);
		 
		
		// home Layout --------------------------------------------------------
		homeLayout = new BorderPane();
		stepsTable.get();
		// top - date
		
		Label date = new Label(DateMaker.LabelToday());
		date.setAlignment(Pos.CENTER);
		date.setId("dateLbl");
		
		// middle - steps
		VBox stepsLayout = new VBox(1);
		stepsLayout.setId("stepsLayout");
		Label steps = new Label();
		steps.setId("stepsLblImg");
		steps.getStyleClass().add("largeCircle");
		stepsNum = new Label(stepsTable.steps+"");
		stepsNum.getStyleClass().addAll("step-facts-num", "numTop");
		stepsNum.setAlignment(Pos.CENTER);
		Label stepsText = new Label("steps");
		stepsText.getStyleClass().add("step-facts-text");
		stepsText.setAlignment(Pos.CENTER);
		stepsLayout.getChildren().addAll(steps, stepsNum, stepsText);
		
		
		// bottom - step facts
		HBox stepFacts = new HBox(75);
		stepFacts.setId("stepFacts");
			// - calories burned
			VBox stepsCalsLayout = new VBox(1);
			Label calBurnedImg = new Label();
			calBurnedImg.setId("calBurnedImg");
			calBurnedImg.getStyleClass().add("smallCircle");
			calBurnedNum = new Label(active.calsBurned+"");
			Label calBurnedText = new Label("calories burned");
			calBurnedText.getStyleClass().add("step-facts-text");
			calBurnedNum.getStyleClass().add("step-facts-num");
			calBurnedNum.setAlignment(Pos.CENTER);
			calBurnedText.setAlignment(Pos.CENTER);
			stepsCalsLayout.getChildren().addAll(calBurnedImg,calBurnedNum, calBurnedText);
			
			// - distance
			VBox distanceLayout = new VBox(1);
			Label stepsDistImg = new Label();
			stepsDistImg.setId("stepsDistImg");
			stepsDistImg.getStyleClass().add("smallCircle");
			stepsDistNum = new Label(stepsTable.milesWalked+"");
			stepsDistNum.getStyleClass().add("step-facts-num");
			stepsDistNum.setAlignment(Pos.CENTER);
			stepsDistNum.setId("stepsDistNum");
			Label stepsDistText = new Label("miles");
			stepsDistText.getStyleClass().add("step-facts-text");
			stepsDistText.setAlignment(Pos.CENTER);
			distanceLayout.getChildren().addAll(stepsDistImg, stepsDistNum, stepsDistText);
			
			// - floors
			VBox floorsLayout = new VBox(1);
			Label stepsFloorsImg = new Label();
			stepsFloorsImg.setId("stepsFloorsImg");
			stepsFloorsImg.getStyleClass().add("smallCircle");
			stepsFloorsNum = new Label(stepsTable.floors+"");
			stepsFloorsNum.getStyleClass().add("step-facts-num");
			stepsFloorsNum.setAlignment(Pos.CENTER);
			stepsFloorsNum.setId("stepsFloorsNum");
			Label stepsFloorsText = new Label("floors");
			stepsFloorsText.getStyleClass().add("step-facts-text");
			stepsFloorsText.setAlignment(Pos.CENTER);
			floorsLayout.getChildren().addAll(stepsFloorsImg, stepsFloorsNum, stepsFloorsText);
			
			// - active minutes 
			VBox activeMinsLayout = new VBox(1);
			Label activeMinsImg = new Label();
			activeMinsImg.setId("stepsMinImg");
			activeMinsImg.getStyleClass().add("smallCircle");
			activeMinsNum = new Label(active.minutes +"");
			activeMinsNum.setId("activeMinsNum");
			activeMinsNum.getStyleClass().add("step-facts-num");
			activeMinsNum.setAlignment(Pos.CENTER);
			Label activeMinsText = new Label("active minutes");
			activeMinsText.getStyleClass().add("step-facts-text");
			activeMinsText.setAlignment(Pos.CENTER);
			activeMinsLayout.getChildren().addAll(activeMinsImg, activeMinsNum, activeMinsText);
			
			
		stepFacts.getChildren().addAll(stepsCalsLayout,distanceLayout, floorsLayout, activeMinsLayout);
		
		stepsLayout.setAlignment(Pos.TOP_CENTER);
		stepFacts.setAlignment(Pos.CENTER);
		homeLayout.setTop(date);
		homeLayout.setCenter(stepsLayout);
		homeLayout.setBottom(stepFacts);
		homeLayout.setAlignment(date,Pos.CENTER);


		//Meals Layout ------------------------------------------------------------------------
		mealsLayout = new BorderPane();
		
		// top - date
		meals.get();
		BorderPane mealsTop = new BorderPane();
		Label caloriesLbl = new Label("Today's Calories");
		caloriesLbl.setAlignment(Pos.CENTER);
		caloriesLbl.setId("caloriesLbl");
		mealsAddButton = new Button();
		mealsAddButton.getStyleClass().add("add-button");
		mealsAddButton.setAlignment(Pos.CENTER_RIGHT);
		mealsTop.setTop(caloriesLbl);
		mealsTop.setCenter(mealsAddButton);
		
		// middle - total calories
		
		
		VBox caloriesLayout = new VBox(1);
		caloriesLayout.setId("caloriesLayout");
		Label caloriesImg = new Label();
		caloriesImg.setId("caloriesLblImg");
		caloriesImg.getStyleClass().add("largeCircle");
		caloriesNum = new Label(meals.totalCals+"");
		caloriesNum.getStyleClass().addAll("facts-num", "numTop");
		caloriesNum.setAlignment(Pos.CENTER);
		Label caloriesText = new Label("total");
		caloriesText.getStyleClass().add("facts-text");
		caloriesText.setAlignment(Pos.CENTER);
		caloriesLayout.getChildren().addAll(caloriesImg, caloriesNum, caloriesText);
		
		
		// bottom - meal facts
		HBox mealFacts = new HBox(75);
		mealFacts.setId("mealFacts");
			// - breakfast
			VBox breakfast = new VBox(1);
			Label breakfastImg = new Label();
			breakfastImg.setId("breakfastImg");
			breakfastImg.getStyleClass().add("smallCircle");
			breakfastCalNum = new Label(meals.breakfastCals+"");
			breakfastCalNum.getStyleClass().add("facts-num");
			breakfastCalNum.setAlignment(Pos.CENTER);
			Label breakfastCalText = new Label("breakfast");
			breakfastCalText.getStyleClass().add("facts-text");
			breakfastCalText.setAlignment(Pos.CENTER);
			breakfast.getChildren().addAll(breakfastImg,breakfastCalNum, breakfastCalText);
			
			// - lunch
			VBox lunch = new VBox(1);
			Label lunchImg = new Label();
			lunchImg.setId("lunchImg");
			lunchImg.getStyleClass().add("smallCircle");
			lunchCalNum = new Label(meals.lunchCals+"");
			lunchCalNum.getStyleClass().add("facts-num");
			lunchCalNum.setAlignment(Pos.CENTER);
			Label lunchCalText = new Label("lunch");
			lunchCalText.getStyleClass().add("facts-text");
			lunchCalText.setAlignment(Pos.CENTER);
			lunch.getChildren().addAll(lunchImg, lunchCalNum, lunchCalText);
			
			// - dinner
			VBox dinner = new VBox(1);
			Label dinnerImg = new Label();
			dinnerImg.setId("dinnerImg");
			dinnerImg.getStyleClass().add("smallCircle");
			dinnerCalNum = new Label(meals.dinnerCals+"");
			dinnerCalNum.getStyleClass().add("facts-num");
			dinnerCalNum.setAlignment(Pos.CENTER);
			Label dinnerCalText = new Label("dinner");
			dinnerCalText.getStyleClass().add("facts-text");
			dinnerCalText.setAlignment(Pos.CENTER);
			dinner.getChildren().addAll(dinnerImg, dinnerCalNum, dinnerCalText);
			
			// - snacks  
			VBox snacks = new VBox(1);
			Label snacksImg = new Label();
			snacksImg.setId("snacksImg");
			snacksImg.getStyleClass().add("smallCircle");
			snacksCalNum = new Label(meals.snackCals+"");
			snacksCalNum.getStyleClass().add("facts-num");
			snacksCalNum.setAlignment(Pos.CENTER);
			Label snacksCalText = new Label("snacks");
			snacksCalText.getStyleClass().add("facts-text");
			snacksCalText.setAlignment(Pos.CENTER);
			snacks.getChildren().addAll(snacksImg, snacksCalNum, snacksCalText);
			
			
		mealFacts.getChildren().addAll(breakfast,lunch, dinner, snacks);
		
		caloriesLayout.setAlignment(Pos.TOP_CENTER);
		mealFacts.setAlignment(Pos.CENTER);
		mealsLayout.setTop(mealsTop);
		mealsLayout.setCenter(caloriesLayout);
		mealsLayout.setBottom(mealFacts);
		mealsLayout.setAlignment(caloriesLbl,Pos.CENTER);
		mealsLayout.setAlignment(mealsAddButton, Pos.CENTER_RIGHT);


		//Weight Layout -------------------------------------------------------------------------------------
		weightLayout = new BorderPane();
		
		weight.get();
		BorderPane weightTop = new BorderPane();
		Label weightLbl = new Label("Weight");
		weightLbl.setId("weightLbl");
		weightLbl.setAlignment(Pos.CENTER);
		weightTop.setCenter(weightLbl);
		
		
		//middle - graphs and facts
		BorderPane weightInfo = new BorderPane();
		weightInfo.setId("weightInfoLayout");
		
			
			BorderPane currentWeightLayout = new BorderPane();
			currentWeightLayout.setId("currentWeightLayout");
			Label currentWeightLbl = new Label(weight.weight+" lbs");
			currentWeightLbl.setId("currentWeightLbl");
			currentWeightLbl.setTextAlignment(TextAlignment.CENTER);
			weightAddButton = new Button();
			weightAddButton.getStyleClass().add("add-button");
			currentWeightLayout.setAlignment(currentWeightLbl, Pos.TOP_CENTER);
			currentWeightLayout.setAlignment(weightAddButton, Pos.TOP_RIGHT);
			currentWeightLayout.setCenter(currentWeightLbl);
			currentWeightLayout.setRight(weightAddButton);

			//graph
			xAxis = new CategoryAxis();
			
		     xAxis.setLabel(DateMaker.weekRange()); 
		        
		      //Defining the y axis   
		      yAxis = new NumberAxis   (0, 350, 75); 
		      yAxis.setLabel("Lbs"); 
		        
		      //Creating the line chart 
		      LineChart<String,Number> weightChart = new LineChart<String, Number>(xAxis, yAxis);  
		      weightChart.setLegendVisible(false);
		      //Prepare XYChart.Series objects by setting data 
		      seriesWeight = new XYChart.Series(); 
		      
		      weight.getWeek();
		      seriesWeight.getData().add(new XYChart.Data("SUN", weight.weeklyData.get("SUNDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("MON", weight.weeklyData.get("MONDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("TUE", weight.weeklyData.get("TUESDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("WED", weight.weeklyData.get("WEDNESDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("THU", weight.weeklyData.get("THURSDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("FRI", weight.weeklyData.get("FRIDAY"))); 
		      seriesWeight.getData().add(new XYChart.Data("SAT", weight.weeklyData.get("SATURDAY")));
		           
		      //Setting the data to Line chart    
		      weightChart.getData().add(seriesWeight);        
		      weightChart.setId("stepsChart");
		      weightChart.getStyleClass().add("activity-chart");
		      weightChart.setAnimated(false);
		      
		     // bottom - facts
		      HBox weightFacts = new HBox(0);
		      Label bmiText = new Label("BMI: ");
		      bmiText.getStyleClass().add("weight-facts-text");
		      Label bmiNum = new Label(weight.bmi+"");
		      bmiNum.setId("bmiNum");
		      bmiNum.getStyleClass().add("weight-facts-num");
		     
		      
		      Label bwpText = new Label("BWP: ");
		      bwpText.getStyleClass().add("weight-facts-text");
		      Label bwpNum = new Label(weight.bwp+"");
		      bwpNum.getStyleClass().add("weight-facts-num");
		      weightFacts.getChildren().addAll(bmiText, bmiNum, bwpText, bwpNum);
		      weightFacts.setAlignment(Pos.CENTER);
		      
			//weightInfo.getChildren().addAll(currentWeightLayout, weightChart, weightFacts);
			weightInfo.setTop(currentWeightLayout);
			weightInfo.setCenter(weightChart);
			weightInfo.setBottom(weightFacts);
			
		
		currentWeightLbl.setAlignment(Pos.TOP_CENTER);
		//weightInfo.setAlignment(Pos.CENTER);
		weightLayout.setTop(weightTop);
		weightLayout.setCenter(weightInfo);
		weightLayout.setAlignment(weightLbl, Pos.CENTER);
		
		
		// Account ----------------------------------------------------------------------------------
		accountLayout = new BorderPane();
		
		//top ---------------------------------------
		BorderPane accountTop = new BorderPane();
		Label accountLbl = new Label("Account");
		accountLbl.setId("accountLbl");
		accountLbl.setAlignment(Pos.CENTER);
		accountTop.setCenter(accountLbl);
		
		
		// middle -------------------------------------
		BorderPane accountMiddle = new BorderPane();
		users.get();
		accountInfo.get();
		
		VBox userInfo = new VBox(8);
		userInfo.setId("userinfo");
		Label name = new Label("Name: " +accountInfo.name);
		name.getStyleClass().add("facts-text");
		Label usernameText = new Label("Username: " +users.username);
		usernameText.getStyleClass().add("facts-text");
		Label height = new Label("Height: "+accountInfo.feet+" FT " + accountInfo.inches + " IN");
		height.getStyleClass().add("facts-text");
		Button logout = new Button("logout");
		logout.setOnAction(e -> {
			window.close();
		});
		userInfo.getChildren().addAll(name, usernameText, height, logout);
		
		accountMiddle.setTop(userInfo);
		
		accountLayout.setTop(accountTop);
		accountLayout.setCenter(accountMiddle);
		
		
	
		mainLayout.setLeft(controlsLayout);
		mainLayout.setCenter(homeLayout);

		mainScene = new Scene(mainLayout, 1200, 600);
		loginScene = new Scene(loginLayout, 1200, 600);
		

		window.setScene(mainScene);
		window.setTitle("Activiosa");
		window.setFullScreen(true);
		window.show();
		

		associateStyles();
		actions();
		

        
	}
		
		public void associateStyles() throws MalformedURLException {
			//link stylesheet
			mainScene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
			loginScene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
			
			//layouts
		
			homeLayout.getStyleClass().add("centralView");	
			mealsLayout.getStyleClass().add("centralView");
			weightLayout.getStyleClass().add("centralView");
			activityLayout.getStyleClass().add("centralView");
			accountLayout.getStyleClass().add("centralView");
		}

		public void actions() {
			

			mealsButton.setOnAction(e -> {
				meals.get();
				mainLayout.setCenter(mealsLayout);
			});
			
			weightButton.setOnAction(e -> {
				weight.get();
				mainLayout.setCenter(weightLayout);
			});
			
			activityButton.setOnAction(e -> {
				stepsTable.get();
				mainLayout.setCenter(activityLayout);
				stepsButton.requestFocus();
			});
			homeButton.setOnAction(e -> mainLayout.setCenter(homeLayout));
			
			activityAddButton.setOnAction(e -> {
		        activityModal.sizeToScene();
		        activityModal.show();
			});
			
			weightAddButton.setOnAction(e -> {
				 weightModal.sizeToScene();
			     weightModal.show();
			     weightButton.requestFocus();
			});
			
			mealsAddButton.setOnAction(e -> {
				meals.get();
				mealsModal.sizeToScene();
			     mealsModal.show();
			});
			
			if(stepsButton.isFocused() || bikingButton.isFocused() || 
					runningButton.isFocused() || swimmingButton.isFocused() 
					|| activityAddButton.isFocused())  {
				activityButton.getStyleClass().add("category-active");
				
			}
			else {
				activityButton.getStyleClass().add("category-passive");
			}
			
			profileButton.setOnAction(e -> {
				mainLayout.setCenter(accountLayout);
				
			});
			
			
			
		}
		
		public static Integer stepsPerThousand(Integer i) {
		
	    	if(i == null) {
	    		return null;
	    	}
	    	else {
	    		return i / 1000;
	    	}
	    
	    }
	}


/*****
 * This class creates the activity Dialog
 * @author Carson Uecker-Herman
 *
 */
class activityDialog extends Stage {
	activity act = new activity();
    @SuppressWarnings("unchecked")
	public activityDialog(Stage owner) {
    	
        super();
        initOwner(owner);
        setTitle("Add Activity");
        initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root, 400 , 400);
        setScene(scene);
        
        ObservableList<String> activities = FXCollections.observableArrayList(//
                "Steps", "Running", "Biking", "Swimming");
        
        GridPane gridpane = new GridPane();
        gridpane.setId("activity-popup");
        gridpane.setVgap(15);
        gridpane.setHgap(10);
        ComboBox activityList = new ComboBox(activities);
        
        activityList.setPromptText("Activity Type");
        
        activityList.getStyleClass().add("modal-combo");
        
        Label activityLbl = new Label("Select Type");
        activityLbl.setLabelFor(activityList);
        activityLbl.getStyleClass().add("modal-label");
        
        TextField distance = new TextField();
        distance.setPromptText("miles");
        distance.getStyleClass().add("modal-textfield");
        Label distanceLbl = new Label("Distance:");
        distanceLbl.getStyleClass().add("modal-label");        
        TextField duration = new TextField();
        duration.setPromptText("hh:mm");
        duration.getStyleClass().add("modal-textfield");
        Label durationLbl = new Label("Duration:");
        durationLbl.getStyleClass().add("modal-label");
        
        TextField calsburned = new TextField();
        calsburned.setPromptText("calories");
        calsburned.getStyleClass().add("modal-textfield");
        Label calsBurnedLbl = new Label("Calories Burned:");
        calsBurnedLbl.getStyleClass().add("modal-label");
        
        DatePicker date = new DatePicker();   
        date.getStyleClass().add("modal-datepicker");
        date.setEditable(false);
        date.setValue(DateMaker.Today().toLocalDate());
        
        
        Label dateLbl = new Label("Date:");
        dateLbl.getStyleClass().add("modal-label");
        
        Button saveActivity = new Button("save");
        saveActivity.getStyleClass().add("save-button");
        
        gridpane.add(activityLbl, 1, 0);
        gridpane.add(activityList, 2, 0);
        gridpane.add(distanceLbl, 1, 1);
        gridpane.add(distance, 2, 1);
        gridpane.add(durationLbl, 1, 2);
        gridpane.add(duration, 2, 2);
        gridpane.add(calsBurnedLbl, 1, 3);
        gridpane.add(calsburned, 2, 3);
        gridpane.add(dateLbl, 1, 4);
        gridpane.add(date, 2, 4);
        gridpane.add(saveActivity, 2, 5);
        
        root.getChildren().addAll(gridpane);

        saveActivity.setOnAction(e -> {
        	
        	if(activityList.getValue() == "Steps") {
        		 stepsTable.insert(DateMaker.ToSQLDate(date.getValue()), Integer.parseInt(distance.getText()), 0, Double.parseDouble(calsburned.getText()));
        		 
        		 stepsTable.getWeek();
        		 GUI.seriesSteps.getData().add(new XYChart.Data("SUN", GUI.stepsPerThousand(stepsTable.weeklySteps.get("SUNDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("MON", GUI.stepsPerThousand(stepsTable.weeklySteps.get("MONDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("TUE", GUI.stepsPerThousand(stepsTable.weeklySteps.get("TUESDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("WED", GUI.stepsPerThousand(stepsTable.weeklySteps.get("WEDNESDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("THU", GUI.stepsPerThousand(stepsTable.weeklySteps.get("THURSDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("FRI", GUI.stepsPerThousand(stepsTable.weeklySteps.get("FRIDAY")))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("SAT", GUI.stepsPerThousand(stepsTable.weeklySteps.get("SATURDAY"))));
       	         GUI.stepsChart.getData().add(GUI.seriesSteps);  
        		 
        		
        		 GUI.activityLayout.setCenter(GUI.activityStepsLayout);
        		 stepsTable.milesWalked += Double.parseDouble(distance.getText()) / 2000;
        		 stepsTable.update();
        		 active.floors = Integer.parseInt(distance.getText()) / 5000;
        		 active.update();
        		 GUI.stepsFloorsNum.setText(active.floors+"");
        		 
        		 close();
        	}
        	else {
        		if(date.getValue()!=null && activityList.getValue()!=null && duration.getText()!=null && distance.getText()!=null&&calsburned.getText()!=null) {
                	activity.insert(DateMaker.ToSQLDate(date.getValue()), activityList.getValue().toString(), duration.getText(), Double.parseDouble(distance.getText()), Double.parseDouble(calsburned.getText()));
                	
                	if(activityList.getValue() == "Running") {
                		
           			 activity.getWeek("Running");
           			 GUI.seriesRunning.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
              	     GUI.seriesRunning.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
   	       	       	 GUI.runningChart.getData().add(GUI.seriesRunning);
   	        		 GUI.activityLayout.setCenter(GUI.activityRunningLayout);
   	        		 
   	        		stepsTable.milesWalked += Double.parseDouble(distance.getText());
   	        		stepsTable.update();
   	        		 this.close();
           		 }
           		 else if(activityList.getValue() == "Biking") {
           			 activity.getWeek("Biking");
           			 GUI.seriesBiking.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
              	      	 GUI.seriesBiking.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
              	         GUI.seriesBiking.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
              	         GUI.seriesBiking.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
              	         GUI.seriesBiking.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
              	         GUI.seriesBiking.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
              	         GUI.seriesBiking.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
              	         GUI.bikingChart.getData().add(GUI.seriesBiking);
   	       	         
   	        		 GUI.activityLayout.setCenter(GUI.activityBikingLayout);
   	        		 close();
           		 }
           		 
           		 else if(activityList.getValue() == "Swimming") {
           			 activity.getWeek("Swimming");
           			 GUI.seriesSwimming.getData().add(new XYChart.Data("SUN", activity.weeklyData.get("SUNDAY"))); 
              	      	 GUI.seriesSwimming.getData().add(new XYChart.Data("MON", activity.weeklyData.get("MONDAY"))); 
              	         GUI.seriesSwimming.getData().add(new XYChart.Data("TUE", activity.weeklyData.get("TUESDAY"))); 
              	         GUI.seriesSwimming.getData().add(new XYChart.Data("WED", activity.weeklyData.get("WEDNESDAY"))); 
              	         GUI.seriesSwimming.getData().add(new XYChart.Data("THU", activity.weeklyData.get("THURSDAY"))); 
              	         GUI.seriesSwimming.getData().add(new XYChart.Data("FRI", activity.weeklyData.get("FRIDAY"))); 
              	         GUI.seriesSwimming.getData().add(new XYChart.Data("SAT", activity.weeklyData.get("SATURDAY")));
              	         GUI.swimmingChart.getData().add(GUI.seriesSwimming);
   	       	         
   	        		 GUI.activityLayout.setCenter(GUI.activitySwimmingLayout);
   	        		 close();
   	        		 
           		 }
        		}
        		 
        	}
        	
        	close();
        	active.calsBurned += Integer.parseInt(calsburned.getText());
        	try {
				active.minutes += DateMaker.numOfMinutes(duration.getText());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	active.update();
        	GUI.calBurnedNum.setText(active.calsBurned+"");
        	GUI.activeMinsNum.setText(active.minutes+"");
        	
        });
        activityList.setOnAction(e -> {
        	if(activityList.getValue() == "Steps") {
        		gridpane.getChildren().remove(duration);
        		gridpane.getChildren().remove(durationLbl);
        		distance.setPromptText("Steps");
        		distanceLbl.setText("Steps:");
        		
        	}
        	else {
        		 gridpane.add(durationLbl, 1, 2);
        	     gridpane.add(duration, 2, 2);
        		 distance.setPromptText("Miles");
        		 distanceLbl.setText("Distance:");
        	}
        	
        });
        
        try {
			scene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
		} catch (MalformedURLException e1) {
		
		}
    }
}

/***
 * This class creates the Weight Dialog
 * @author Carson Uecker-Herman
 *
 */
class weightDialog extends Stage {
	
	@SuppressWarnings("unchecked")
	public weightDialog(Stage owner) {
    	
        super();
        initOwner(owner);
        setTitle("Add Weight");
        initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root);
        setScene(scene);
        
        GridPane gridpane = new GridPane();
        gridpane.setVgap(15);
        gridpane.setHgap(10);
        gridpane.setId("weightModal");
        
        Label weightLbl = new Label("Weight:");
        weightLbl.getStyleClass().add("modal-label");
        
        TextField weightNum = new TextField();
        weightNum.setPromptText("Lbs");
        weightNum.getStyleClass().add("modal-textfield");
        
        Label dateLbl = new Label("Date:");
        dateLbl.getStyleClass().add("modal-label");
        
        DatePicker date = new DatePicker();   
        date.getStyleClass().add("modal-datepicker");
        date.setEditable(false);
        date.setValue(DateMaker.Today().toLocalDate());
        
        
        
        
        Button save = new Button("save");
        save.getStyleClass().add("save-button");
        
        gridpane.add(weightLbl, 0, 0);
        gridpane.add(weightNum, 1, 0);
        gridpane.add(dateLbl, 0, 1);
        gridpane.add(date, 1, 1);
        gridpane.add(save, 1, 2);
        
        root.getChildren().addAll(gridpane);
        
        save.setOnAction(e -> {
        	weight.insertWeight(DateMaker.ToSQLDate(date.getValue()), Double.parseDouble(weightNum.getText()));
        	
        	  weight.getWeek();
		      GUI.seriesWeight.getData().add(new XYChart.Data("SUN", weight.weeklyData.get("SUNDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("MON", weight.weeklyData.get("MONDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("TUE", weight.weeklyData.get("TUESDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("WED", weight.weeklyData.get("WEDNESDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("THU", weight.weeklyData.get("THURSDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("FRI", weight.weeklyData.get("FRIDAY"))); 
		      GUI.seriesWeight.getData().add(new XYChart.Data("SAT", weight.weeklyData.get("SATURDAY")));
		      GUI.weightChart.getData().add(GUI.seriesWeight);
        
		      weight.get();
		      accountInfo.get();
		      GUI.mainLayout.setCenter(GUI.weightLayout);
		      close();
        });
        
        try {
			scene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
		} catch (MalformedURLException e1) {
			
		}
		
	}
}

/***
 * This class creates the meals dialog 
 * @author Carson Uecker-Herman
 *
 */
class mealsDialog extends Stage {
	public mealsDialog(Stage owner) {
		super();
        initOwner(owner);
        setTitle("Add Meals");
        initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root);
        setScene(scene);
        
        GridPane gridpane = new GridPane();
        gridpane.setVgap(15);
        gridpane.setHgap(10);
        gridpane.setId("mealsModal");
        
        Label breakfastLbl = new Label("Breakfast:");
        breakfastLbl.getStyleClass().add("modal-label");
        
        TextField breakfastNum = new TextField();
        breakfastNum.getStyleClass().add("modal-textfield");
        breakfastNum.setText(meals.breakfastCals+"");
        
        Label lunchLbl = new Label("Lunch:");
        lunchLbl.getStyleClass().add("modal-label");
        
        TextField lunchNum = new TextField();
        lunchNum.getStyleClass().add("modal-textfield");
        lunchNum.setText(meals.lunchCals+"");
        
        Label dinnerLbl = new Label("Dinner:");
        dinnerLbl.getStyleClass().add("modal-label");
        
        TextField dinnerNum = new TextField();
        dinnerNum.getStyleClass().add("modal-textfield");
        dinnerNum.setText(meals.dinnerCals+"");
        
        Label snacksLbl = new Label("Snacks:");
        snacksLbl.getStyleClass().add("modal-label");
        
        TextField snacksNum = new TextField();
        snacksNum.getStyleClass().add("modal-textfield");
        snacksNum.setText(meals.snackCals+"");
        
        Button save = new Button("save");
        save.getStyleClass().add("save-button");
        
        gridpane.add(breakfastLbl, 0, 0);
        gridpane.add(breakfastNum, 1, 0);
        gridpane.add(lunchLbl, 0, 1);
        gridpane.add(lunchNum, 1, 1);
        gridpane.add(dinnerLbl, 0, 2);
        gridpane.add(dinnerNum, 1, 2);
        gridpane.add(snacksLbl, 0, 3);
        gridpane.add(snacksNum, 1, 3);
        gridpane.add(save, 1, 4);
        
        root.getChildren().addAll(gridpane);
        
        save.setOnAction(e -> {
        	int breakfast = 0;
        	int lunch = 0;
        	int dinner = 0;
        	int snacks = 0;
        	
        	if(!breakfastNum.getText().isEmpty()) {
        		breakfast = Integer.parseInt(breakfastNum.getText());
        	}
        	
        	if(!lunchNum.getText().isEmpty()) {
        		lunch = Integer.parseInt(lunchNum.getText());
        	}
        	
        	if(!dinnerNum.getText().isEmpty()) {
        		dinner = Integer.parseInt(dinnerNum.getText());
        	}
        	
        	if(!snacksNum.getText().isEmpty()) {
        		snacks = Integer.parseInt(snacksNum.getText());
        	}
        	
        	
        	if(meals.exists) {
        		meals.breakfastCals = breakfast;
        		meals.lunchCals = lunch;
        		meals.dinnerCals = dinner;
        		meals.snackCals = snacks;
        		meals.update();
        		meals.get();
        		
        	}
        	else {
        		meals.insert(breakfast, lunch, dinner, snacks);
        		meals.get();
        	}
        	
        	GUI.caloriesNum.setText(meals.totalCals+"");
        	GUI.breakfastCalNum.setText(meals.breakfastCals+"");
        	GUI.lunchCalNum.setText(meals.lunchCals+"");
        	GUI.dinnerCalNum.setText(meals.dinnerCals+"");
        	GUI.snacksCalNum.setText(meals.snackCals+"");
        	//meals.get();
        	GUI.mainLayout.setCenter(GUI.mealsLayout);
        	close();
        	
        });
        
        try {
			scene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
		} catch (MalformedURLException e1) {
			
		}
	}
}


