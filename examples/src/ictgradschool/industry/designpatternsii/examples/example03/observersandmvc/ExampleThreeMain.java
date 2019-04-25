package ictgradschool.industry.designpatternsii.examples.example03.observersandmvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple example which combines the MVC pattern with the Adapter pattern, to to get arbitrary data displayed in a
 * {@link JTable}, which will be updated properly when said data is updated.
 *
 * <p>Note that this class doesn't really handle user error (e.g. clicking the "remove" button when there's nothing to remove).</p>
 */
public class ExampleThreeMain extends JPanel {

    private NameGenerator gen = new NameGenerator();

    private MyModel model;

    private JTable tableView;

    public ExampleThreeMain() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 250));

        model = new MyModel();

        tableView = new JTable(new MyModelTableAdapter(model));

        JPanel buttonPanel = new JPanel();
        JButton btnAddRandom = new JButton("Add Random");
        btnAddRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.add(gen.getRandomName());
            }
        });
        buttonPanel.add(btnAddRandom);

        JButton btnChangeRandom = new JButton("Change Random");
        btnChangeRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.change((int)(Math.random() * model.size()), gen.getRandomName());
            }
        });
        buttonPanel.add(btnChangeRandom);

        JButton btnRemoveRandom = new JButton("Remove Random");
        btnRemoveRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.remove(model.get((int)(Math.random() * model.size())));
            }
        });
        buttonPanel.add(btnRemoveRandom);

        add(new JScrollPane(tableView), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /** Builds the GUI */
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("TableModelAdapter example");
        frame.setContentPane(new ExampleThreeMain());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /** Program entry point */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
