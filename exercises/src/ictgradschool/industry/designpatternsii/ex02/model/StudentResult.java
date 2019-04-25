package ictgradschool.industry.designpatternsii.ex02.model;

import ictgradschool.industry.designpatternsii.ex02.policy.AssessmentPolicy;

import java.io.Serializable;

/**
 * A class representing a student's result for a course. The result is broken
 * down into a test mark, an exam mark, and an assignment mark. From these an
 * overall mark is calculated. In addition to a mark breakdown, a StudentResult
 * object stores a student's unique ID and their name.
 * 
 * 
 */
public class StudentResult implements Serializable {

	public enum AssessmentElement {
		Exam, Test, Assignment, Overall
	};

	public final int _studentID;
	public final String _studentSurname;
	public final String _studentForename;
	private Percentage _examMark;
	private Percentage _testMark;
	private Percentage _assignmentMark;
	private Percentage _overallMark;

	/**
	 * Creates a StudentResult object with the given arguments.
	 */
	public StudentResult(int studentID, String studentSurname,
			String studentForename, Percentage examMark, Percentage testMark,
			Percentage assignmentMark, Percentage overallMark) {
		this._studentID = studentID;
		this._studentSurname = studentSurname;
		this._studentForename = studentForename;
		this._examMark = examMark;
		this._testMark = testMark;
		this._assignmentMark = assignmentMark;
		this._overallMark = overallMark;
	}

	/**
	 * Returns a student's specified assessment element.
	 */
	public Percentage getAssessmentElement(AssessmentElement element) {
		if (element == AssessmentElement.Exam)
			return _examMark;
		else if (element == AssessmentElement.Test)
			return _testMark;
		else if (element == AssessmentElement.Assignment)
			return _assignmentMark;
		else
			return _overallMark;
	}

	/**
	 * Returns a String representation of a StudentResult object.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(_studentSurname);
		buffer.append(", ");
		buffer.append(_studentForename);
		buffer.append(", ");
		buffer.append(_studentID);
		buffer.append(", exam:");
		buffer.append(_examMark);
		buffer.append(", test: ");
		buffer.append(_testMark);
		buffer.append(", assignment: ");
		buffer.append(_assignmentMark);
		buffer.append(", overall: ");
		buffer.append(_overallMark);
		return buffer.toString();
	}

	/**
	 * Operation to set a student's specified assessment element.
	 */
	void setAssessmentElement(AssessmentElement element, Percentage mark,
			AssessmentPolicy policy) {
		if (element == AssessmentElement.Exam)
			_examMark = mark;
		else if (element == AssessmentElement.Test)
			_testMark = mark;
		else if (element == AssessmentElement.Assignment)
			_assignmentMark = mark;

		// Recalculate overall mark.
		recalculate(policy);
	}

	void recalculate(AssessmentPolicy policy) {
		_overallMark = policy.calculate(this);
	}

}