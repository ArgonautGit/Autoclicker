import javax.swing.*;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Class checklist:
// MainPanel
// Input
// StartButton

public class Gui {

    // Object for accessing auto keyboard methods in Keyboard.java
    Keyboard Keyboard = new Keyboard();
    // Object for accessing auto clicker methods in Mouse.java
    Mouse Mouse = new Mouse();

    Gui(JFrame frame) {
        frame.add(MainPanel.panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    MainPanel MainPanel = new MainPanel();
    class MainPanel {
        JPanel panel = new JPanel();

        MainPanel() {
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        }
    }
    
    Input Input = new Input();
    class Input {
        final int TEXTFIELD_LENGTH = 5;

        // Class panel.
        JPanel panel = new JPanel(new FlowLayout());

        final int PADDING = 3;
        JTextField text         = new JTextField("Text",          TEXTFIELD_LENGTH + PADDING);
        JTextField keybind      = new JTextField("Keybind",       TEXTFIELD_LENGTH + PADDING);
        JTextField delay        = new JTextField("Delay (ms)",    TEXTFIELD_LENGTH + PADDING);
        JTextField releaseDelay = new JTextField("Release Delay", TEXTFIELD_LENGTH + PADDING);
        
        Input() {
            MainPanel.panel.add(panel);

            panel.add(text);
            panel.add(delay);
            panel.add(keybind);
        }
    }
    
    StartButton StartButton = new StartButton();
    class StartButton {
        // Class panel.
        JPanel panel = new JPanel(new FlowLayout());

        JButton startKeyboard = new JButton("Start Keyboard");
        JButton startMouse    = new JButton("Start Mouse");

        StartButton() {
            MainPanel.panel.add(panel);

            panel.add(startKeyboard);
            panel.add(startMouse);

            startKeyboard.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent keyboardStartEvent) {
                    String text    = Input.text.getText();
                    char   keybind = Input.keybind.getText().charAt(0);
                    int    delay   = 5; // Default 5 milliseconds.

                    // If the user does not enter an integer for Input.delay, catch and alert user.
                    try { 
                        delay = Integer.parseInt(Input.delay.getText()); 
                    } catch (Exception delayInputException) { 
                        // Alert user.
                        JOptionPane.showMessageDialog(null, "Please enter an integer for input delay."); 
                        
                        // Exit method before running auto keyboard.
                        return;
                    }

                    // Start auto typer.
                    Keyboard.AutoType.startAutoThread(text, delay, keybind);
                }
            });

            startMouse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent mouseStartEvent) {
                    int delay = 5; // Default 5 milliseconds.

                    // If the user does an integer for Input.delay, catch and alert user.
                    try {
                        delay = Integer.parseInt(Input.delay.getText());
                    } catch (Exception delayInputException) {
                        // Alert user.
                        JOptionPane.showMessageDialog(null, "Please enter an integer for input delay."); 
                        
                        // Exit method before running auto keyboard.
                        return;
                    }

                    // Start auto clicker.
                    Mouse.AutoClick.startAutoThread(/* need to get parameters (clickType) from some radio box here */);
                }
            });
        }
    }
}
