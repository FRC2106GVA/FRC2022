// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Imports
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterPIDConstants;
import frc.robot.util.VisionProcessing;

public class ShooterSubsystem extends SubsystemBase {

   
   
   // Create motors for shooter, one is main and the other one follows
   private static CANSparkMax m_shooterLead;
   private static CANSparkMax m_shooterFollow;

   // Create encoders- one is main and the other one follows
   private static RelativeEncoder m_leadEncoder;
   private static RelativeEncoder m_followEncoder;

   private SparkMaxPIDController m_pidController;

   private static VisionProcessing m_visionProcessing;

     

  public ShooterSubsystem() {
    // Set values for shooter motors, inputs are MotorID and MotorType
    m_shooterLead = new CANSparkMax(ShooterConstants.CANleadMotor, MotorType.kBrushless);
    m_shooterFollow = new CANSparkMax(ShooterConstants.CANfollowMotor, MotorType.kBrushless);

    // Restore motor controllers' default settings
    m_shooterLead.restoreFactoryDefaults();
    m_shooterFollow.restoreFactoryDefaults();

    // Makes motors have no resistance, aka they move freely
    m_shooterLead.setIdleMode(IdleMode.kCoast);
    m_shooterFollow.setIdleMode(IdleMode.kCoast);

    // Make the one shooter motor follow the other
    m_shooterFollow.follow(m_shooterLead, true);


    // Make the one encoder follow the other
    m_leadEncoder = m_shooterLead.getEncoder();

    m_visionProcessing.getInstance();

    m_pidController = m_shooterLead.getPIDController();

    m_pidController.setP(ShooterPIDConstants.kP);
    m_pidController.setI(ShooterPIDConstants.kI);
    m_pidController.setD(ShooterPIDConstants.kD);
    m_pidController.setIZone(ShooterPIDConstants.kIz);
    m_pidController.setFF(ShooterPIDConstants.kFF);
    m_pidController.setOutputRange(ShooterPIDConstants.kMinOutput, ShooterPIDConstants.kMaxOutput);
  }

  // Run the shooter lead motor, is called via a instant command
  public void runTestShooter(double input){
    m_shooterLead.set(input);
  }

  public void testShooterPID(double setpoint){
    m_pidController.setReference(setpoint, CANSparkMax.ControlType.kVelocity);
  }

  public void stopMotors(){
    m_shooterLead.stopMotor();
  }

  @Override
  public void periodic() {
    // Update smart dashboard
    SmartDashboard.putNumber("Shooter RPM", m_leadEncoder.getVelocity());
  }



}
