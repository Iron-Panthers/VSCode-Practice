package frc.robot.util;

public class Constants {
    /*  CONTROLLERS: "thrustmaster", "arcade", "gamepad"  */
    public static final String CONTROLLER = "arcade";
    public static final double ACCELERATION = 0.05;
    public static final double DECELERATION = 0.025;
    public class Arcade{
        public static final double ROBOT_SPEED = 1;
        public static final double TURNING_SPEED = 1;
        public static final double DEADZONE = 0.15;
        public static final boolean INVERT_TURN = false;
        public static final boolean INVERT_DIRECTION = false;
    }
    public class Thrustmaster{
        public static final double ROBOT_SPEED = 1;
        public static final double TURNING_SPEED = 1;
        public static final double DEADZONE = 0.1;
        public static final boolean INVERT_TURN = true;                                                  
        public static final boolean INVERT_DIRECTION = false;
    }
    public class Gamepad{
        public static final double ROBOT_SPEED = 1;
        public static final double TURNING_SPEED = 0.8;
        public static final double DEADZONE = 0.1;
        public static final boolean INVERT_TURN = false;                                                  
        public static final boolean INVERT_DIRECTION = false;
    }
}