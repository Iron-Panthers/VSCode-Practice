/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int LEFT_MOTOR = 1;
  public static final int RIGHT_MOTOR = 9;

  public class Arcade{
      public static final int PORT = 0;
      public static final int QUICK_TURN = 5;
      public static final int REVERSE = 3;
  }

  public class Thrustmaster{
      public static final int PORT = 1;
      public static final int QUICK_TURN = 5;
      public static final int REVERSE = 3;
  }
  
  public class Gamepad{
      public static final int PORT = 2;
      public static final int QUICK_TURN = 5;
      public static final int REVERSE = 6;
  }

}
