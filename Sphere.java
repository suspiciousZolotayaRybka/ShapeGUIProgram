package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Sphere.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Sphere. It specifies the properties of a Sphere. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): ThreeDimensionalShape
 *
 * has-a(n): radius, volume, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Sphere extends ThreeDimensionalShape {
	private final double radius;

	/**
	 * The constructor for the concrete sphere class that extends
	 * ThreeDimensionalShape. The constructor calculates the volume.
	 *
	 * @param radius The radius of the sphere
	 */
	public Sphere(double radius) {
		super((4.0 / 3.0) * (Math.PI) * (Math.pow(radius, 3)));
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
		return String.format("sphere:radius=[%f],volume=[%f],numberOfDimensions=[%d]", radius, super.getVolume(),
				super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Sphere) obj).radius == radius)
				&& (((Sphere) obj).getVolume() == super.getVolume())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(radius, super.getVolume());
	}

}
