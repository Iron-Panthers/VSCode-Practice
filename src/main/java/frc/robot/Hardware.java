package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Hardware {
    public TalonSRX leftMotor;
    public TalonSRX rightMotor;
    public Hardware(){
        this.leftMotor = new TalonSRX(RobotMap.LEFT_MOTOR);
        this.rightMotor = new TalonSRX(RobotMap.RIGHT_MOTOR);
    }
}