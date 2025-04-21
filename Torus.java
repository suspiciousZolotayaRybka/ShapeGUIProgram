package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Torus.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Torus. It specifies the properties of a Torus. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): ThreeDimensionalShape
 *
 * has-a(n): radius, radiusOfRevolution, volume, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Torus extends ThreeDimensionalShape {
	private final double minorRadius;
	private final double majorRadius;

	/**
	 * The constructor for the concrete cylinder class that extends
	 * ThreeDimensionalShape. The constructor calculates the volume.
	 *
	 * @param minorRadius The minorRadius of the torus
	 * @param majorRadius The majorRadius of the torus
	 */
	public Torus(double minorRadius, double majorRadius) {
		super(Math.PI * (Math.pow(minorRadius, 2)) * 2 * Math.PI * majorRadius);
		this.minorRadius = minorRadius;
		this.majorRadius = majorRadius;
	}

	/**
	 * Return the minorRadius
	 *
	 * @return The return value is the minorRadius, a double
	 */
	public double getMinorRadius() {
		return minorRadius;
	}

	/**
	 * Return the majorRadius
	 *
	 * @return The return value is the majorRadius, a double
	 */
	public double getMajorRadius() {
		return majorRadius;
	}

	@Override
	public String toString() {
		return String.format("torus:minorRadius=[%f],majorRadius=[%f],volume=[%f],numberOfDimensions=[%d]", minorRadius,
				majorRadius, super.getVolume(), super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Torus) obj).minorRadius == minorRadius)
				&& (((Torus) obj).majorRadius == majorRadius)
				&& (((Torus) obj).getVolume() == super.getVolume())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(minorRadius, majorRadius, super.getVolume());
	}

}
