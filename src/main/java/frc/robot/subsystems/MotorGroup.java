/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;

/**
 * Add your docs here.
 */
public class MotorGroup extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  ArrayList<TalonSRX> motors = new ArrayList<TalonSRX>();

  public MotorGroup(TalonSRX motor1, TalonSRX ...motors){
    this.motors.add(motor1);
    for(TalonSRX i : motors){
      this.motors.add(i);
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  
}
