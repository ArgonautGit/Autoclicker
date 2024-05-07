import java.awt.Robot;
import java.awt.AWTException;


class Keyboard {

    // Class to contain variables the clicker thread can access
    User User = new User();
    class User {
        volatile String inputText;
        volatile int delay;
        volatile char keybind;
    }

    AutoType AutoType = new AutoType();
    class AutoType extends Thread {
        void startAutoThread(String inputText, int delay, char keybind) {
            // Update thread-shared variables.
            User.inputText = inputText;
            User.keybind = keybind;
            User.delay = delay;
            
            System.out.println("Creating AutoType thread...");
            AutoType thread = new AutoType();
            thread.start();
        }

        // Code to run in new thread.
        public void run() {
            try {
                // Create Robot object.
                Robot Robot = new Robot();
    
                // Convert text to uppercase. The Robot class uses upper case ASCII codes.
                User.inputText = User.inputText.toUpperCase();
    
                for (char key : User.inputText.toCharArray()) {
                    // Implicitly convert current character into keycode.
                    int keyNum = key;
    
                    // Press keyboard key.
                    Robot.keyPress(keyNum);     // Press key.
                    Robot.keyRelease(keyNum);   // Release key.
    
                    // Delay before next keypress
                    Thread.sleep(User.delay);
                }
            } catch(AWTException | InterruptedException error) {
                error.printStackTrace();
            }
        }
    }
    
    AutoHold AutoHold = new AutoHold();
    class AutoHold {
        void startAuto() {
            // Hold key code...
        }
    }
}
