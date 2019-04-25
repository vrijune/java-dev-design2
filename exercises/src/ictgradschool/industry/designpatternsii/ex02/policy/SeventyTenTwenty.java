package ictgradschool.industry.designpatternsii.ex02.policy;

import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

/**
 * A course assessment policy. This policy assigns a 70/10/20 weighting to
 * exam/test/assignment marks respectively.
 * 
 */
public class SeventyTenTwenty implements AssessmentPolicy {

	public Percentage calculate(StudentResult result) {
		return new Percentage(
				(int) ((result.getAssessmentElement(
						StudentResult.AssessmentElement.Exam).intValue() * 0.7)
						+ (result.getAssessmentElement(
								StudentResult.AssessmentElement.Test)
								.intValue() * 0.1) + (result
						.getAssessmentElement(
								StudentResult.AssessmentElement.Assignment)
						.intValue() * 0.2)));
	}

	public String toString() {
		return "70/10/20 exam/test/assignment";
	}

}
