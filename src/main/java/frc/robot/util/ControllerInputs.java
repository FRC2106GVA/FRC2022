package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.inputConstants;

public class ControllerInputs {
    // Create joystick and other input varibles
    private Joystick rightJoystick;
    private Joystick leftJoystick;
    private XboxController xboxController;


    private static ControllerInputs controllerInputs = null;

    public static ControllerInputs getInstance(){
        if(controllerInputs == null){
            controllerInputs = new ControllerInputs();
        }
        return controllerInputs;
    }

    
    private ControllerInputs(){
        
        // Set controller varibles to respective IDs
        
    }

    // GET functions for inputs
    public XboxController GETxboxController(){
        return xboxController;
    }
    public Joystick GETrightJoystick(){
        return rightJoystick;
    }
    public Joystick GETleftJoystick(){
        return leftJoystick;
    }

    // Below this line; Define the button IDs that start certain commands
    public boolean GETexample(){
        return rightJoystick.getRawButton(1);
    }

    public boolean GETslowMode(){
        return rightJoystick.getRawButton(2);
    }
}

 