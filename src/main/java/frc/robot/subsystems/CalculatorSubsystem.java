// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

public class CalculatorSubsystem extends SubsystemBase {

  private static double dist;
  

  /** Creates a new CalculatorSubsystem. */
  public CalculatorSubsystem() {
    dist = 0.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static double calculateVelocity(double distance){
    dist = distance;
    return Math.sqrt(
        ((-9.8)*(Math.pow(dist, 2)))/
        (-2*Math.cos(70))*((VisionConstants.kTargetHeightMeters-VisionConstants.kCameraHeightMeters)-(dist * Math.tan(70))));
  }
}
