package frc.robot.util;

import frc.robot.Robot;

public class GyroWrapper {
    private double[] ypr_deg = new double[3];

	public double getYaw() {
		Robot.hardware.pigeon.getYawPitchRoll(ypr_deg);
		return ypr_deg[0];
	}
}