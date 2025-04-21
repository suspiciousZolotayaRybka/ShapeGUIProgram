package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Circle.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Circle. It specifies the properties of a circle. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): TwoDimensionalShape
 *
 * has-a(n): radius, area, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Circle extends TwoDimensionalShape {
	private final double radius;

	/**
	 * The constructor for the concrete circle class that extends
	 * TwoDimensionalShape. The constructor calculates the area.
	 *
	 * @param radius The radius of the circle
	 */
	public Circle(double radius) {
		super(Math.PI * (Math.pow(radius, 2)));
		this.radius = radius;
	}

	/**
	 * Return the radius
	 *
	 * @return The return value is the radius, a double
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return String.format("circle:radius=[%f],area=[%f],numberOfDimensions=[%d]", radius, super.getArea(),
				super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return ((((Circle) obj).radius == radius)
				&& (((Circle) obj).getArea() == super.getArea()))
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(radius, super.getArea());
	}

}
