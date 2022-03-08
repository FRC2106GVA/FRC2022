// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IndexerConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

@SuppressWarnings("unused")

public class IndexerSubsystem extends SubsystemBase {
  /** Creates a new IndexerSubsystem. */
  private static CANSparkMax m_indexerMotor;
  

  
  public IndexerSubsystem() {
    m_indexerMotor = new CANSparkMax(IndexerConstants.CANindexerMotor, MotorType.kBrushless);
    m_indexerMotor.restoreFactoryDefaults();
    m_indexerMotor.setIdleMode(IdleMode.kCoast);
    m_indexerMotor.setSmartCurrentLimit(20);
    

  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void runIndexer(double value) {
    m_indexerMotor.set(value);
  }

}
