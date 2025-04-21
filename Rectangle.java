package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Rectangle.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Rectangle. It specifies the properties of a rectangle. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): TwoDimensionalShape
 *
 * has-a(n): length, width, area, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Rectangle extends TwoDimensionalShape {
	private final double length;
	private final double width;

	/**
	 * The constructor for the concrete rectangle class that extends
	 * TwoDimensionalShape. The constructor calculates the area.
	 *
	 * @param length The length of the rectangle
	 * @param width  The width of the rectangle
	 */
	public Rectangle(double length, double width) {
		super(length * width);
		this.length = length;
		this.width = width;
	}

	/**
	 * Return the length
	 *
	 * @return The return value is the length, a double
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Return the width
	 *
	 * @return The return value is the width, a double
	 */
	public double getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return String.format("rectangle:length=[%f],width=[%f],area=[%f],numberOfDimensions=[%d]", length, width,
				super.getArea(), super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Rectangle) obj).length == length)
				&& (((Rectangle) obj).width == width)
				&& (((Rectangle) obj).getArea() == super.getArea())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(length, width, super.getArea());
	}

}
