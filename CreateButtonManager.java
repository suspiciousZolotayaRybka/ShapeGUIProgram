package application;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * CreateButtonManager.java
 * Isaac Finehout
 * 1 April 2025
 *
 * I initially created a buttonManager commented out below this code. I noticed
 * there was far too much repeating code, but I did not know how to smoothly
 * handle the repeating code, namely for the Node, Callback, and Consumer
 * object. I used ChatGPT with my initial code to create a more streamlined
 * CreateButtonManager. I left my initial code commented out at the bottom for
 * review.
 * @author fineh
 *
 */

import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.util.Callback;

public class CreateButtonManager {

	/**
	 *
	 * This method was created using ChatGPT. The original code can be seen at the
	 * very bottom of this file. I noticed a lot of repeating constructors, and I
	 * new it could be condensed. However, I didn't know how to go about this, so I
	 * asked ChatGPT for advice, and I was assisted in writing this method to help
	 * condense the code.
	 *
	 * @param button
	 * @param buttonText
	 * @param dialogTitle
	 * @param dialogContents
	 * @param resultConverter
	 * @param shapeCreator
	 * @return
	 */
	public static Button createShapeButton(Button button, String buttonText, String dialogTitle, Node[] dialogContents,
			Callback<ButtonType, Object> resultConverter, Consumer<Object> shapeCreator) {

		button.setText(buttonText);
		button.setOnAction(e -> {
			Dialog<Object> dialog = new Dialog<>();
			dialog.setTitle(dialogTitle);

			ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

			VBox content = new VBox(10);
			content.getChildren().addAll(dialogContents);
			dialog.getDialogPane().setContent(content);

			dialog.setResultConverter(resultConverter);
			dialog.showAndWait().ifPresent(shapeCreator);
		});

		return button;
	}

	/**
	 *
	 * @param circleButton the createButton to be made into a circleButton
	 * @return
	 */
	public static Button changeToCreateCircleButton(Button circleButton) {
		Spinner<Double> radiusSpinner = new Spinner<>();
		radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 425, 150));
		radiusSpinner.setEditable(true);

		Label radiusLabel = new Label("Choose a radius between 1 - 425");

		return createShapeButton(circleButton, "Create Circle", "Circle Creator",
				new Node[] { radiusLabel, radiusSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return radiusSpinner.getValue();
					}
					return null;
				}, result -> {
					double radius = (Double) result;
					Main.setShape(new application.Circle(radius));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(radius);
					circle.setFill(Main.getShapeColorPicker().getValue());
					Main.getShapeWindow().getChildren().addAll(circle);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param rectangleButton the createButton to be made into a rectangleButton
	 * @return
	 */
	public static Button changeToCreateRectangleButton(Button rectangleButton) {
		Spinner<Double> lengthSpinner = new Spinner<>();
		lengthSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		lengthSpinner.setEditable(true);

		Spinner<Double> widthSpinner = new Spinner<>();
		widthSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		widthSpinner.setEditable(true);

		Label lengthLabel = new Label("Choose a length between 1 - 850");
		Label widthLabel = new Label("Choose a width between 1 - 850");

		return createShapeButton(rectangleButton, "Create Rectangle", "Rectangle Creator",
				new Node[] { lengthLabel, lengthSpinner, widthLabel, widthSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return new Double[] { lengthSpinner.getValue(), widthSpinner.getValue() };
					}
					return null;
				}, result -> {
					Double[] dims = (Double[]) result;
					Main.setShape(new application.Rectangle(dims[0], dims[1]));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(dims[0], dims[1]);
					rectangle.setFill(Main.getShapeColorPicker().getValue());
					Main.getShapeWindow().getChildren().addAll(rectangle);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param squareButton the createButton to be made into a squareButton
	 * @return
	 */
	public static Button changeToCreateSquareButton(Button squareButton) {
		Spinner<Double> sideSpinner = new Spinner<>();
		sideSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		sideSpinner.setEditable(true);

		Label sideLabel = new Label("Choose a side between 1 - 850");

		return createShapeButton(squareButton, "Create square", "Square Creator", new Node[] { sideLabel, sideSpinner },
				dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return sideSpinner.getValue();
					}
					return null;
				}, result -> {
					double side = (Double) result;
					Main.setShape(new application.Square(side));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					javafx.scene.shape.Rectangle square = new javafx.scene.shape.Rectangle(side, side);
					square.setFill(Main.getShapeColorPicker().getValue());
					Main.getShapeWindow().getChildren().addAll(square);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param triangleButton the createButton to be made into a triangleButton
	 * @return
	 */
	public static Button changeToCreateTriangleButton(Button triangleButton) {

		Spinner<Double> baseSpinner = new Spinner<>();
		baseSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		baseSpinner.setEditable(true);

		Spinner<Double> heightSpinner = new Spinner<>();
		heightSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		heightSpinner.setEditable(true);

		Label baseLabel = new Label("Choose a base between 1 - 850");
		Label heightLabel = new Label("Choose a height between 1 - 850");

		return createShapeButton(triangleButton, "Create Triangle", "Triangle Creator",
				new Node[] { baseLabel, baseSpinner, heightLabel, heightSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return new Double[] { baseSpinner.getValue(), heightSpinner.getValue() };
					}
					return null;
				}, result -> {
					Double[] dims = (Double[]) result;
					Main.setShape(new application.Triangle(dims[0], dims[1]));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon();
					triangle.getPoints().addAll(new Double[] {
				//@formatter:off
									0.0, 850.0,						// Bottom left point
									dims[0], 850.0,					// Bottom right point
									dims[0] / 2.0, 850.0 - dims[1]	// Middle top point
				//@formatter:on
					});
					triangle.setFill(Main.getShapeColorPicker().getValue());
					Main.getShapeWindow().getChildren().addAll(triangle);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param cubeButton the createButton to be made into a cubeButton
	 * @return
	 */
	public static Button changeToCreateCubeButton(Button cubeButton) {
		Spinner<Double> sideSpinner = new Spinner<>();
		sideSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		sideSpinner.setEditable(true);

		Label sideLabel = new Label("Choose a side between 1 - 850");

		return createShapeButton(cubeButton, "Create cube", "Cube Creator", new Node[] { sideLabel, sideSpinner },
				dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return sideSpinner.getValue();
					}
					return null;
				}, result -> {
					double side = (Double) result;
					Main.setShape(new application.Cube(side));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();

					// Colors
					Color baseColor = Main.getShapeColorPicker().getValue();
					Color darker = baseColor.darker();
					Color darkest = baseColor.darker().darker();

					// Front face
					javafx.scene.shape.Rectangle front = new javafx.scene.shape.Rectangle(side, side);
					front.setFill(baseColor);
					front.setTranslateZ(-1); // Slight Z shift just for stacking order

					// Top face (simulated with rotation and color)
					Polygon top = new Polygon(0.0, 0.0, side, 0.0, side * 0.8, -side * 0.3, side * 0.0, -side * 0.3);
					top.setFill(darker);

					// Side face
					Polygon sideFace = new Polygon(side, 0.0, side, side, side * 0.8, side - (side * 0.3), side * 0.8,
							-side * 0.3);
					sideFace.setFill(darkest);

					// Group them together
					Group fakeCube = new Group(top, sideFace, front);

					// Optional: scale or shift to center it
					fakeCube.setTranslateX(50);
					fakeCube.setTranslateY(50);

					Main.getShapeWindow().getChildren().addAll(fakeCube);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param sphereButton the createButton to be made into a sphereButton
	 * @return
	 */
	public static Button changeToCreateSphereButton(Button sphereButton) {
		Spinner<Double> radiusSpinner = new Spinner<>();
		radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 425, 150));
		radiusSpinner.setEditable(true);

		Label radiusLabel = new Label("Choose a radius between 1 - 425");

		return createShapeButton(sphereButton, "Create Sphere", "Sphere Creator",
				new Node[] { radiusLabel, radiusSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return radiusSpinner.getValue();
					}
					return null;
				}, result -> {
					double radius = (Double) result;
					Main.setShape(new application.Sphere(radius));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(radius);

					Color base = Main.getShapeColorPicker().getValue();
					RadialGradient gradient = new RadialGradient(0, 0, // focusAngle, focusDistance
							0.3, 0.3, // centerX, centerY — offset the light source slightly
							0.7, // radius
							true, // proportional
							CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(0.3, base),
							new Stop(1, base.darker().darker()));

					circle.setFill(gradient);
					Main.getShapeWindow().getChildren().addAll(circle);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param coneButton the createButton to be made into a coneButton
	 * @return
	 */
	public static Button changeToCreateConeButton(Button coneButton) {

		Spinner<Double> radiusSpinner = new Spinner<>();
		radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		radiusSpinner.setEditable(true);

		Spinner<Double> heightSpinner = new Spinner<>();
		heightSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425));
		heightSpinner.setEditable(true);

		Label radiusLabel = new Label("Choose a radius between 1 - 850");
		Label heightLabel = new Label("Choose a height between 1 - 850");

		return createShapeButton(coneButton, "Create Cone", "Cone Creator",
				new Node[] { radiusLabel, radiusSpinner, heightLabel, heightSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return new Double[] { radiusSpinner.getValue(), heightSpinner.getValue() };
					}
					return null;
				}, result -> {
					Double[] dims = (Double[]) result;
					Main.setShape(new application.Cone(dims[0], dims[1]));
					double radius = dims[0];
					double height = dims[1];
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();

					// Create a polygon representing a cone shape
					Polygon cone = new Polygon();

					// Add points to make a triangle (base and tip)
					double centerX = radius / 2;
					cone.getPoints().addAll(centerX, 0.0, // top point (tip of the cone)
							0.0, height, // left base point
							radius, height // right base point
					);

					// Set a gradient to give it a "fake" 3D look
					Paint gradient = new LinearGradient(0, 0, 1, 1, true, null,
							new Stop(0, Main.getShapeColorPicker().getValue()),
							new Stop(1, Main.getShapeColorPicker().getValue()));

					cone.setFill(gradient);

					Main.getShapeWindow().getChildren().addAll(cone);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param cylinderButton the createButton to be made into a cylinderButton
	 * @return
	 */
	public static Button changeToCreateCylinderButton(Button cylinderButton) {

		Spinner<Double> radiusSpinner = new Spinner<>();
		radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 400, 200));
		radiusSpinner.setEditable(true);

		Spinner<Double> heightSpinner = new Spinner<>();
		heightSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 800, 400));
		heightSpinner.setEditable(true);

		Label radiusLabel = new Label("Choose a radius between 1 - 400");
		Label heightLabel = new Label("Choose a height between 1 - 800");

		return createShapeButton(cylinderButton, "Create Cylinder", "Cylinder Creator",
				new Node[] { radiusLabel, radiusSpinner, heightLabel, heightSpinner }, dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return new Double[] { radiusSpinner.getValue(), heightSpinner.getValue() };
					}
					return null;
				}, result -> {
					Double[] dims = (Double[]) result;
					Main.setShape(new application.Cylinder(dims[0], dims[1]));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					double radius = dims[0];
					double height = dims[1];

					// Create the top and bottom ellipses
					Ellipse topEllipse = new Ellipse(radius, 20); // Top ellipse
					topEllipse.setTranslateY(-height / 2); // Position it at the top

					Ellipse bottomEllipse = new Ellipse(radius, 20); // Bottom ellipse
					bottomEllipse.setTranslateY(height / 2); // Position it at the bottom

					// Create the side polygon (simulating the side of the cylinder)
					Polygon sidePolygon = new Polygon();
					sidePolygon.getPoints().addAll(0.0, -height / 2, // Top-left point of the side
							radius * 2, -height / 2, // Top-right point of the side
							radius * 2, height / 2, // Bottom-right point of the side
							0.0, height / 2 // Bottom-left point of the side
					);
					Color color = Main.getShapeColorPicker().getValue();
					sidePolygon.setFill(new LinearGradient(0, 0, 1, 1, true, null, new Stop(0, color),
							new Stop(1, color.darker()))); // Gradient for the side

					// Create a gradient for the top and bottom ellipses (to make them look more 3D)
					topEllipse.setFill(new LinearGradient(0, 0, 0, 1, true, null, new Stop(0, color),
							new Stop(1, color.interpolate(Color.WHITE, 0.3))));
					bottomEllipse.setFill(new LinearGradient(0, 0, 0, 1, true, null, new Stop(0, color.darker()),
							new Stop(1, color.darker().darker())));

					Main.getShapeWindow().getChildren().addAll(topEllipse, sidePolygon, bottomEllipse);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

	/**
	 *
	 * @param torusButton the createButton to be made into a torusButton
	 * @return
	 */
	public static Button changeToCreateTorusButton(Button torusButton) {

		Spinner<Double> minorRadiusSpinner = new Spinner<>();
		minorRadiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 200, 100));
		minorRadiusSpinner.setEditable(true);

		Spinner<Double> majorRadiusSpinner = new Spinner<>();
		majorRadiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(200, 400, 300));
		majorRadiusSpinner.setEditable(true);

		Label minorRadiusLabel = new Label("Choose a minor Radius between 1 - 200");
		Label majorRadiusLabel = new Label("Choose a major Radius between 200 - 400");

		return createShapeButton(torusButton, "Create Torus", "Torus Creator",
				new Node[] { minorRadiusLabel, minorRadiusSpinner, majorRadiusLabel, majorRadiusSpinner },
				dialogButton -> {
					if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
						return new Double[] { minorRadiusSpinner.getValue(), majorRadiusSpinner.getValue() };
					}
					return null;
				}, result -> {
					Double[] dims = (Double[]) result;
					Main.setShape(new application.Torus(dims[0], dims[1]));
					// Remove the previous shape and add a new one
					Main.getShapeWindow().getChildren().clear();
					double minorRadius = dims[0];
					double majorRadius = dims[1];

					javafx.scene.Group group = new javafx.scene.Group();
					Color color = Main.getShapeColorPicker().getValue();

					// Outer torus ellipse (representing the outer part of the torus)
					Ellipse outerTorus = new Ellipse();
					outerTorus.setRadiusX(majorRadius);
					outerTorus.setRadiusY(majorRadius / 2); // To simulate a 3D perspective
					outerTorus.setFill(
							new RadialGradient(0, 0, 0.5, 0.5, 1, true, null, new Stop(0, color), new Stop(1, color)));

					// Inner torus ellipse (representing the hole)
					Ellipse innerTorus = new Ellipse();
					innerTorus.setRadiusX(minorRadius);
					innerTorus.setRadiusY(minorRadius / 2); // To simulate a 3D perspective
					innerTorus.setFill(Color.WHITE);

					// Apply shadow effects to simulate depth
					DropShadow shadow = new DropShadow();
					shadow.setOffsetX(5);
					shadow.setOffsetY(5);
					shadow.setColor(color);
					outerTorus.setEffect(shadow);
					innerTorus.setEffect(shadow);

					// Apply a rotation to simulate the 3D effect
					Rotate rotate = new Rotate();
					rotate.setAngle(45);
					rotate.setPivotX(majorRadius);
					rotate.setPivotY(majorRadius);
					outerTorus.getTransforms().add(rotate);
					innerTorus.getTransforms().add(rotate);

					// Subtract the inner circle to simulate the hole
					Path torusPath = new Path();
					torusPath.getElements().add(new MoveTo(majorRadius, majorRadius));
					torusPath.getElements()
							.add(new ArcTo(majorRadius, majorRadius, 0, majorRadius * 2, majorRadius, false, true));

					// Add the outer and inner ellipses to create the final look
					group.getChildren().addAll(outerTorus, innerTorus);

					Main.getShapeWindow().getChildren().addAll(group);
					Main.getUserInterfaceLabel().setText(Main.getShape().toString());
				});
	}

}

/**
 *
 * // Initial unrefactored code, repeated too much // // /** // * // * @param
 * circleButton a button that pulls up a dialog box to create a circle //
 * * @return //
 */
//	public static Button changeToCreateCircleButton(Button circleButton) {
//
//		circleButton.setText("Create Circle");
//		circleButton.setOnAction(e -> {
//			Dialog<Double> circleSpinnerDialog = new Dialog<>();
//			circleSpinnerDialog.setTitle("Circle Creator");
//
//			// Set the button types
//			ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//			circleSpinnerDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//			// Create the Spinner
//			Spinner<Double> radiusSpinner = new Spinner<>();
//			radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 425, 150)); // min, max, initial
//			radiusSpinner.setEditable(true);
//
//			// Create radius label
//			Label radiusLabel = new Label("Choose a radius between 1 - 425");
//
//			VBox content = new VBox(10);
//			content.getChildren().add(radiusLabel);
//			content.getChildren().add(radiusSpinner);
//			circleSpinnerDialog.getDialogPane().setContent(content);
//
//			// Convert result to double when OK is clicked
//			circleSpinnerDialog.setResultConverter(dialogButton -> {
//				if (dialogButton == okButtonType) {
//					return radiusSpinner.getValue();
//				}
//				return null;
//			});
//
//			// Set the shape to the new shape
//			circleSpinnerDialog.showAndWait().ifPresent(selectedValue -> {
//				double radius = selectedValue;
//				Main.setShape(new Circle(radius));
//			});
//		});
//		return circleButton;
//
//	}
//
//	public static Button changeToCreateRectangleButton(Button rectangleButton) {
//
//		rectangleButton.setText("Create Rectangle");
//		rectangleButton.setOnAction(e -> {
//			Dialog<Double[]> rectangleSpinnerDialog = new Dialog<>();
//			rectangleSpinnerDialog.setTitle("Rectangle Creator");
//
//			// Set the button types
//			ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//			rectangleSpinnerDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//			// Create the Spinners
//			Spinner<Double> lengthSpinner = new Spinner<>();
//			lengthSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425)); // min, max, initial
//			lengthSpinner.setEditable(true);
//			Spinner<Double> widthSpinner = new Spinner<>();
//			widthSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 850, 425)); // min, max, initial
//			widthSpinner.setEditable(true);
//
//			// Create labels
//			Label lengthLabel = new Label("Choose a length between 1 - 850");
//			Label widthLabel = new Label("Choose a width between 1 - 850");
//
//			VBox content = new VBox(10);
//			content.getChildren().add(lengthLabel);
//			content.getChildren().add(lengthSpinner);
//			content.getChildren().add(widthLabel);
//			content.getChildren().add(widthSpinner);
//			rectangleSpinnerDialog.getDialogPane().setContent(content);
//
//			// Convert result to double when OK is clicked
//			rectangleSpinnerDialog.setResultConverter(dialogButton -> {
//				if (dialogButton == okButtonType) {
//					double length = lengthSpinner.getValue();
//					double width = widthSpinner.getValue();
//					return new Double[] { length, width };
//				}
//				return null;
//			});
//
//			// Set the shape to the new shape.
//			// rectangleArray[0]=length
//			// rectangleArray[1]=width
//			rectangleSpinnerDialog.showAndWait().ifPresent(selectedValue -> {
//				Double[] rectangleArray = selectedValue;
//				double length = rectangleArray[0];
//				double width = rectangleArray[1];
//				Main.setShape(new Rectangle(length, width));
//			});
//		});
//		return rectangleButton;
//
//	}
//
//	public static Button changeToCreateSquareButton(Button squareButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateTriangleButton(Button triangleButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateSphereButton(Button sphereButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateCubeButton(Button cubeButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateConeButton(Button coneButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateCylinderButton(Button cylinderButton) {
//
//		return null;
//	}
//
//	public static Button changeToCreateTorusButton(Button torusButton) {
//
//		torusButton.setText("Create Torus");
//
//		torusButton.setOnAction(e -> {
//			System.out.println("TORUS BUTTON");
//		});
//		return torusButton;
//
//	}
//
//	/**
//	 *
//	 * @return Return the initial button, which is a Circle Button
//	 */
//	public static Button initializeCreateButtonAsCircle() {
//
//		Button circleButton = new Button("Create Circle");
//		circleButton.setOnAction(e -> {
//			Dialog<Double> circleSpinnerDialog = new Dialog<>();
//			circleSpinnerDialog.setTitle("Circle Creator");
//
//			// Set the button types
//			ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//			circleSpinnerDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
//
//			// Create the Spinner
//			Spinner<Double> radiusSpinner = new Spinner<>();
//			radiusSpinner.setValueFactory(new DoubleSpinnerValueFactory(1, 425, 150)); // min, max, initial
//			radiusSpinner.setEditable(true);
//
//			// Create radius label
//			Label radiusLabel = new Label("Choose a radius between 1 - 425");
//
//			VBox content = new VBox(10);
//			content.getChildren().add(radiusLabel);
//			content.getChildren().add(radiusSpinner);
//			circleSpinnerDialog.getDialogPane().setContent(content);
//
//			// Convert result to double when OK is clicked
//			circleSpinnerDialog.setResultConverter(dialogButton -> {
//				if (dialogButton == okButtonType) {
//					return radiusSpinner.getValue();
//				}
//				return null;
//			});
//
//			// Set the shape to the new shape
//			circleSpinnerDialog.showAndWait().ifPresent(selectedValue -> {
//				double radius = selectedValue;
//				Main.setShape(new Circle(radius));
//			});
//		});
//		return circleButton;
//
//	}
