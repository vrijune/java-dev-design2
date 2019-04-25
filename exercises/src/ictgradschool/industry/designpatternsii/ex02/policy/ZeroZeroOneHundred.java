package ictgradschool.industry.designpatternsii.ex02.policy;

import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

/**
 * A course assessment policy. This policy is used to assess students entirely
 * on their assignment mark.
 * 
 */
public class ZeroZeroOneHundred implements AssessmentPolicy {

	public Percentage calculate(StudentResult result) {
		return result
				.getAssessmentElement(StudentResult.AssessmentElement.Assignment);
	}

	public String toString() {
		return "Assignment only";
	}

}