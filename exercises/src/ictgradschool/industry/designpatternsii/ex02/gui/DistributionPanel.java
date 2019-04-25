package ictgradschool.industry.designpatternsii.ex02.gui;

import ictgradschool.industry.designpatternsii.ex02.model.Course;
import ictgradschool.industry.designpatternsii.ex02.model.Percentage;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * Class to graphically present, using a bar chart, the distribution of a set of
 * student marks held in a Course instance. A DistributionPanel instance shows
 * the relative number of students across ten bands (0-10%, 11-20%, ... 90-100%)
 * based on their overall mark.
 * 
 */
public class DistributionPanel extends JPanel {

	/* The width of a bar in the bar chart. */
	private static final int BAR_WIDTH = 20;

	/* The maximum height of any bar. */
	private static final int BAR_CEILING = 120;

	/* The gap between bars. */
	private static final int HORIZONTAL_GAP = 5;

	/*
	 * Left, right, top and bottom margin size; the border around the bar chart.
	 */
	private static final int MARGIN = 10;

	/*
	 * The number to multiply the height of bars by to ensure that the highest
	 * bar uses the full height of the area allocated for drawing. The highest
	 * bar should be BAR_CEILING pixels high.
	 */
	private double _barHeightMultiplier;

	private int[] _distribution;

	private Course _course;

	/**
	 * Creates a DistributionPanel object.
	 * 
	 */
	public DistributionPanel(Course course) {
		_distribution = new int[10];
		this._course = course;

		repaint();
	}

	/**
	 * Returns the preferred size of this JComponent. The preferred size is the
	 * maximum drawing area required to display the bar chart.
	 */
	public Dimension getPreferredSize() {
		int width = (HORIZONTAL_GAP * 9) + (BAR_WIDTH * 10) + (MARGIN * 2);
		int height = (MARGIN * 2) + BAR_CEILING;
		return new Dimension(width, height);
	}

	/**
	 * Refreshes the appearance of this JComponent. This method performs the
	 * actual drawing of the bar chart.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		generateGraph();

		int x = MARGIN;

		for (int i = 0; i < _distribution.length; i++) {
			g.setColor(Color.red);
			g.fillRect(x, BAR_CEILING + MARGIN
					- (int) (_distribution[i] * _barHeightMultiplier), BAR_WIDTH,
					(int) (_distribution[i] * _barHeightMultiplier));

			g.setColor(Color.black);
			g.drawLine(x, BAR_CEILING + MARGIN, x + BAR_WIDTH - 1, BAR_CEILING
					+ MARGIN);

			x += BAR_WIDTH + HORIZONTAL_GAP;
		}
	}

	private void generateGraph() {
		/* Re-calculate distribution. */
		int numberOfRows = _course.size();

		/* Clear previous distribution data. */
		Arrays.fill(_distribution, 0);

		/*
		 * Query the Course object for the distribution of overall marks.
		 */
		for (Iterator<StudentResult> i = _course.iterator(); i.hasNext();) {
			StudentResult result = i.next();
			Percentage mark = result
					.getAssessmentElement(StudentResult.AssessmentElement.Overall);
			int intVal = mark.intValue();

			if (intVal == 100) {
				_distribution[9]++;
			} else {
				_distribution[intVal / 10]++;
			}
		}

		/* Normalise distribution. */
		int highestNormalisedValue = 0;
		for (int i = 0; i < _distribution.length; i++) {
			_distribution[i] = (int) ((double) _distribution[i] / numberOfRows * 100);
			if (i == 0) {
				highestNormalisedValue = _distribution[0];
			} else {
				highestNormalisedValue = Math.max(highestNormalisedValue,
						_distribution[i]);
			}
		}
		_barHeightMultiplier = (BAR_CEILING / (double) highestNormalisedValue);
	}

}
