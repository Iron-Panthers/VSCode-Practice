/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.PIDTurn;

/**
 * The drive subsystem.
 */
public class Drive extends Subsystem {
    private TalonSRX leftMotor;
    private TalonSRX rightMotor;

    public Drive() {
        rightMotor = Robot.hardware.rightMotor;
        leftMotor = Robot.hardware.leftMotor;
    }

    public void stop() {
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
    }
    
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick()); //actual correct one
    }
}