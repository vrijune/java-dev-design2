package ictgradschool.industry.designpatternsii.examples.example02.texteditor;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * This class contains two {@link JTextArea}s, and shows how to effortlessly get them displaying the same text (and keeping
 * in sync with one another) using a backing model (i.e. the {@link Document} class).
 * <p>
 * The class also incidentally shows how to do drop-down menus and keyboard shortcuts in Swing, which isn't required
 * knowledge for this course but may be of interest to you (BTW this pattern has a name too: the Command pattern!)
 */
public class MultiViewTextEditor extends JPanel {

    /**
     * The model that will be shared between the two text areas
     */
    private Document _model;

    /**
     * The first text editor component
     */
    private JTextArea _view1;

    /**
     * The second text editor component
     */
    private JTextArea _view2;

    /**
     * A label that shows the status of certain operations to the user
     */
    private JLabel _statusInfo;

    /**
     * Creates a new {@link MultiViewTextEditor}
     */
    public MultiViewTextEditor() {

        // Create a PlainDocument to use as the model.
        _model = new PlainDocument();

        // Create the JTextAreas which will be used as the text editors
        _view1 = new JTextArea();
        _view2 = new JTextArea();

        // Set the document of both editors to the same model.
        _view1.setDocument(_model);
        _view2.setDocument(_model);

        _statusInfo = new JLabel(" ");

        layoutGUI();
    }

    /**
     * Builds the UI
     */
    private void layoutGUI() {
        JPanel viewsPane = new JPanel();
        viewsPane.setLayout(new GridLayout(2, 1));

        // Scroll panes allow the views inside them to have scrollbars. In this case, we are adding scrollbars
        // to the first text editor component.
        JScrollPane scrollPaneForView1 = new JScrollPane();
        scrollPaneForView1.setViewportView(_view1);

        JScrollPane scrollPaneForView2 = new JScrollPane();
        scrollPaneForView2.setViewportView(_view2);

        viewsPane.add(scrollPaneForView1);
        viewsPane.add(scrollPaneForView2);

        JMenuBar menuBar = createMenuBar();

        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(_statusInfo, BorderLayout.SOUTH);
        add(viewsPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(600, 800));
    }

    /**
     * Creates the menu bar that will be placed on the top part of the screen.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        // When you create new menu items, you can specify the functionality that menu item will have as an Action object.
        // This also works with JButtons and similar. This is a "best-practice" way of having multiple buttons in your
        // program which implement the same functionality.
        JMenuItem item = new JMenuItem(new NewAction());
        file.add(item);

        file.add(item = new JMenuItem(new OpenAction()));

        file.addSeparator();
        file.add(item = new JMenuItem(new ExitAction()));
        menuBar.add(file);

        return menuBar;
    }

    /** Creates the {@link JFrame} */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("MultiViewTextEditor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new MultiViewTextEditor();
        frame.add(newContentPane);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Program entry point */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    /**
     * A class that encapsulates the action of creating a new document.
     *
     * For more information on this pattern, see: https://docs.oracle.com/javase/tutorial/uiswing/misc/action.html
     */
    private class NewAction extends AbstractAction {
        public NewAction() {
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
            putValue(Action.NAME, "New");
            putValue(Action.SHORT_DESCRIPTION, "Start over with a new document");
        }

        /**
         * The code to be run when this action is performed. Here we just clear all the text in the model using
         * the {@link Document#remove(int, int)} method.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                _model.remove(0, _model.getLength());
            } catch (BadLocationException e) {
                // Ignore.
            }
        }
    }

    /** A class that encapsulates the action of opening an existing document. */
    private class OpenAction extends AbstractAction {
        public OpenAction() {
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
            putValue(Action.NAME, "Open");
            putValue(Action.SHORT_DESCRIPTION, "Open a file");
        }

        /**
         * The code to be run when this action is performed. Here we open a file chooser dialog allowing the user
         * to pick a file. Then, we read that file into the model using a {@link BufferedReader}.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(MultiViewTextEditor.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                    // Read in text file
                    char buffer[] = new char[4096];
                    int len;
                    while ((len = br.read(buffer, 0, buffer.length)) != -1) {
                        // Insert into pane
                        _model.insertString(_model.getLength(), new String(buffer, 0, len), null);
                    }

                } catch (BadLocationException exc) {
                    _statusInfo.setText("Error loading: " + file.getName());
                } catch (FileNotFoundException exc) {
                    _statusInfo.setText("File Not Found: " + file.getName());
                } catch (IOException exc) {
                    _statusInfo.setText("IOException: " + file.getName());
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    /** A class that encapsulates the action of quitting the program. */
    private class ExitAction extends AbstractAction {
        public ExitAction() {
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit the application");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
