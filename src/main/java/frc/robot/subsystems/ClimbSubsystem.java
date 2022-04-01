// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimbConstants;

import com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

@SuppressWarnings("unused")




public class ClimbSubsystem extends SubsystemBase {

  
  private static CANSparkMax m_firstStage1;
  private static CANSparkMax m_firstStage2;
  private static CANSparkMax m_secondStage;

  private static RelativeEncoder m_firstStageEncoder;
  private static RelativeEncoder m_secondStageEncoder;


  private static MotorControllerGroup m_firstStageMotors;

  


  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {
    m_firstStage1 = new CANSparkMax(ClimbConstants.CANfirstStage1, MotorType.kBrushless);
    m_firstStage2 = new CANSparkMax(ClimbConstants.CANfirstStage2, MotorType.kBrushless);
    m_secondStage = new CANSparkMax(ClimbConstants.CANsecondStage, MotorType.kBrushless);

    m_firstStageEncoder = m_firstStage1.getEncoder();
    m_secondStageEncoder = m_secondStage.getEncoder();

    m_firstStage1.restoreFactoryDefaults();
    m_firstStage2.restoreFactoryDefaults();
    m_secondStage.restoreFactoryDefaults();

    m_firstStage1.setIdleMode(IdleMode.kBrake);
    m_firstStage2.setIdleMode(IdleMode.kBrake);
    m_secondStage.setIdleMode(IdleMode.kBrake);


    m_firstStage2.follow(m_firstStage1, false);
    
    m_firstStageEncoder.setPosition(0);
    m_secondStageEncoder.setPosition(0);

    //m_firstStage1.setSoftLimit(SoftLimitDirection.kReverse, -169.58f);
    //m_secondStage.setSoftLimit(SoftLimitDirection.kForward, 90.0f);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("First stage rotations:", m_firstStageEncoder.getPosition());
    SmartDashboard.putNumber("Second stage rotations:", m_secondStageEncoder.getPosition());

  }


  public void runFirstStage(double input) {
    m_firstStage1.set(input);
  }

  public void runSecondStage(double input) {
    m_secondStage.set(input);
  }
}
