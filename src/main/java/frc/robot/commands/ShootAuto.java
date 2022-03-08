// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

import frc.robot.Constants.AutoConstants;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;

@SuppressWarnings("unused")


/** An example command that uses an example subsystem. */
public class ShootAuto extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final ShooterSubsystem m_shooterSubsystem;
  private final IndexerSubsystem m_indexerSubsystem;
  private final IntakeSubsystem m_intakeSubsystem;

  /**
   * Creates a new ShootAuto.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootAuto(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem, IntakeSubsystem intakeSubsystem) {
    m_driveSubsystem = driveSubsystem;
    m_shooterSubsystem = shooterSubsystem;
    m_indexerSubsystem = indexerSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem, shooterSubsystem, indexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    new SequentialCommandGroup(
      new DriveForward(m_driveSubsystem, .3f, -.5f, true),
      new DriveForward(m_driveSubsystem, .4f, .35f, true),
      new ParallelRaceGroup(
        new RunIndexer(m_indexerSubsystem),
        new DriveForward(m_driveSubsystem, 1f, .5f, false),
        new RunIntake(m_intakeSubsystem),
        new WaitCommand(3)
      ),
      new DriveForward(m_driveSubsystem, .6f, -.5f, true),
      new RunIndexerBack(m_indexerSubsystem),
      new ParallelRaceGroup(
        new TestShootCommand(m_shooterSubsystem),
        new SequentialCommandGroup(
          new WaitCommand(1),
          new ParallelRaceGroup(
            new RunIndexer(m_indexerSubsystem),
            new WaitCommand(1)
          ),
          new WaitCommand(0.5),
          new ParallelRaceGroup(
            new RunIndexer(m_indexerSubsystem),
            new WaitCommand(2)
          )),
        new WaitCommand(6))
    ).schedule();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
