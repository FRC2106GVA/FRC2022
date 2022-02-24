package frc.robot.util;

import frc.robot.Constants.VisionConstants;

public class ParametricCalculator {
    
    private static double dist = 0.0;

    public static double calculateVelocity(double distance){
        dist = distance;
        return Math.sqrt(
            ((-9.8)*(Math.pow(dist, 2)))/
            (-2*Math.cos(70))*((VisionConstants.kTargetHeightMeters-VisionConstants.kCameraHeightMeters)-(dist * Math.tan(70))));
    }


}
