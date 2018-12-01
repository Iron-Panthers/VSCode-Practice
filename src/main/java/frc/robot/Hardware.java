package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.util.GyroWrapper;

public class Hardware {
    public TalonSRX leftMotor;
    public TalonSRX rightMotor;
    public PigeonIMU pigeon;
    public GyroWrapper gyroWrapper;

    public Hardware() {
        /* I'm not using RobotMap because lazy  */
        gyroWrapper = new GyroWrapper();
        pigeon = new PigeonIMU(2);
        rightMotor = new TalonSRX(9);
        leftMotor = new TalonSRX(1);
        leftMotor.setInverted(true);
    }
}