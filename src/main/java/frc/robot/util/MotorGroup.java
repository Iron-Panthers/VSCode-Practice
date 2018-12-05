/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.util.Constants;

/**
 * Add your docs here.
 */
public class MotorGroup {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public TalonSRX masterMotor;
	public ArrayList<TalonSRX> motorGroup;

	public MotorGroup(double p, double i, double d, double f, int v, int a, TalonSRX masterMotor, TalonSRX... motors) {
		motorGroup = new ArrayList<TalonSRX>();
		initArray(masterMotor, motors);
		setup(p, i, d, f, v, a);
	}

	public void initArray(TalonSRX masterMotor, TalonSRX... motors) {
		this.masterMotor = masterMotor;
		for (TalonSRX element : motors) {
			motorGroup.add(element);
			element.follow(this.masterMotor);
		}

	}

	public void setup(double p, double i, double d, double f, int v, int a) {
		masterMotor.config_kP(Constants.kPIDIdx, p, Constants.kTimeoutMs);
		masterMotor.config_kI(Constants.kPIDIdx, i, Constants.kTimeoutMs);
		masterMotor.config_kD(Constants.kPIDIdx, d, Constants.kTimeoutMs);
		masterMotor.config_kF(Constants.kPIDIdx, f, Constants.kTimeoutMs);
		masterMotor.configMotionCruiseVelocity(a, Constants.kTimeoutMs);
		masterMotor.configMotionAcceleration(a, Constants.kTimeoutMs);
	}

	public void set(double power) {
		masterMotor.set(ControlMode.PercentOutput, power);
	}

	public void setInverted(boolean isInverted) {
		masterMotor.setInverted(isInverted);
		for (TalonSRX element : motorGroup) {
			element.setInverted(isInverted);
		}
	}
}
