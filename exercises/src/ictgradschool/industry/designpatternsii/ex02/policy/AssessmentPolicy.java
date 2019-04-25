package ictgradschool.industry.designpatternsii.ex02.policy;

import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import java.io.Serializable;

/**
 * An interface which is intended to be implemented by a class that represents a
 * particular assessment policy.
 * 
 */
public interface AssessmentPolicy extends Serializable {

	/**
	 * Calculates and returns the overall mark based on a particular policy.
	 * 
	 * @param result
	 *            the StudentResult object to inspect for marks necessary to
	 *            calculate the overall mark.
	 * @return the overall mark.
	 */
	public Percentage calculate(StudentResult result);

	/**
	 * Returns a descriptive name for the policy.
	 */
	public String toString();
}
