// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimbConstants;

import com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

@SuppressWarnings("unused")


//Remember to set voltage limit


public class ClimbSubsystem extends SubsystemBase {

  
  private static CANSparkMax m_firstStage1;
  private static CANSparkMax m_firstStage2;
  private static CANSparkMax m_secondstage;

  private static MotorControllerGroup m_firstStageMotors;


  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {
    m_firstStage1 = new CANSparkMax(ClimbConstants.CANfirstStage1, MotorType.kBrushless);
    m_firstStage2 = new CANSparkMax(ClimbConstants.CANfirstStage2, MotorType.kBrushless);
    m_secondstage = new CANSparkMax(ClimbConstants.CANsecondStage, MotorType.kBrushless);

    m_firstStage1.restoreFactoryDefaults();
    m_firstStage2.restoreFactoryDefaults();
    m_secondstage.restoreFactoryDefaults();

    m_firstStage1.setIdleMode(IdleMode.kBrake);
    m_firstStage2.setIdleMode(IdleMode.kBrake);
    m_secondstage.setIdleMode(IdleMode.kBrake);

    m_firstStageMotors = new MotorControllerGroup(m_firstStage1, m_firstStage2);

   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void runFirstStage(double input) {
    m_firstStageMotors.set(input);
  }

  public void runSecondStage(double input) {
    m_secondstage.set(input);
  }
}
