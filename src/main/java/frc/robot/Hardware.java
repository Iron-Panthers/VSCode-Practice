package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.util.Constants;
import frc.robot.util.MotorGroup;

public class Hardware {
	public TalonSRX leftMaster;
	public TalonSRX rightMaster;

	public MotorGroup left;
	public MotorGroup right;

	public Hardware(){
		leftMaster = new TalonSRX(RobotMap.LEFT_MASTER_PORT);
		rightMaster = new TalonSRX(RobotMap.RIGHT_MASTER_PORT);
		
		left = new MotorGroup(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F, Constants.DRIVE_VELOCITY, Constants.DRIVE_ACCELERATION, leftMaster);
		right = new MotorGroup(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F, Constants.DRIVE_VELOCITY, Constants.DRIVE_ACCELERATION, rightMaster);
		left.setInverted(Constants.IS_LEFT_INVERTED);
		right.setInverted(Constants.IS_RIGHT_INVERTED);
	}
}