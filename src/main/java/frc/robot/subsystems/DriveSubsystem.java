// Overview: sets-up the drivetrain systems, like motors

// Import variables and libaries
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.fasterxml.jackson.core.json.DupDetector;
//import java.util.PrimitiveIterator.OfDouble;
//import javax.print.CancelablePrintJob;
//import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import edu.wpi.first.wpilibj.geometry.Pose2d;
//import edu.wpi.first.wpilibj.geometry.Rotation2d;
//import edu.wpi.first.wpilibj.kinematics. ampWarning = 2;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Get robot drive instance  
public class DriveSubsystem extends SubsystemBase {

   // Create variables used in subsystem
   private static DriveSubsystem robotDrive = null;
   public DifferentialDrive robotTankDrive;
 
   // Speed controller groups
   private static MotorControllerGroup mg_rightMotors;
   private static MotorControllerGroup mg_leftMotors;
 
   // Motor variables
   private static CANSparkMax m_rightMotor1;
   private static CANSparkMax m_rightMotor2;
   private static CANSparkMax m_rightMotor3;
   private static CANSparkMax m_leftMotor1;
   private static CANSparkMax m_leftMotor2;
   private static CANSparkMax m_leftMotor3;
 
   // PDP instance
   
 
   // Other varibles
   public boolean driveTrainLocked;

  public static DriveSubsystem getInstance(){
    if(robotDrive == null){
      robotDrive = new DriveSubsystem();
    }
    return robotDrive;
  }
 

  private DriveSubsystem() {
    // Assign motor CAN IDs, and winding type
    m_rightMotor1 = new CANSparkMax(DriveConstants.CANrightMotor1, MotorType.kBrushless);
    m_rightMotor2 = new CANSparkMax(DriveConstants.CANrightMotor2, MotorType.kBrushless);
    m_rightMotor3 = new CANSparkMax(DriveConstants.CANrightMotor3, MotorType.kBrushless);
    m_leftMotor1 = new CANSparkMax(DriveConstants.CANleftMotor1, MotorType.kBrushless);
    m_leftMotor2 = new CANSparkMax(DriveConstants.CANleftMotor2, MotorType.kBrushless);
    m_leftMotor3 = new CANSparkMax(DriveConstants.CANleftMotor3, MotorType.kBrushless);

    // Restore motor factory settings
    m_rightMotor1.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();
    m_rightMotor3.restoreFactoryDefaults();
    m_leftMotor1.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();
    m_leftMotor3.restoreFactoryDefaults();

    // Assign motors to speed controller groups
    mg_rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2, m_rightMotor3);
    mg_leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2, m_leftMotor3);

    // Create a tank drive using speed controller groups
    robotTankDrive = new DifferentialDrive(mg_leftMotors, mg_rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }




/**
  public void lockDrivetrain(boolean locked){
    // Lock all motors
    if(locked == true){
      driveTrainLocked = true;
      m_rightMotor1.setIdleMode(IdleMode.kBrake);
      m_rightMotor2.setIdleMode(IdleMode.kBrake);
      m_leftMotor1.setIdleMode(IdleMode.kBrake);
      m_leftMotor2.setIdleMode(IdleMode.kBrake);
    }

    // Unlock all motors
    if(locked == false){
      driveTrainLocked = false;
      m_rightMotor1.setIdleMode(IdleMode.kCoast);
      m_rightMotor2.setIdleMode(IdleMode.kCoast);
      m_leftMotor1.setIdleMode(IdleMode.kCoast);
      m_leftMotor2.setIdleMode(IdleMode.kCoast);
    }
  }

  // Arcade drive
  public void arcadeDrive(double forward, double rotation){
    if (DriverStation.isTeleop()){
      robotTankDrive.arcadeDrive(forward, -rotation, true); 
    }
  }
  **/
}

