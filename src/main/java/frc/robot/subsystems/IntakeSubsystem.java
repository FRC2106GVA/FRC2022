// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

@SuppressWarnings("unused")

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem robotIntake = null;

  private static CANSparkMax m_intakeMotor;
  private static Spark m_raiseMotor;

  public static IntakeSubsystem getInstance(){
    if(robotIntake == null){
      robotIntake = new IntakeSubsystem();
    }
    return robotIntake;
  }

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    
    m_intakeMotor = new CANSparkMax(IntakeConstants.CANIntakeMotor, MotorType.kBrushless);
    
    m_intakeMotor.restoreFactoryDefaults();
    m_intakeMotor.setIdleMode(IdleMode.kCoast);

    m_raiseMotor = new Spark(IntakeConstants.PWMIntakeRaiseMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setIntakeCurrentLimit (int IntakeCurrentLimit){

    m_intakeMotor.setSmartCurrentLimit(IntakeCurrentLimit);

  }

  public void runIntake (double IntakePercent){

    m_intakeMotor.set(IntakePercent);

  }

  public void raiseIntake (double input){
    m_raiseMotor.set(input);
  }

  

}
