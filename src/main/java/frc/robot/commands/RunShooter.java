// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants.ShooterConstants;

/** An example command that uses an example subsystem. */
public class RunShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;

  /**
   * Creates a new RunShooter.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunShooter(ShooterSubsystem subsystem) {
    
    m_subsystem = subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.testShooterPID(2300);
    ShooterConstants.shooterRunning = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_subsystem.runTestShooter(0.65);
    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_subsystem.testShooterPID(0);
    m_subsystem.stopMotors();
    ShooterConstants.shooterRunning = true;
    System.out.println("Shooter stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
