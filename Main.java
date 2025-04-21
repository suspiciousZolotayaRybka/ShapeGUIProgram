package application;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Main.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This the for the GUI shape program. It creates the shape application that
 * uses event handlers and listeners to expand on the Project 1 shape theme.
 * @author fineh
 *
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	// Initialize starting variables
	private static Button createButton = new Button();
	private static Label userInterfaceLabel = new Label("No Shape Created");
	private static StackPane shapeWindow;
	private static Shape shape = null;
	private static ColorPicker shapeColorPicker;

	/**
	 * The start for the Shape Application
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// Create the shape display on the right side of the screen
			shapeWindow = new StackPane();
			shapeWindow.getChildren().addAll(new Label("No Shape Created"));

			// Create the UI on the left hand side (choice box, create button, label with
			// shape info toString, Clear button)
			userInterfaceLabel = new Label("No Shape Created");
			userInterfaceLabel.getStyleClass().add("bordered");
			userInterfaceLabel.setWrapText(true);
			shapeColorPicker = initializeShapeColorPicker();
			ChoiceBox<String> shapeChoiceBox = initializeShapeChoiceBox();
			// The createButton changes its handler dynamically based on the shapeChoice
			// entered by the user
			createButton = CreateButtonManager.changeToCreateCircleButton(createButton);
			Button clearButton = initializeClearButton();

			// Create the user interface in the left side of the screen
			VBox leftPanel = initializeLeftPanel(shapeChoiceBox, clearButton);

			// Set the leftPanel for user interface and the shape window side by side
			HBox root = new HBox();
			root.getChildren().addAll(leftPanel, shapeWindow);
			HBox.setHgrow(shapeWindow, Priority.ALWAYS);

			// Set the scene window to 1250 width 850 height
			Scene scene = new Scene(root, 1050, 850);

			// Add any CSS styling
			scene.getStylesheets().add(getClass().getResource("shapeApplication.css").toExternalForm());

			// Set the window in the top left of the user's screen
			primaryStage.setX(0);
			primaryStage.setY(0);

			// Set and show the scene
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return the initialized ColorPicker
	 */
	private static ColorPicker initializeShapeColorPicker() {
		ColorPicker shapeColorPicker = new ColorPicker();
		shapeColorPicker.setValue(Color.BLACK);
		return shapeColorPicker;
	}

	/**
	 *
	 *
	 * @return return the ShapeChoiceBox to the user
	 */
	private static ChoiceBox<String> initializeShapeChoiceBox() {
		// Initialize the ChoiceBoc
		ChoiceBox<String> shapeChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList("Circle", "Square",
				"Triangle", "Rectangle", "Sphere", "Cube", "Cone", "Cylinder", "Torus"));
		shapeChoiceBox.setValue("Circle");

		// Change the CreateButton depending on the shape
		shapeChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				switch (shapeChoiceBox.getValue()) {
				case "Circle":
					CreateButtonManager.changeToCreateCircleButton(createButton);
					break;
				case "Rectangle":
					CreateButtonManager.changeToCreateRectangleButton(createButton);
					break;
				case "Square":
					CreateButtonManager.changeToCreateSquareButton(createButton);
					break;
				case "Triangle":
					CreateButtonManager.changeToCreateTriangleButton(createButton);
					break;
				case "Sphere":
					CreateButtonManager.changeToCreateSphereButton(createButton);
					break;
				case "Cube":
					CreateButtonManager.changeToCreateCubeButton(createButton);
					break;
				case "Cone":
					CreateButtonManager.changeToCreateConeButton(createButton);
					break;
				case "Cylinder":
					CreateButtonManager.changeToCreateCylinderButton(createButton);
					break;
				case "Torus":
					CreateButtonManager.changeToCreateTorusButton(createButton);
					break;
				}
			}
		});
		shapeChoiceBox.setTooltip(new Tooltip("Select the shape to create"));
		return shapeChoiceBox;
	}

	/**
	 * Clears the shape on the stage and sets label and color picker to default
	 *
	 * @param shapeColorPicker
	 * @return
	 */
	private static Button initializeClearButton() {
		Button clearButton = new Button("Clear");
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				shapeColorPicker.setValue(Color.BLACK);
				// Set the default text
				userInterfaceLabel.setText("No Shape Created");
				shape = null;
				shapeWindow.getChildren().remove(0);
				shapeWindow.getChildren().addAll(new Label("No Shape Created"));

			}
		});
		return clearButton;
	}

	/**
	 *
	 * @param currentShapeInfoLabel information about the current shape
	 * @param shapeColorPicker      the color of the current shape
	 * @param shapeChoiceBox        allows the user to choose the shape to make
	 * @param createButton          creates the shape
	 * @param clearButton           clears the shape
	 * @return
	 */
	private static VBox initializeLeftPanel(ChoiceBox<String> shapeChoiceBox, Button clearButton) {
		VBox leftPanel = new VBox(10);
		leftPanel.setPrefWidth(200);
		leftPanel.getChildren().addAll(userInterfaceLabel, shapeChoiceBox, shapeColorPicker, createButton, clearButton);

		return leftPanel;
	}

	/**
	 *
	 * @param shape the shape to set static variable shape to
	 */
	public static void setShape(Shape shape) {
		Main.shape = shape;
	}

	/**
	 *
	 * @param shapeWindow shapeWindow to set
	 */
	public static void setShapeWindow(StackPane shapeWindow) {
		Main.shapeWindow = shapeWindow;
	}

	/**
	 *
	 * @return the shapeWindow
	 */
	public static StackPane getShapeWindow() {
		return shapeWindow;
	}

	/**
	 *
	 * @return the userInterfaceLabel
	 */
	public static Label getUserInterfaceLabel() {
		return Main.userInterfaceLabel;
	}

	/**
	 *
	 * @return the shape
	 */
	public static Shape getShape() {
		return Main.shape;
	}

	/**
	 *
	 * @return the shapeColorPicker
	 */
	public static ColorPicker getShapeColorPicker() {
		return Main.shapeColorPicker;
	}

	/**
	 * Main method runs start
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}