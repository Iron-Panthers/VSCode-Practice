/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.util.SpicyJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public SpicyJoystick stick;
  public JoystickButton button1;

  public OI() {
    stick = new SpicyJoystick(0);
    button1 = new JoystickButton(stick, 0);
    // button1.whenPressed( turn 90 degs);
  }
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  // button.whenPressed(new ExampleCommand());
  // button.whileHeld(new ExampleCommand());
  // button.whenReleased(new ExampleCommand());
}
