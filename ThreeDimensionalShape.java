package application;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * ThreeDimensionalShape.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is an abstract class named ThreeDimensionalShape. It serves as a base for specifying three
 * dimensional shapes. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): Shape
 *
 * has-a(n): volume, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public abstract class ThreeDimensionalShape extends Shape {

	// This is a private field that represents the area a ThreeDimensionalShape has
	private final double volume;

	// This is the constructor for ThreeDimensionalShape
	public ThreeDimensionalShape(double volume) {
		super(3);
		this.volume = volume;
	}

	// This is the getter for volume
	public double getVolume() {
		return volume;
	}

}
