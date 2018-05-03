package edu.cmu.cs.cs214.rec07.gui;

import edu.cmu.cs.cs214.rec07.solver.CryptarithmSolver;
import edu.cmu.cs.cs214.rec07.solver.TheBestCryptarithmSolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gui interface for CryptarithmSolver.
 */
public class CryptarithmSolverGui extends JFrame {
    //Class constants
    private static final String TITLE = "Cryptarithm Solver";

    //GUI components
    private InputPanel inputPanel;
    private JTextArea solutionArea;

    private final CryptarithmSolver solver;

    public CryptarithmSolverGui(CryptarithmSolver solver) {
        /* TODO:

           Do the following in this constructor. Each of these steps requires one (or in the case of step 6, two)
           lines of code. See the indicated web pages for more information:

           1. Call our superClass constructor with title TITLE:
              https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html

           2. Set our default close operation to EXIT_ON_CLOSE, ensuring that the app exits when the frame is closed.
              https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#setDefaultCloseOperation(int)

           3. Initialize inputPanel with a new instance of the InputPanel class defined below.

           4. Initialize solutionArea to a new JTextArea.
              https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html

           5. Set the layout manager for this frame to a new BoxLayout manager. The API for this layout manager
              is pretty bad, so we'll give you a little hint:
                  setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
              information: http://docs.oracle.com/javase/tutorial/uiswing/layout/using.html

           6. add inputPanel and solutionArea to frame.
              https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html

           7. Call the pack method on the frame, causing the frame to lay out the components.
              Note that JFrame is a subtype of java.awt.Window
              https://docs.oracle.com/javase/7/docs/api/java/awt/Window.html#pack()

         */

        super(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.inputPanel = new InputPanel();
        this.solutionArea = new JTextArea();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(this.inputPanel);
        this.add(this.solutionArea);
        this.pack();
        this.solver = solver;
    }

    private class InputPanel extends JPanel {
        private static final String SOLVE_BUTTON_TEXT = "Solve!";
        private static final String PROMPT = "input cryptarithm";
        private static final int INPUT_FIELD_WIDTH = 40;
        private JButton solveButton;
        private JTextField input;

        InputPanel() {
            /* TODO:

              Each step requires one (or in the case of step 4, two) lines of code):

              1. Initialize the JButton field (solveButton) with a new JButton with SOLVE_BUTTON_TEXT as its label text.
                 https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html

              2. Initialize the JTextField (input) with a new JTextField with PROMPT as its initial text,
                 and INPUT_FIELD_WIDTH as its initial width.
                 https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html

              3. Add an action listener of the JButton to be a function that sets the text of solutionArea
                 to the string representing the solution to the cryptrarithm in the input field.
                 Note that you can update that field from this constructor,
                 as this class is a non-static nested class of CryptarithmSolverGui.
                 Use a lambda for the ActionListener if you know how; otherwise, use an anonymous class instance.
                 https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
                 https://docs.oracle.com/javase/8/docs/api/javax/swing/text/JTextComponent.html#setText-java.lang.String-

              4. Add the two components (solveButton and input) to this panel. Note that a JPanel is a java.awt.Container.
                 https://docs.oracle.com/javase/8/docs/api/java/awt/Container.html#add-java.awt.Component-

            */

            this.solveButton = new JButton(SOLVE_BUTTON_TEXT);
            this.input = new JTextField(PROMPT, INPUT_FIELD_WIDTH);
            this.solveButton.addActionListener(e -> {
                String str = input.getText();
                solutionArea.setText(new TheBestCryptarithmSolver().solve(str).toString());
            });
            this.add(input);
            this.add(solveButton);


        }
    }
}
