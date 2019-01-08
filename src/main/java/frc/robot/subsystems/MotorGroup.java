package frc.robot.subsystems;

import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;

public class MotorGroup{

    Talon leadTalon;
    List<Talon> talons;

    TalonSRX leadTalonSRX;
    List<TalonSRX> talonSRXs;

    public MotorGroup(TalonSRX motor1, TalonSRX...motors){
        leadTalonSRX = motor1;
        for(int i = 0;i<motors.length;i++){
            talonSRXs.add(motors[i]);
        }
    }
    public MotorGroup(Talon motor1, Talon...motors){
        leadTalon = motor1;
        for(int i = 0;i<motors.length;i++){
            talons.add(motors[i]);
        }
    }

    void followMasters(){
        for(int i = 0;i<talons.size();i++){
            talons.get(i).set(leadTalon.get());
        }
        for(int i = 0;i<talonSRXs.size();i++){
            talonSRXs.get(i).follow(leadTalonSRX);
        }
    }
}