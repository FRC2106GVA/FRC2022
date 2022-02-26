// Overview: sets-up the drivetrain systems, like motors

// Import variables and libaries
package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;


import com.fasterxml.jackson.core.json.DupDetector;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;

import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Get robot drive instance  
public class DriveSubsystem extends SubsystemBase {

   // Create variables used in subsystem
   public DifferentialDrive m_robotTankDrive;
 
   // Speed controller groups
   private static MotorControllerGroup m_rightMotors;
   private static MotorControllerGroup m_leftMotors;
 
   // Motor variables
   private static CANSparkMax m_rightMotor1;
   private static CANSparkMax m_rightMotor2;
   private static CANSparkMax m_rightMotor3;
   private static CANSparkMax m_leftMotor1;
   private static CANSparkMax m_leftMotor2;
   private static CANSparkMax m_leftMotor3;

  public DriveSubsystem() {
    // Assign data to motors, MotorID and MotorType
    m_rightMotor1 = new CANSparkMax(DriveConstants.CANrightMotor1, MotorType.kBrushless);
    m_rightMotor2 = new CANSparkMax(DriveConstants.CANrightMotor2, MotorType.kBrushless);
    m_rightMotor3 = new CANSparkMax(DriveConstants.CANrightMotor3, MotorType.kBrushless);
    m_leftMotor1 = new CANSparkMax(DriveConstants.CANleftMotor1, MotorType.kBrushless);
    m_leftMotor2 = new CANSparkMax(DriveConstants.CANleftMotor2, MotorType.kBrushless);
     m_leftMotor3 = new CANSparkMax(DriveConstants.CANleftMotor3, MotorType.kBrushless);

    // Restore motor factory settings, to avoid messed up motors
    m_rightMotor1.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();
    m_rightMotor3.restoreFactoryDefaults();
    m_leftMotor1.restoreFactoryDefaults();
     m_leftMotor2.restoreFactoryDefaults();
     m_leftMotor3.restoreFactoryDefaults();

    // Assign motors to speed controller groups, just input motor names
    m_rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2, m_rightMotor3);
    m_leftMotors = new MotorControllerGroup(m_leftMotor1 ,m_leftMotor2, m_leftMotor3);

    // Invert motors, do this so drivetrain spin like a beyblade
    m_leftMotors.setInverted(true);
    m_rightMotors.setInverted(false);

    
    // Create a tank drive using speed controller groups, a tank drive is LeftForward, RightForward
    m_robotTankDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  }

  // Control drivetrain with lamba function in RobotContainer
  public void tankDrive(double left, double right)
  {
    m_robotTankDrive.arcadeDrive(left, right);
  }

  @Override
  public void periodic() {
    // Update shuffle board values
    
  }

  public void resetEncoders(){

  }

}