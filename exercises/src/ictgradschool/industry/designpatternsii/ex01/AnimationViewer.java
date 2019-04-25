package ictgradschool.industry.designpatternsii.ex01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * Simple GUI program to show an animation of shapes. Class ictgradschool.industry.lab15.ex01.AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * ictgradschool.industry.lab15.ex01.AnimationViewer can be added to a JFrame object. A JFrame object is a
 * window that can be closed, minimised, and maximised. The state of a
 * ictgradschool.industry.lab15.ex01.AnimationViewer object comprises a list of Shapes and a Timer object. An
 * ictgradschool.industry.lab15.ex01.AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the ictgradschool.industry.lab15.ex01.AnimationViewer iterates
 * through a list of Shapes requesting that each ictgradschool.industry.lab15.ex01.Shape paints and moves itself.
 * 
 * @author Ian Warren
 */
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds to generate ActionEvents.
	private final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<ictgradschool.industry.designpatternsii.ex01.Shape> shapes;

	private Timer timer = new Timer(DELAY, this);

	/**
	 * Creates an ictgradschool.industry.lab15.ex01.AnimationViewer instance with a list of ictgradschool.industry.lab15.ex01.Shape objects and
	 * starts the animation.
	 */
	public AnimationViewer() {
		shapes = new ArrayList<>();
	
		// Populate the list of Shapes.
        RectangleShape rect = new RectangleShape(0, 0, 2, 3, 40, 50);
		shapes.add(rect);
		shapes.add(new RectangleShape(10, 10, 5, 7, 50, 40));
        shapes.add(new OvalShape(15, 50, 3, 2, 40, 40));
        GemShape largeGem = new GemShape(100, 200, 3, 2, 100, 40);
        shapes.add(largeGem);
        shapes.add(new GemShape(200, 100, 4, 5, 40, 40));
        shapes.add(new DynamicRectangleShape(300, 200, 5, 7, 36, 50, Color.red));
        ImageShape imageShape = new ImageShape(200, 200, -3, 3, "TRex.png", 0.5);
        shapes.add(imageShape);

        // Add some text to some shapes
        rect.setText("Rectangle");
        largeGem.setText("Gem");
        imageShape.setTextColor(Color.white);
        imageShape.setText("RAWR");

		// Start the animation.
		timer.start();
	}

	/**
	 * Called by the Swing framework whenever this ictgradschool.industry.lab15.ex01.AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this ictgradschool.industry.lab15.ex01.AnimationViewer
	 * object has been exposed after being hidden by another window. 
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);


		// Create a ictgradschool.industry.lab15.ex01.GraphicsPainter that ictgradschool.industry.lab15.ex01.Shape objects will use for drawing.
		// The ictgradschool.industry.lab15.ex01.GraphicsPainter delegates painting to a basic Graphics object.
		ictgradschool.industry.designpatternsii.ex01.Painter painter = new GraphicsPainter(g);
		
		// Draw all shapes
		for(ictgradschool.industry.designpatternsii.ex01.Shape s : shapes) {
			s.paint(painter);
		}
	}

	/**
	 * Notifies this ictgradschool.industry.lab15.ex01.AnimationViewer object of an ActionEvent.
	 */
	public void actionPerformed(ActionEvent e) {

        // Calculate bounds of animation screen area.
        int width = getWidth();
        int height = getHeight();

        // Move all shapes
        for(ictgradschool.industry.designpatternsii.ex01.Shape s : shapes) {
            s.move(width, height);
        }

        // Request that the ictgradschool.industry.lab15.ex01.AnimationViewer repaints itself. The call to
        // repaint() will cause the ictgradschool.industry.lab15.ex01.AnimationViewer's paintComponent() to be
        // called.
		repaint();
	}
	
	/**
	 * Main program method to create an ictgradschool.industry.lab15.ex01.AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Animation viewer");
                frame.add(new AnimationViewer());

                // Set window properties.
                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
	}
}
