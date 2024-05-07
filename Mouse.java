import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;

public class Mouse {

    // Class to contain thread-shared variables.
    User User = new User();
    class User {
        int clickType;
        int delay;
        int clickNum;
        char keybind;
    }
    
    AutoClick AutoClick = new AutoClick();
    class AutoClick extends Thread {
        final int LEFT_CLICK = 1;
        final int RIGHT_CLICK = 2;
        final int MIDDLE_CLICK = 3;

        void leftClick() {
            
            try {
                // Create Robot object.
                Robot Robot = new Robot();

                // Begin clicking left mouse button.
                for(int i = 0; i < User.clickNum; i++) {
                    Robot.mousePress  (InputEvent.BUTTON1_DOWN_MASK);
                    Robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    Thread.sleep(User.delay);
                }

            } catch (AWTException | InterruptedException error) {
                error.printStackTrace();
            }

        }

        void rightClick() {

            try {
                // Create Robot object.
                Robot Robot = new Robot();

                // Begin clicking right mouse button.
                for(int i = 0; i < User.clickNum; i++) {
                    Robot.mousePress  (InputEvent.BUTTON2_DOWN_MASK);
                    Robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                    Thread.sleep(User.delay);
                }

            } catch (AWTException | InterruptedException error) {
                error.printStackTrace();
            }
        }

        void middleClick() {

            try {
                // Create Robot object.
                Robot Robot = new Robot();

                // Begin clicking middle mouse button.
                for(int i = 0; i < User.clickNum; i++) {
                    Robot.mousePress  (InputEvent.BUTTON3_DOWN_MASK);
                    Robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                    Thread.sleep(User.delay);
                }

            } catch (AWTException | InterruptedException error) {
                error.printStackTrace();
            }
        }

        void startAutoThread(int clickType, int delay, int clickNum, char keybind) {
            User.clickType = clickType;
            User.delay = delay;
            User.clickNum = clickNum;
            User.keybind = keybind;

            System.out.println("Creating AutoClick thread...");
            AutoClick thread = new AutoClick();
            thread.start();
        }
        
        public void run() {
            
            // Use different mouse buttons depnding on clickType.
            switch (User.clickType) {
                
                // Left click.
                case 1:
                    leftClick();
                    break;
    
                // Right click.
                case 2:
                    rightClick();
                    break;
    
                // Middle click.
                case 3:
                    middleClick();
                    break;
            }
        }
    }
}
