package ictgradschool.industry.designpatternsii.ex02.gui;

import ictgradschool.industry.designpatternsii.ex02.admin.CourseDataManager;
import ictgradschool.industry.designpatternsii.ex02.model.Course;
import ictgradschool.industry.designpatternsii.ex02.model.CourseAdapter;
import ictgradschool.industry.designpatternsii.ex02.model.StudentResult;
import ictgradschool.industry.designpatternsii.ex02.policy.AssessmentPolicy;
import ictgradschool.industry.designpatternsii.ex02.policy.AssessmentPolicyStore;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

/**
 * An application which has been designed according to Swing's model/view
 * architecture.
 * 
 */
public class Main extends JFrame {

	/* Main model for this application. */
	private Course _courseModel;

	/* Collection of assessment policy objects. */
	private AssessmentPolicyStore _assessmentPolicies;

	/**
	 * Initialises the application.
	 */
	public Main() {
		super("Model View Application");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/* Create application's model. */
		_courseModel = new Course();

		/* Load model data in the background. */
		Worker worker = new Worker();
		worker.execute();
		
		/**********************************************************************
		 * YOUR CODE STARTS HERE
		 * TODO Instantiate view classes.
		 * TODO Instantiate adapters.
		 * TODO Wire-up (connect) objects.
		 */

		/* View components. */
		DistributionPanel distributionPanel = null;
		StatisticsPanel statsPanel = null;
		JTable tableView = null;

		/* Adapters. */
		DistributionPanelAdapter graphView = null;
		StatisticsPanelAdapter statsView = null;
		CourseAdapter tableModel = null;
		
		/**********************************************************************
		 * YOUR CODE ENDS HERE
		 * You do not need to make further changes to class Main. 
		 */

		/* Create the collection of assessment policies. */
		_assessmentPolicies = new AssessmentPolicyStore();
		_courseModel.setAssessmentPolicy(_assessmentPolicies.getDefaultPolicy());

		// Construct the GUI in the ED thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buildGUI(distributionPanel, statsPanel, tableView,
						new PolicyControlPanel());
			}
		});
	}

	/*
	 * Builds the GUI.
	 */
	private void buildGUI(JPanel distributionView, JPanel statisticsView,
			JTable studentResultView, JPanel policyControlPanel) {
		/*
		 * Create a Panel to combine the distribution and statistics visual
		 * representations.
		 */
		JPanel right = new JPanel();
		right.setBorder(BorderFactory
				.createTitledBorder("Distribution & statistics"));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(distributionView);
		right.add(Box.createRigidArea(new Dimension(10, 0)));
		right.add(statisticsView);

		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(studentResultView);
		scrollPane.setBorder(BorderFactory
				.createTitledBorder("Results breakdown"));
		left.add(scrollPane);
		left.add(Box.createRigidArea(new Dimension(10, 0)));
		left.add(policyControlPanel);

		left.setPreferredSize(new Dimension(left.getPreferredSize().width,
				right.getSize().height));

		/* Create main pane for the application. */
		JPanel mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.X_AXIS));
		mainPane.add(left);
		mainPane.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPane.add(right);

		add(mainPane);

		/* Quit the program in response to the user closing the window. */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Runs the application.
	 */
	public static void main(String[] args) {
		new Main();
	}

	/*
	 * Nested inner class to load Course data from file using a separate thread.
	 */
	private class Worker extends SwingWorker<List<StudentResult>, Void> {

		@Override
		protected List<StudentResult> doInBackground() {
			List<StudentResult> data = CourseDataManager.readData();
			return data;
		}

		@Override
		protected void done() {
			List<StudentResult> data;
			try {
				data = get();

				if (data == null) {
					// No data loaded.
					JOptionPane
							.showMessageDialog(
									Main.this,
									"Unable to load student results. The data file is empty, missing or corrupt. \nUse cs718.modelview.admin.CourseDataManager to (re)create the data file.",
									"Load error", JOptionPane.WARNING_MESSAGE);
				} else {
					// Populate the Course model object with the loaded data.
					for (StudentResult result : data) {
						_courseModel.addStudentResult(result);
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Nested inner class for the policy control panel. Being a subclass of
	 * JPanel (and thus JComponent), a PolicyControlPanel object can be added to
	 * a content pane.
	 */
	private class PolicyControlPanel extends JPanel {

		/*
		 * Creates a PolicyControlPanel object.
		 */
		public PolicyControlPanel() {
			ComboBoxModel<String> policyNames = new DefaultComboBoxModel<String>() {
				{
					// Initialisation code for the new instance of anonymous
					// inner subclass of DefaultComboBoxModel.
					for (AssessmentPolicy policy : _assessmentPolicies
							.getAllPolicies())
						addElement(policy.toString());
				}
			};

			final JComboBox<String> policyNamesComboBox = new JComboBox<String>(
					policyNames);

			/*
			 * Create/instantiate an anonymous inner class to respond to user
			 * selections in the JComboBox component. Essentially, use the
			 * selection to get the corresponding AssessmentPolicy object from
			 * the AssessmentPolicyStore and set it as the Course's policy.
			 */
			policyNamesComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String policyNameSelected = (String) policyNamesComboBox
							.getSelectedItem();
					AssessmentPolicy policySelected = _assessmentPolicies
							.getPolicy(policyNameSelected);
					_courseModel.setAssessmentPolicy(policySelected);
				}
			});

			this.setBorder(BorderFactory
					.createTitledBorder("Assessment policy"));
			this.add(policyNamesComboBox);
		}
	}
}