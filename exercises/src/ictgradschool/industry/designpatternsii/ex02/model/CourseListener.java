package ictgradschool.industry.designpatternsii.ex02.model;

/**
 * Listener (Observer) interface for the Course. An implementation of this 
 * interface can be registered with a Course object and notified whenever the
 * Course changes state.
 *
 */
public interface CourseListener {

	void courseHasChanged(Course course);
}
