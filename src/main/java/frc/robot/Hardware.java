package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Hardware {
    public TalonSRX leftMotor1;
    public TalonSRX rightMotor1;
    public Hardware() {
        leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR);
        rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR);
    }   
}