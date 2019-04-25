package ictgradschool.industry.designpatternsii.ex02.policy;

import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

/**
 * A course assessment policy. This policy weights exam and coursework marks
 * equally and ignores any assignment mark.
 * 
 */
public class FiftyZeroFifty implements AssessmentPolicy {

	public Percentage calculate(StudentResult result) {
		return new Percentage(
				(int) ((result.getAssessmentElement(
						StudentResult.AssessmentElement.Exam).intValue() * 0.5) + (result
						.getAssessmentElement(
								StudentResult.AssessmentElement.Assignment)
						.intValue() * 0.5)));
	}

	public String toString() {
		return "50/50 exam/assignment";
	}
}
