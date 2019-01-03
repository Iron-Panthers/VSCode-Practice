package frc.robot.util;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;

public abstract class Controller {

    public HashMap<String, Double> realMotorPower = new HashMap<>();
    public Joystick stick;

    public boolean reverse = false;
    public double speedMultiplier;

    public Controller(Joystick stick){
        this.stick = stick;
    }

    public abstract void reset();

    public abstract void setMotorPowers();

    public abstract double[] calculateMotorPower();

    protected boolean getButton(int button) {
        return stick.getRawButton(button);
    }

    boolean buttonClicked(int button) {
        return stick.getRawButtonPressed(button);
    }

    double linearDeadzone(double value, double deadzone) { // prevents unintended robot movement when the joystick is
                                                           // still
        return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0.05, 1) : 0)
                * (value >= 0 ? 1 : -1);
    }

    HashMap<String, Double> circularDeadzone(double x, double y, double deadzone) { // prevents unintended robotmovement
                                                                                    // when the joystick is still
        HashMap<String, Double> bothValues = new HashMap<String, Double>();
        double magnitude = Math.sqrt(x * x + y * y);
        double scaledMagnitude = (magnitude - deadzone) / (1 - deadzone);
        bothValues.put("x", x * scaledMagnitude);
        bothValues.put("y", y * scaledMagnitude);
        return bothValues;
    }

    double map(double value, double currentMin, double currentMax, double newMin, double newMax) {
        return (value - currentMin) / (currentMax - currentMin) * (newMax - newMin) + newMin;
    }

}