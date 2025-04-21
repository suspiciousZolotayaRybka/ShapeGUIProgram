package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Triangle.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Triangle. It specifies the properties of a Triangle. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): TwoDimensionalShape
 *
 * has-a(n): base, height, area, numberOfDimensions
 *
 *
 *
 * @author fineh
 *
 */

public class Triangle extends TwoDimensionalShape {
	private final double base;
	private final double height;

	/**
	 * The constructor for the concrete triangle class that extends
	 * TwoDimensionalShape. The constructor calculates the area.
	 *
	 * @param base   The length of the triangle base
	 * @param height The measurement of the triangle height
	 */
	public Triangle(double base, double height) {
		super(.5 * (base * height));
		this.base = base;
		this.height = height;
	}

	/**
	 * Return the base length
	 *
	 * @return The return value is the base length, a double
	 */
	public double getBase() {
		return base;
	}

	/**
	 * Return the height
	 *
	 * @return The return value is the height, a double
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return String.format("triangle:base=[%f],height=[%f],area=[%f],numberOfDimensions=[%d]", base, height,
				super.getArea(), super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Triangle) obj).base == base)
				&& (((Triangle) obj).height == height)
				&& (((Triangle) obj).getArea() == super.getArea())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(base, height, super.getArea());
	}

}
