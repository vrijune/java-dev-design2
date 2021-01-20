package ictgradschool.industry.designpatternsii.ex02.gui;

import ictgradschool.industry.designpatternsii.ex02.model.Course;
import ictgradschool.industry.designpatternsii.ex02.model.CourseListener;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * YOUR CODE HERE
 */

public class StatisticsPanelAdapter implements CourseListener{

   StatisticsPanel statisticsPanel;

   public StatisticsPanelAdapter(StatisticsPanel statisticsPanel){
   	this.statisticsPanel = statisticsPanel;
   }


	@Override
	public void courseHasChanged(Course course) {
		statisticsPanel.repaint();
	}

}
