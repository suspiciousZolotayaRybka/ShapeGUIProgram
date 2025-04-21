package application;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Shape.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is an abstract class named Shape. It serves as a base for specifying two
 * dimensional and three dimensional shapes. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): Object
 *
 * has-a(n): numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public abstract class Shape {

	// This is a private field that represents the numberOfDimensions a Shape has
	private final int numberOfDimensions;

	// This is the constructor for Shape
	public Shape(int numberOfDimensions) {
		this.numberOfDimensions = numberOfDimensions;
	}

	// This is the getter for numberOfDimensions
	public int getNumberOfDimensions() {
		return numberOfDimensions;
	}

	// This is the toString method that must be implemented in concrete subclasses
	@Override
	public abstract String toString();

	// This is the equals method that must be implemented in concrete subclasses
	@Override
	public abstract boolean equals(Object obj);

	// This is the hashCode method that must be implemented in concrete subclasses
	@Override
	public abstract int hashCode();
}
