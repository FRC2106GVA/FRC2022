// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;

import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
   // Create variables used in subsystem
   private static ShooterSubsystem robotShooter = null;

   private static CANSparkMax m_shooterLead;
   private static CANSparkMax m_shooterFollow;

   
  
  /** Creates a new ShooterSubsystem. */
  public static ShooterSubsystem getInstance(){
    if(robotShooter == null){
      robotShooter = new ShooterSubsystem();
    }
    return robotShooter;
  }
 

  public ShooterSubsystem() {
    m_shooterLead = new CANSparkMax(ShooterConstants.CANleadMotor, MotorType.kBrushless);
    m_shooterFollow = new CANSparkMax(ShooterConstants.CANfollowMotor, MotorType.kBrushless);

    m_shooterLead.restoreFactoryDefaults();
    m_shooterFollow.restoreFactoryDefaults();

    m_shooterLead.setIdleMode(IdleMode.kCoast);
    m_shooterFollow.setIdleMode(IdleMode.kCoast);

    m_shooterFollow.follow(m_shooterLead, true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
