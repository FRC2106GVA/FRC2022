// Overview: sets-up the drivetrain systems, like motors

// Import variables and libaries
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;


import com.fasterxml.jackson.core.json.DupDetector;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")

// Get robot drive instance  
public class DriveSubsystem extends SubsystemBase {

  // Create variables used in subsystem
  public DifferentialDrive m_robotDrive;
 
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

  private static RelativeEncoder m_rightEncoder;
  private static RelativeEncoder m_leftEncoder;

  private static DifferentialDriveOdometry m_odometry;

  private static AHRS navx;
   
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

    m_rightEncoder = m_rightMotor1.getEncoder();
    m_leftEncoder = m_leftMotor1.getEncoder();


    // Create a tank drive using speed controller groups, a tank drive is LeftForward, RightForward
    m_robotDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    resetEncoders();


    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    try
    {
      navx = new AHRS(SPI.Port.kMXP);
    }catch (RuntimeException e)
    {
      DriverStation.reportError("Navx had a problem : " + e.getMessage(), true);
    }
  }


  boolean autoBalanceXMode = false;
  boolean autoBalanceYMode = false;

  // Control drivetrain with lamba function in RobotContainer
  public void tankDrive(double fwd, double rot, boolean stabi)
  {
    if(stabi == true){
      // Drive with stabi

      // I HAVE NO CLUE IF THIS WILL WORK!!!

      double xAxisRate = rot;
      double yAxisRate = fwd;
      double pitchAngleDegrees = navx.getPitch();
      double rollAngleDegrees = navx.getRoll();
      
      // check if x-axis is off balance and set inner mode activation
      if ( !autoBalanceXMode && (Math.abs(pitchAngleDegrees) >= Math.abs(Constants.DriveConstants.kOffBalanceAngleThresholdDegrees))) {
        autoBalanceXMode = true;
      }
      else if ( autoBalanceXMode && (Math.abs(pitchAngleDegrees) <= Math.abs(Constants.DriveConstants.kOonBalanceAngleThresholdDegrees))) {
        autoBalanceXMode = false;
      }

      // check if x-axis is off balance and set inner mode activation
      if ( !autoBalanceYMode && (Math.abs(pitchAngleDegrees) >= Math.abs(Constants.DriveConstants.kOffBalanceAngleThresholdDegrees))) {
          autoBalanceYMode = true;
      }
      else if ( autoBalanceYMode && (Math.abs(pitchAngleDegrees) <= Math.abs(Constants.DriveConstants.kOonBalanceAngleThresholdDegrees))) {
          autoBalanceYMode = false;
      }

      // Control robot if inner mode is on
      if ( autoBalanceXMode ) {
        double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
        xAxisRate = Math.sin(pitchAngleRadians) * -1;
      }
      if ( autoBalanceYMode ) {
        double rollAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
        yAxisRate = Math.sin(rollAngleRadians) * -1;
      }

    m_robotDrive.arcadeDrive(yAxisRate, rot);
    Timer.delay(0.005);		

    }
    else{
      // Drive normal
      m_robotDrive.arcadeDrive(fwd, rot);
    }
  }






  @Override
  public void periodic() {
    // Update shuffle board values
    
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public double getLeftEncoderDistance()
  {
    return m_leftEncoder.getPosition();
  }

  public double getRightEncoderDistance()
  {
    return m_rightEncoder.getPosition();
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds()
  {
    if (DriveConstants.kInvertedDrivetrain)
    {
      return new DifferentialDriveWheelSpeeds(-m_rightEncoder.getVelocity(), m_leftEncoder.getVelocity());
    }
    return new DifferentialDriveWheelSpeeds(-m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts)
  {
    if (DriveConstants.kInvertedDrivetrain)
    {
      m_leftMotors.setVoltage(rightVolts);
      m_rightMotors.setVoltage(-leftVolts);
    }
    else
    {
      m_leftMotors.setVoltage(-leftVolts);
      m_rightMotors.setVoltage(rightVolts);
    }
    m_robotDrive.feed();
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    zeroHeading();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));;
  }

  public void resetEncoders(){
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  public double getAverageEncoderDistance()
  {
    return (m_rightEncoder.getPosition() + m_leftEncoder.getPosition()) / 2.0;
  }

  public void setMaxOutput(double maxOutput)
  {
    m_robotDrive.setMaxOutput(maxOutput);
  }


  public double getHeading()
  {
    double angle = 0;
    try{
      angle = Math.IEEEremainder(navx.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    } catch(RuntimeException ex)
    {
      DriverStation.reportError("Navx could not get heading" + ex.getMessage(), true);
    }
    return angle;
  }

  public void zeroHeading()
  {
    navx.reset();
  }
}