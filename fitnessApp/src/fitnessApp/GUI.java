package fitnessApp;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
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

public class GUI extends Application {

	protected activity act = new activity();
	Stage window;
	private Scene scene;
	
	Circle lgCircle = new Circle(100);
	Circle smCircle = new Circle(75);
	

	//Layouts
	protected static VBox activityTopBar, bmiLayout, dataLayout, controlsLayout, 
	activityStepsLayout, activityRunningLayout, activityBikingLayout, activitySwimmingLayout;
	protected static Series<String, Number> seriesSteps, seriesRunning, seriesSwimming, seriesBiking, seriesWeight;
	protected static LineChart<String,Number> stepsChart, runningChart, bikingChart, swimmingChart;
	protected static HBox activityMenuLayout;
	protected static BorderPane mainLayout,activityLayout, homeLayout, mealsLayout, weightLayout;
	
	//Buttons
	private Button activityButton;
	private Button weightButton;
	private Button dataButton;
	private Button mealsButton;
	private Button bmiButton;
	private Button bmiSubmit;
	private Button profileButton;
	private Button runningButton;
	private Button stepsButton;
	private Button swimmingButton;
	private Button bikingButton;
	private Button homeButton;
	private Button activityAddButton;
	//Labels
	private Label bmiTitle;
	private Label weightTitle;
	private Label activityTitle;
	private Label mealsTitle;
	private Label dataTitle;
	private Label homeTitle;
	static Stage activityModal;
	LocalDateTime currentTime;
	private static String currentLayout;


	public void start(Stage primaryStage) throws Exception {
		window  = primaryStage;
		activityModal = new activityDialog(primaryStage);	
		

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
		 activityMenuLayout.getChildren().addAll(stepsButton, runningButton, bikingButton, swimmingButton, activityAddButton);
		 
		 
		 // steps --------------------------------------------------------------
		 activityStepsLayout = new VBox(5);
		 activityStepsLayout.setId("activityStepsLayout");
		 stepsButton.requestFocus();
		 CategoryAxis xAxis = new CategoryAxis();
	     xAxis.setLabel(DateMaker.weekRange()); 
	        
	      //Defining the y axis   
	      NumberAxis yAxis = new NumberAxis(0, 25000, 5000); 
	      yAxis.setLabel("Steps"); 
	        
	      //Creating the line chart 
	      stepsChart = new LineChart<String, Number>(xAxis, yAxis);  
	      stepsChart.setLegendVisible(false);
	      
	      //Prepare XYChart.Series objects by setting data 
	      seriesSteps = new XYChart.Series(); 
	      
	      
	      stepsTable.getWeek();
	      seriesSteps.getData().add(new XYChart.Data("SUN", stepsTable.weeklySteps.get("SUNDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("MON", stepsTable.weeklySteps.get("MONDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("TUE", stepsTable.weeklySteps.get("TUESDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("WED", stepsTable.weeklySteps.get("WEDNESDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("THU", stepsTable.weeklySteps.get("THURSDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("FRI", stepsTable.weeklySteps.get("FRIDAY"))); 
	      seriesSteps.getData().add(new XYChart.Data("SAT", stepsTable.weeklySteps.get("SATURDAY")));
	      
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
		 
		 
		 activityLayout.setTop(activityMenuLayout);
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
		Label stepsNum = new Label(stepsTable.steps+"");
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
			Label calBurnedNum = new Label(active.calsBurned+"");
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
			Label stepsDistNum = new Label(stepsTable.milesWalked+"");
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
			Label stepsFloorsNum = new Label(stepsTable.floors+"");
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
			Label activeMinsNum = new Label(active.minutes +"");
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
		Label caloriesLbl = new Label("Today's Calories");
		caloriesLbl.setAlignment(Pos.CENTER);
		caloriesLbl.setId("caloriesLbl");
		
		// middle - total calories
		VBox caloriesLayout = new VBox(1);
		caloriesLayout.setId("caloriesLayout");
		Label caloriesImg = new Label();
		caloriesImg.setId("caloriesLblImg");
		caloriesImg.getStyleClass().add("largeCircle");
		Label caloriesNum = new Label("0");
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
			Label breakfastCalNum = new Label("0");
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
			Label lunchCalNum = new Label("0");
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
			Label dinnerCalNum = new Label("0");
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
			Label snacksCalNum = new Label("0");
			snacksCalNum.getStyleClass().add("facts-num");
			snacksCalNum.setAlignment(Pos.CENTER);
			Label snacksCalText = new Label("snacks");
			snacksCalText.getStyleClass().add("facts-text");
			snacksCalText.setAlignment(Pos.CENTER);
			snacks.getChildren().addAll(snacksImg, snacksCalNum, snacksCalText);
			
			
		mealFacts.getChildren().addAll(breakfast,lunch, dinner, snacks);
		
		caloriesLayout.setAlignment(Pos.TOP_CENTER);
		mealFacts.setAlignment(Pos.CENTER);
		mealsLayout.setTop(caloriesLbl);
		mealsLayout.setCenter(caloriesLayout);
		mealsLayout.setBottom(mealFacts);
		mealsLayout.setAlignment(caloriesLbl,Pos.CENTER);


		//Weight Layout -------------------------------------------------------------------------------------
		weightLayout = new BorderPane();
		Label weightLbl = new Label("Weight");
		weightLbl.setId("weightLbl");
		
		//middle - graphs and facts
		VBox weightInfo = new VBox(10);
		
			Label currentWeightLbl = new Label(accountInfo.currentWeight+" lbs");
			currentWeightLbl.setId("currentWeightLbl");
			currentWeightLbl.setTextAlignment(TextAlignment.CENTER);
		

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
			weightInfo.getChildren().addAll(currentWeightLbl, weightChart, weightFacts);
		
		currentWeightLbl.setAlignment(Pos.TOP_CENTER);
		weightInfo.setAlignment(Pos.CENTER);
		weightLayout.setTop(weightLbl);
		weightLayout.setCenter(weightInfo);
		weightLayout.setAlignment(weightLbl, Pos.CENTER);
		
	
		mainLayout.setLeft(controlsLayout);
		mainLayout.setCenter(homeLayout);

		scene = new Scene(mainLayout, 1200, 600);


		window.setScene(scene);
		window.setTitle("Fitness App");
		window.show();

		associateStyles();
		actions();
		

        
	}

		public void associateStyles() throws MalformedURLException {
			//link stylesheet
			scene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
			
			
			//layouts
		
			homeLayout.getStyleClass().add("centralView");	
			mealsLayout.getStyleClass().add("centralView");
			weightLayout.getStyleClass().add("centralView");
			activityLayout.getStyleClass().add("centralView");
		}

		public void actions() {
			

			mealsButton.setOnAction(e -> mainLayout.setCenter(mealsLayout));
			weightButton.setOnAction(e -> mainLayout.setCenter(weightLayout));
			activityButton.setOnAction(e -> {
				stepsTable.get();
				mainLayout.setCenter(activityLayout);
			});
			homeButton.setOnAction(e -> mainLayout.setCenter(homeLayout));
			activityAddButton.setOnAction(e -> {
		        activityModal.sizeToScene();
		        activityModal.show();
			});
			
		}
	}


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
        		 GUI.seriesSteps.getData().add(new XYChart.Data("SUN", stepsTable.weeklySteps.get("SUNDAY"))); 
       	      	 GUI.seriesSteps.getData().add(new XYChart.Data("MON", stepsTable.weeklySteps.get("MONDAY"))); 
       	         GUI.seriesSteps.getData().add(new XYChart.Data("TUE", stepsTable.weeklySteps.get("TUESDAY"))); 
       	         GUI.seriesSteps.getData().add(new XYChart.Data("WED", stepsTable.weeklySteps.get("WEDNESDAY"))); 
       	         GUI.seriesSteps.getData().add(new XYChart.Data("THU", stepsTable.weeklySteps.get("THURSDAY"))); 
       	         GUI.seriesSteps.getData().add(new XYChart.Data("FRI", stepsTable.weeklySteps.get("FRIDAY"))); 
       	         GUI.seriesSteps.getData().add(new XYChart.Data("SAT", stepsTable.weeklySteps.get("SATURDAY")));
       	         GUI.stepsChart.getData().add(GUI.seriesSteps);  
        		 
        		
        		 GUI.activityLayout.setCenter(GUI.activityStepsLayout);
        		 stepsTable.milesWalked += Double.parseDouble(distance.getText()) / 2000;
        		 stepsTable.update();
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
        });
        activityList.setOnAction(e -> {
        	if(activityList.getValue() == "Steps") {
        		gridpane.getChildren().remove(duration);
        		gridpane.getChildren().remove(durationLbl);
        		distance.setPromptText("Steps");
        		
        	}
        	else {
        		 gridpane.add(durationLbl, 1, 2);
        	     gridpane.add(duration, 2, 2);
        		 distance.setPromptText("Miles");
        	}
        	
        });
        
        try {
			scene.getStylesheets().add(new File("src/fitnessApp/main.css").toURI().toURL().toString());
		} catch (MalformedURLException e1) {
		
		}
    }
    
}


