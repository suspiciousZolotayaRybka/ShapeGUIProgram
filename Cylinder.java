package application;

import java.util.Objects;

/* CMSC 335 7382 Object-Oriented and Concurrent Programming
 * Professor Amitava Karmaker
 * Project 2
 * Cylinder.java
 * Isaac Finehout
 * 1 April 2025
 *
 * This is a concrete class named Cylinder. It specifies the properties of a Cylinder. Its is-a and has-a relationships
 * are as follows:
 *
 * is-a(n): ThreeDimensionalShape
 *
 * has-a(n): radius, height, volume, numberOfDimensions
 *
 *
 * @author fineh
 *
 */

public class Cylinder extends ThreeDimensionalShape {
	private final double radius;
	private final double height;

	/**
	 * The constructor for the concrete cylinder class that extends
	 * ThreeDimensionalShape. The constructor calculates the volume.
	 *
	 * @param radius The radius of the cylinder
	 * @param height The height of the cylinder
	 */
	public Cylinder(double radius, double height) {
		super(Math.PI * (Math.pow(radius, 2)) * height);
		this.radius = radius;
		this.height = height;
	}

	/**
	 * Return the radius
	 *
	 * @return The return value is the radius, a double
	 */
	public double getRadius() {
		return radius;
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
		return String.format("cylinder:radius=[%f],height=[%f],volume=[%f],numberOfDimensions=[%d]", radius, height,
				super.getVolume(), super.getNumberOfDimensions());
	}

	@Override
	public boolean equals(Object obj) {
		//	@formatter:off
		return (
				(((Cylinder) obj).radius == radius)
				&& (((Cylinder) obj).height == height)
				&& (((Cylinder) obj).getVolume() == super.getVolume())
				)
				? true : false;
		//	@formatter:on
	}

	@Override
	public int hashCode() {
		return Objects.hash(radius, height, super.getVolume());
	}
}
