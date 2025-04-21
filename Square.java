package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Square.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Square. It specifies the properties of a square. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): TwoDimensionalShape
 *
 * has-a(n): side, area, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Square extends TwoDimensionalShape {
	private final double side;

	/**
	 * The constructor for the concrete square class that extends
	 * TwoDimensionalShape. The constructor calculates the area.
	 *
	 * @param side The length of one square side
	 */
	public Square(double side) {
		super(Math.pow(side, 2));
		this.side = side;
	}

	/**
	 * Return the side length
	 *
	 * @return The return value is the side length, a double
	 */
	public double getSide() {
		return side;
	}

	@Override
	public String toString() {
		return String.format("square:side=[%f],area=[%f],numberOfDimensions=[%d]", side, super.getArea(),
				super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Square) obj).side == side)
				&& (((Square) obj).getArea() == super.getArea())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(side, super.getArea());
	}

}
