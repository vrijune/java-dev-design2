package ictgradschool.industry.designpatternsii.ex02.gui;

import ictgradschool.industry.designpatternsii.ex02.model.Course;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A representation of a Course object that is statistics-based. In particular,
 * this view calculates the average, median and standard deviation for the set
 * of (overall) student marks.
 * 
 */
public class StatisticsPanel extends JPanel {

	/* TextFields to display the statistics. */
	private JTextField _averageField, _stdDevField, _medianField;

	private Course _course;

	/**
	 * Creates a StatisticsPanel object.
	 */
	public StatisticsPanel(Course course) {
		setupPanel();
		this._course = course;

		repaint();
	}

	/**
	 * Refreshes the statistics displayed by a StatisticsPanel object.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int[] overallMarks = new int[_course.size()];

		int index = 0;
		for (Iterator<StudentResult> i = _course.iterator(); i.hasNext(); index++) {
			StudentResult result = i.next();
			overallMarks[index] = result.getAssessmentElement(
					StudentResult.AssessmentElement.Overall).intValue();
		}

		/*
		 * Calculate the statistics based on the overall marks extracted from
		 * the Course object.
		 */
		double stdDev = getStdDev(overallMarks);
		double average = getAverage(overallMarks);
		int median = getMedian(overallMarks);

		/*
		 * Update the textfields with the new statistics values.
		 */
		_averageField.setText(formatNumber(average));
		_stdDevField.setText(formatNumber(stdDev));
		_medianField.setText(formatNumber(median));
	}

	/**
	 * Returns the standard deviation for a series of int values.
	 */
	private double getStdDev(int[] series) {
		double sum = 0.0;
		double average = getAverage(series);

		for (int i = 0; i < series.length; i++) {
			sum += (series[i] - average) * (series[i] - average);
		}
		return Math.sqrt(sum / (double) (series.length - 1));
	}

	/**
	 * Returns the average of a series of int values.
	 */
	private double getAverage(int[] series) {
		double total = 0;

		if (series.length == 0) {
			return 0;
		}

		for (int i = 0; i < series.length; i++) {
			total += series[i];
		}
		return total / series.length;
	}

	/**
	 * Returns the median value for a series of values.
	 */
	private int getMedian(int[] series) {
		if (series.length == 0) {
			return 0;
		}

		/* Sort the series. */
		Arrays.sort(series);
		return series[series.length / 2];
	}

	/**
	 * Returns a String representation of a decimal number. The representation
	 * is to two decimal places.
	 */
	private String formatNumber(double number) {
		DecimalFormat format = new DecimalFormat("0.##");
		return format.format(number);
	}

	/**
	 * Sets up the GUI for this StatisticsView object.
	 */
	private void setupPanel() {
		JLabel averageLabel = new JLabel("Average");
		JLabel stdDevLabel = new JLabel("Standard deviation");
		JLabel medianLabel = new JLabel("Median");

		_averageField = new JTextField();
		_averageField.setEditable(false);
		_stdDevField = new JTextField();
		_stdDevField.setEditable(false);
		_medianField = new JTextField();
		_medianField.setEditable(false);

		JPanel labelPane = new JPanel();
		labelPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		labelPane.setLayout(new GridLayout(0, 1));
		labelPane.add(averageLabel);
		labelPane.add(stdDevLabel);
		labelPane.add(medianLabel);

		JPanel valuePane = new JPanel();
		valuePane.setLayout(new GridLayout(0, 1));
		valuePane.add(_averageField);
		valuePane.add(_stdDevField);
		valuePane.add(_medianField);

		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
		this.add(labelPane, BorderLayout.WEST);
		this.add(valuePane, BorderLayout.CENTER);
	}
}
