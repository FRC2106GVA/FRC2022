// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    //Mechanism Constants
    public static final class DriveConstants{

        //Motor controller CAN IDs
        public static final int CANrightMotor1 = 1;
        public static final int CANrightMotor2 = 2;
        public static final int CANrightMotor3 = 3;

        public static final int CANleftMotor1 = 4;
        public static final int CANleftMotor2 = 5;
        public static final int CANleftMotor3 = 6;

        //Power Distribution Panel (PDP) slots
        /*
        public static final int PDPrightMotor1 = 0;
        public static final int PDPrightMotor2 = 1;
        public static final int PDPrightMotor3 = 2;

        public static final int PDPleftMotor1 = 3;
        public static final int PDPleftMotor2 = 4;
        public static final int PDPleftMotor3 = 5;

        public static final int ampWarning = 2;

        public static final double slowModeAmount = 0.5;
                */
        //PID Constants

        public static final double kDriveGearRatio = 6.9;
         
    }

    public static final class IntakeConstants{
        
        //Motor controller CAN IDs
        //Change this to proper CAN ID

        public static final int CANIntakeMotor = 13;

        //Intake raise PWM port
        public static final int PWMIntakeRaiseMotor = 0;

        //Power Distribution Panel(PDP) slots
        //Change this to proper slot number

        public static final int PDPIntakeMotor = 10;

        //Intake motor current limit

        public static final int IntakeAmpLimit = 20;

    }

    public static final class IndexerConstants{
        //Motor controller CAN IDs
        public static final int CANindexerMotor = 10;
    }

    public static final class ShooterConstants{
        
        //Motor controller CAN IDs
        public static final int CANleadMotor = 11;
        public static final int CANfollowMotor = 12;

        //Power Distribution Panel (PDP) slots
        public static final int PDPleadMotor = 6;
        public static final int PDPfollowMotor = 7;

    }

    public static final class ClimbConstants{
        public static final int CANfirstStage1 = 0;
        public static final int CANfirstStage2 = 0;

        public static final int CANsecondStage = 0;
    }

    public static final class LimitSwitchConstant{
        
    }

    public static final class ShooterPIDConstants{
        public static double kP = 0; 
        public static double kI = 0;
        public static double kD = 0; 
        public static double kIz = 0; 
        public static double kFF = 0.000015; 
        public static double kMaxOutput = 1; 
        public static double kMinOutput = -1;
        public static double maxRPM = 5700;
    }

    public static final class inputConstants{

        public static final int kIDrightJoystick = 1;
        public static final int kIDleftJoystick = 0;

        public static final int kIDxboxController = 3;
        public static final int kIDbuttonBoard = 2;

    }

    public static final class VisionConstants{
        
        public static final double kTargetHeightMeters = 2.64;
        public static final double kCameraHeightMeters = 1.1131;

        public static final double kCameraMountAngle = 20.0;
        
        
    }
}
