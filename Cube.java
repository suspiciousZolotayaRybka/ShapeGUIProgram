package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Cube.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Cube. It specifies the properties of a Cube. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): ThreeDimensionalShape
 *
 * has-a(n): side, volume, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Cube extends ThreeDimensionalShape {
	private final double side;

	/**
	 * The constructor for the concrete cube class that extends
	 * ThreeDimensionalShape. The constructor calculates the volume.
	 *
	 * @param side The side length of the cube
	 */
	public Cube(double side) {
		super(Math.pow(side, 3));
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
		return String.format("cube:side=[%f],volume=[%f],numberOfDimensions=[%d]", side, super.getVolume(),
				super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Cube) obj).side == side)
				&& (((Cube) obj).getVolume() == super.getVolume())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(side, super.getVolume());
	}

}
