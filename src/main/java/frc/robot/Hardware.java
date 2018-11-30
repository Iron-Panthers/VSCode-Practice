package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Hardware {
    public TalonSRX leftMotor;
    public TalonSRX rightMotor;

    public Hardware() {
        /* I'm not using RobotMap because lazy  */
        rightMotor = new TalonSRX(9);
        leftMotor = new TalonSRX(1);
        leftMotor.setInverted(true);
    }
}