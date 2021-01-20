package ictgradschool.industry.designpatternsii.ex02.gui;

import ictgradschool.industry.designpatternsii.ex02.model.Course;
import ictgradschool.industry.designpatternsii.ex02.model.CourseListener;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import javax.swing.table.AbstractTableModel;
/**********************************************************************
 * YOUR CODE HERE
 */
public class DistributionPanelAdapter implements CourseListener {

	DistributionPanel p;

	public DistributionPanelAdapter(DistributionPanel p){
		this.p = p;

	}

	@Override
	public void courseHasChanged(Course course) {
       p.repaint();
	}


}
