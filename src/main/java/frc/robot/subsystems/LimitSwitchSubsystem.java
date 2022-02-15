// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimitSwitchSubsystem extends SubsystemBase {

  public static LimitSwitchSubsystem robotLimitSwicthes = null;

  public static LimitSwitchSubsystem getInstance(){
    if(robotLimitSwicthes == null){
      robotLimitSwicthes = new LimitSwitchSubsystem();
    }
    return robotLimitSwicthes;
  }

  /** Creates a new LimitSwitchSubsystem. */
  public LimitSwitchSubsystem() {

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
