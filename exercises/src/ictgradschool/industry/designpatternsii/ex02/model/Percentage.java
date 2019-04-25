package ictgradschool.industry.designpatternsii.ex02.model;

import java.io.Serializable;

/**
 * Class to represent a whole percentage number.
 * 
 */
public class Percentage extends Number implements Serializable {

	/* The actual percentage value. */
	private int _value;

	/**
	 * Attempts to create a Percentage object.
	 * 
	 * @param value
	 *            the value for the Percentage instance.
	 * @throws IllegalArgumentException
	 *             if the value argument falls outside of the permitted bounds
	 *             for a percentage (i.e. 0 to 100).
	 */
	public Percentage(int value) throws IllegalArgumentException {
		if (value < 0 || value > 100) {
			throw new IllegalArgumentException();
		}
		this._value = value;
	}

	/**
	 * Attempts to create a Percentage object from a String.
	 * 
	 * @param value
	 *            the string value for the Percentage instance.
	 * @throws IllegalArgumentException
	 *             if the value argument is not an integer or its integer value
	 *             is negative or greater than 100.
	 */
	public Percentage(String value) throws IllegalArgumentException {
		try {
			int intVal = Integer.parseInt(value);
			if (intVal < 0 || intVal > 100) {
				throw new IllegalArgumentException();
			}
			this._value = intVal;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the value of a Percentage object as a double.
	 * 
	 * @see Number
	 */
	public double doubleValue() {
		return (double) _value;
	}

	/**
	 * Returns the value of a Percentage object as a float.
	 * 
	 * @see Number
	 */
	public float floatValue() {
		return (float) _value;
	}

	/**
	 * Returns the value of a Percentage object as an int.
	 * 
	 * @see Number
	 */
	public int intValue() {
		return _value;
	}

	/**
	 * Returns the value of a Percentage object as a long.
	 * 
	 * @see Number
	 */
	public long longValue() {
		return (long) _value;
	}

	/**
	 * Returns a String representation of a Percentage object.
	 */
	public String toString() {
		return Integer.toString(_value);
	}
}
