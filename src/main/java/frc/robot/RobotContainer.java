// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Imports
package frc.robot;
import javax.swing.text.html.HTMLDocument.RunElement;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.inputConstants;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.VisionSubsystem;


import frc.robot.commands.*;

// Supress stupid warnings
 @SuppressWarnings("unused")

public class RobotContainer {

 

  //Create controller instance
  Joystick m_rightJoystick = new Joystick(inputConstants.kIDrightJoystick);
  Joystick m_leftJoystick = new Joystick(inputConstants.kIDleftJoystick);
  XboxController m_xboxController = new XboxController(inputConstants.kIDxboxController);
  Joystick m_buttonBoard = new Joystick(inputConstants.kIDbuttonBoard);

  //Create drive subsystem instance
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final VisionSubsystem m_visionProcessing = new VisionSubsystem();

  //private static Command shootCommand;

  private int distance = 0;

  
  SlewRateLimiter filter = new SlewRateLimiter(Constants.DriveConstants.inputSlew);

  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Run drivetrain with joystick inputs, calls to DriveTrain subsystem with inputed values
    m_driveSubsystem.setDefaultCommand(
    new RunCommand(() -> m_driveSubsystem.tankDrive(filter.calculate(-m_rightJoystick.getY()),m_leftJoystick.getX(), false), m_driveSubsystem));

    // Set the scheduler to log Shuffleboard events for command init, interrupt, and finish
    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));
    

  }

  //Instant commands, you can call functions instantly with inputs
  private void configureButtonBindings() {

    
    new JoystickButton(m_rightJoystick, 3)
    .whenPressed(() -> m_driveSubsystem.setMaxOutput(0.5))
    .whenReleased(() -> m_driveSubsystem.setMaxOutput(1));
   
    // Instant commands
    new JoystickButton(m_xboxController, 4)
      .whenPressed(roughShoot());
    
    new JoystickButton(m_rightJoystick, 1)
      .toggleWhenPressed(new RunIntake(m_intakeSubsystem), true);
    new JoystickButton(m_xboxController, 6)
      .whenHeld(new RunIndexer(m_indexerSubsystem), true);
    new JoystickButton(m_xboxController, 5)
      .whenHeld(new RunIndexerBack(m_indexerSubsystem), true);

    new JoystickButton(m_xboxController, 9)
      .toggleWhenPressed(new Vomit(m_intakeSubsystem, m_shooterSubsystem, m_indexerSubsystem));

    new JoystickButton(m_buttonBoard, 4)
      .whenHeld(new RaiseIntake(m_intakeSubsystem), true);
    new JoystickButton(m_buttonBoard, 8)
      .whenHeld(new LowerIntake(m_intakeSubsystem), true);
    
    //Climb Buttons
    new JoystickButton(m_buttonBoard, 1)
      .whenHeld(new FirstStageForward(m_climbSubsystem), true);
    new JoystickButton(m_buttonBoard, 5)
      .whenHeld(new FirstStageBackward(m_climbSubsystem), true);
    
    new JoystickButton(m_buttonBoard, 2)
      .whenHeld(new SecondStageForward(m_climbSubsystem), true);
    new JoystickButton(m_buttonBoard, 6)
      .whenHeld(new SecondStageBackward(m_climbSubsystem), true);
    
    new JoystickButton(m_buttonBoard, 7)
      .whenPressed(new InstantCommand(m_visionProcessing :: getDistance, m_visionProcessing));
    new JoystickButton(m_buttonBoard, 3)
      .toggleWhenPressed(new ChangeLimelightView(m_visionProcessing), true);
    
  }
 

  
// AUTO STUFF BELOW THIS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return new ShootAuto(m_driveSubsystem, m_shooterSubsystem, m_indexerSubsystem, m_intakeSubsystem);
    return new WallShootAuto(m_driveSubsystem, m_shooterSubsystem, m_indexerSubsystem, m_intakeSubsystem);
  }

  public Command roughShoot(){
    Command roughShoot = new SequentialCommandGroup(
      new ParallelRaceGroup(
        new ParallelRaceGroup(
          new RunShooter(m_shooterSubsystem),
          new WaitCommand(4.5)),
        new SequentialCommandGroup(
          new ParallelRaceGroup(
            new RunIndexer(m_indexerSubsystem),
            new WaitCommand(0.5)
          ),
          new WaitCommand(0.75),
          new ParallelRaceGroup(
            new RunIndexer(m_indexerSubsystem),
            new WaitCommand(1.5) 
          ),
          new WaitCommand(0.5)
        )
      )
    );
    return roughShoot;
  }
}
 