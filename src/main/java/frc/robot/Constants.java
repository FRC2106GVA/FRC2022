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

        //Motor controller CAN Ids
        public static final int CANrightMotor1 = 2;
        public static final int CANrightMotor2 = 3;
        public static final int CANrightMotor3 = 4;

        public static final int CANleftMotor1 = 5;
        public static final int CANleftMotor2 = 6;
        public static final int CANleftMotor3 = 7;

        //Power Distribution Panel (PDP) slots
        public static final int PDPrightMotor1 = 0;
        public static final int PDPrightMotor2 = 1;
        public static final int PDPrightMotor3 = 2;

        public static final int PDPleftMotor1 = 3;
        public static final int PDPleftMotor2 = 4;
        public static final int PDPleftMotor3 = 5;


        public static final int ampWarning = 2;
    }

    public static final class IntakeConstants{

    }

    public static final class IndexerConstants{

    }

    public static final class ShooterConstants{
        public static final int CANleadMotor = 2;
        public static final int CANfollowMotor = 3;
    }

    public static final class ClimbConstants{

    }

    public static final class LimitSwitchConstant{
        
    }

    public static final class inputConstants{

        public static final int IDrightJoystick = 0;
        public static final int IDleftJoystick = 0;

        public static final int IDxboxController = 0;
        public static final int IDbuttonBoard = 0;

    }
}
