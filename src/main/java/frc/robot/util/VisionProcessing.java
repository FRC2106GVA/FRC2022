package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.Constants.VisionConstants;

@SuppressWarnings("unused")

public class VisionProcessing{
    
    private static VisionProcessing visionProcessing = null;
    private static NetworkTable table;
    private static NetworkTableEntry tx, ty;
    private static double x, y;

    public static VisionProcessing getInstance()
    {
        if (visionProcessing == null)
        {
            visionProcessing = new VisionProcessing();
        }
        return visionProcessing;
    }

    private VisionProcessing()
    {
        //Initialize NetworkTables and entries
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        x = 0;
        y = 0;
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
        return (VisionConstants.kTargetHeightMeters - VisionConstants.kCameraHeightMeters)
                /Math.tan(Math.toRadians(VisionConstants.kCameraMountAngle + y));
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