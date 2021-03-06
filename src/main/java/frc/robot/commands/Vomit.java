// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.RunShooter;


@SuppressWarnings("unused")

/** An example command that uses an example subsystem. */
public class Vomit extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final IntakeSubsystem m_intakeSubsystem;
  private final ShooterSubsystem m_shooterSubsystem;
  private final IndexerSubsystem m_indexerSubsystem;

  private final double shooterSpeed = -0.15;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Vomit(IntakeSubsystem intake, ShooterSubsystem shooter, IndexerSubsystem indexer) {

    m_intakeSubsystem = intake;
    m_shooterSubsystem = shooter;
    m_indexerSubsystem = indexer;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter, indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooterSubsystem.stopMotors();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.runIntake(0.75);
   
    m_indexerSubsystem.runIndexer(0.35);
    
    //m_shooterSubsystem.runTestShooter(-0.15);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeSubsystem.runIntake(0);
    m_indexerSubsystem.runIndexer(0);
    //m_shooterSubsystem.runTestShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
