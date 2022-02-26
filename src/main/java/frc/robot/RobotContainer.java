// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Imports
package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.inputConstants;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.*;

// Supress stupid warnings
 @SuppressWarnings("unused")

public class RobotContainer {

  // Auto command stuff
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //Create controller instance
  Joystick m_rightJoystick = new Joystick(inputConstants.kIDrightJoystick);
  Joystick m_leftJoystick =  new Joystick(inputConstants.kIDleftJoystick);
  XboxController m_xboxController = new XboxController(inputConstants.kIDxboxController);

  //Create drive subsystem instance
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Run drivetrain with joystick inputs, calls to DriveTrain subsystem with inputed values
    m_driveSubsystem.setDefaultCommand(
    new RunCommand(() -> m_driveSubsystem.tankDrive(m_leftJoystick.getY(), m_rightJoystick.getY()), m_driveSubsystem));

  }

  //Instant commands, you can call functions instantly with inputs
  private void configureButtonBindings() {

    // Test the shooter
    //new JoystickButton(m_rightJoystick, 1)
    //  .toggleWhenPressed(new TestShootCommand(m_shooterSubsystem), true);

    
  }
 

  
// AUTO STUFF BELOW THIS




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
 