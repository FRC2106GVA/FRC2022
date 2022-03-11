// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.Constants.VisionConstants;


public class VisionSubsystem extends SubsystemBase {

  private static NetworkTable table;
  private static NetworkTableEntry tx, ty;
  private static double x, y;
  private static double distance;


  /** Creates a new VisionSubsystem. */
  public VisionSubsystem() {
    //Initialize NetworkTables and entries
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    x = 0;
    y = 0;
    distance = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    run();
  }

  /**
     * Returns the target's current X position, which is from -30 to 30 degrees
     * @return Position in degrees
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the target's current Y position, which is from -25 to 25 degrees
     * @return Position in degrees
     */
    public double getY()
    {
        return y;
    }

    /**
     * The target's distance from the shooter
     * @return The distance in meters
     */
    public double getDistance()
    {
        distance = (VisionConstants.kTargetHeightMeters - VisionConstants.kCameraHeightMeters)
        /Math.tan(Math.toRadians(VisionConstants.kCameraMountAngle + y)) + 0.6096;
        SmartDashboard.putNumber("Distance:", distance);
        return distance;
    }

    public void run()
    {
        x = tx.getDouble(0);
        y = ty.getDouble(0);
    }

    public void setNormalView()
    {
        table.getEntry("pipeline").setNumber(0);
    }

    public void setVisionProcessingView()
    {
        table.getEntry("pipeline").setNumber(1);
    }
}
