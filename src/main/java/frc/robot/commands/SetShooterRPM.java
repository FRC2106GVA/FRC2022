// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.CalculatorSubsystem;

import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class SetShooterRPM extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField", "unused"})
  
  private final ShooterSubsystem m_shooterSubsystem;
  private final VisionSubsystem m_visionSubsystem;
  private final CalculatorSubsystem m_calculatorSubsystem;
  public static double shooterRPM = 0.0;
  
  /**
   * Creates a new SetShooterRPM.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SetShooterRPM(ShooterSubsystem shooter, VisionSubsystem vision, CalculatorSubsystem calc) {
    m_shooterSubsystem = shooter;
    m_visionSubsystem = vision;
    m_calculatorSubsystem = calc;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, vision, calc);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterRPM = (m_calculatorSubsystem.calculateVelocity(m_visionSubsystem.getDistance()))/VisionConstants.kVelocitytoRPMConversion;
    SmartDashboard.putNumber("Calculated RPM:", shooterRPM);
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
