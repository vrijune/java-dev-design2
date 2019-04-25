package ictgradschool.industry.designpatternsii.ex02.model;

import ictgradschool.industry.designpatternsii.ex02.policy.AssessmentPolicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * Class to represent a collection of StudentResult instances.
 * 
 */
public class Course {

	/*
	 * StudentResult objects are stored in a Hashtable where the key is the
	 * unique student ID and the value is the associated StudentResult object.
	 */
	private Hashtable<Integer, StudentResult> _results;

	/*
	 * An indexed collection of the StudentResult objects stored in the results
	 * hashtable. This essentially provides an index offering direct access to
	 * any StudentResult object stored.
	 */
	private List<StudentResult> _indexedResults;

	/*
	 * Strategy object for calculating overall marks.
	 */
	private AssessmentPolicy _policy;
	
	/*
	 * List of registered CourseListener objects.
	 */
	private List<CourseListener> _listeners;

	/*
	 * Creates an empty Course instance.
	 */
	public Course() {
		_results = new Hashtable<Integer, StudentResult>();
		_indexedResults = new ArrayList<StudentResult>();
		_listeners = new ArrayList<CourseListener>();
	}

	/**
	 * Adds a new StudentResult object to the model.
	 * 
	 * @param result
	 *            the new StudentResult object.
	 */
	public void addStudentResult(StudentResult result) {
		_results.put(new Integer(result._studentID), result);
		// Regenerate index.
		_indexedResults = new ArrayList<StudentResult>(_results.values());
	}

	/**
	 * Returns a particular StudentResult object from the model.
	 * 
	 * @param studentID
	 *            the unique ID of the student.
	 * @return the StudentResult object corresponding to the studentID argument,
	 *         null if there is no such StudentResult object.
	 */
	public StudentResult getResult(int studentID) {
		Integer key = new Integer(studentID);
		return _results.get(key);
	}

	/**
	 * Returns the StudentResult object at a specified index position within the
	 * model.
	 * 
	 * @param index
	 *            the index position.
	 * @return the corresponding StudentResult object or null if there is no
	 *         matching StudentResult object in the model.
	 */
	public StudentResult getResultAt(int index) {
		if (index < 0 || index >= _results.size()) {
			return null;
		} else {
			return _indexedResults.get(index);
		}
	}

	/**
	 * Returns the index of a particular StudentResult object.
	 * 
	 * @param result
	 *            the StudentResult object whose index position is sought.
	 * @return the index position.
	 */
	public int indexOf(StudentResult result) {
		return _indexedResults.indexOf(result);
	}

	/**
	 * Returns an Iterator object which can be used to iterate through the
	 * Course model's StudentResult objects.
	 * 
	 * @return an Iterator of StudentResult objects.
	 */
	public Iterator<StudentResult> iterator() {
		return _indexedResults.iterator();
	}

	/**
	 * Returns the number of StudentResult objects stored in the model.
	 */
	public int size() {
		return _results.size();
	}

	/**
	 * Sets the AssessmentPolicy object associated with this Course object. This
	 * method recalculates the overall mark for every StudentResult object and
	 * then notifies any Observers of the changes.
	 * 
	 * @param policy
	 *            the new AssessmentPolicy object.
	 */
	public void setAssessmentPolicy(AssessmentPolicy policy) {
		// Record new policy.
		this._policy = policy;

		// Iterate through StudentResult objects and apply the new policy.
		for (StudentResult result : _indexedResults) {
			result.recalculate(_policy);
		}
		
		// Notify listeners.
		for(CourseListener listener : _listeners) {
			listener.courseHasChanged(this);
		}
	}

	/**
	 * Registers a CourseListener object with this Course object. Once 
	 * registered, the listener will receive update notifications when the
	 * Course changes its state.
	 * 
	 * @param listener
	 * 				the CourseListener to register.
	 */
	public void addCourseListener(CourseListener listener) {
		_listeners.add(listener);
	}
	
	/**
	 * Deregisters a CourseListener object from this Course object. Once
	 * deregistered, the listener will no longer receive updates.
	 * 
	 * @param listener
	 * 				the CourseListener to deregister.
	 */
	public void removeCourseListener(CourseListener listener) {
		_listeners.remove(listener);
	}
}
