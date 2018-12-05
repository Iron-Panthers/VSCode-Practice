/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.util.MotorGroup;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
	public MotorGroup left;
	public MotorGroup right;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Drive(){
		left = Robot.hardware.left;
		right = Robot.hardware.right;
	}

	public void move(double power){
		left.set(power);
		right.set(power);
	}

	public void move(double leftPower, double rightPower){
		left.set(leftPower);
		right.set(rightPower);
	}

	public void stop(){
		left.set(0);
		right.set(0);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoystick());
	}
}
