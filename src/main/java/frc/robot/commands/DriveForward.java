// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class DriveForward extends CommandBase {
  private final DriveSubsystem m_drive;
  private final float driveTime;
  private final float driveSpeed;
  private boolean isFin = false;
  private boolean isDone;
  private int counter = 0;

  /**
   * Creates a new DriveDistance.
   *
   */
  public DriveForward(DriveSubsystem drive, float seconds, float speed, boolean done) {
    m_drive = drive;
    driveTime = seconds;
    driveSpeed = speed;
    isDone = done;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
      counter = 0;
  }

  @Override
  public void execute() {
      if (counter < 50 * driveTime)
      {
          counter++;
          m_drive.tankDrive(driveSpeed, 0);
      }
      else
      {
        m_drive.tankDrive(0, 0);
        isFin = true;
      }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

   @Override
  public boolean isFinished() {
    return isFin && isDone;
  }
}