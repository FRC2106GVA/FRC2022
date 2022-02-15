
package frc.robot.commands;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.util.ControllerInputs;

public class ManualDrive extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final DriveSubsystem m_driveSubsystem;
  private final ControllerInputs m_controllerInputs;
  private boolean m_driveTrainLocked;
  private double m_slowModeAmount;
  
  public ManualDrive(){
 
    m_driveSubsystem = DriveSubsystem.getInstance();
    m_controllerInputs = ControllerInputs.getInstance();
    m_driveTrainLocked = false;
    m_slowModeAmount = Constants.DriveConstants.slowModeAmount;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    // Check if drive train is locked
    if(!m_driveTrainLocked){
      // Check if slow mode is on
      if(m_controllerInputs.GETslowMode()){
        // Move the robot with tank drive
        m_driveSubsystem.robotTankDrive.arcadeDrive(m_controllerInputs.GETleftJoystick().getY()/m_slowModeAmount,m_controllerInputs.GETrightJoystick().getX()/m_slowModeAmount);
      }
      // If slow mode is off
      else{
        m_driveSubsystem.robotTankDrive.arcadeDrive(m_controllerInputs.GETleftJoystick().getY(),m_controllerInputs.GETrightJoystick().getX());
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted){
      // Send a warning if command is interupped
      DriverStation.reportWarning("Command: Manual Drive Interupped", false);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
