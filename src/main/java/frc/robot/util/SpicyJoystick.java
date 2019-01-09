package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class SpicyJoystick extends Joystick {
	private double magnitude;
	private double x;
	private double y;
    
    /**
	 * Spicy joystick
	 * Uses magnitude for power
     * My attempt at a better driving experience(?)
	 */
	public SpicyJoystick (int port) {
		super(port);
		x = 0;
		y = 0;
		magnitude = 0;
    }
    
	/**
	 * Uses magnitude (using Pythagorean theorem)
	 * Applies deadzone to the magnitude, then scales it
	 */
	private void findMagnitude() {
		x = getX();
		y = getY();		
		System.out.println("Joystick Values: "+x+", "+y);
		magnitude = Math.abs(Math.sqrt(x*x+y*y)); //Pythagorean theorem
		if (magnitude > 1) { 
			magnitude = 1;
		}

		double scaledMagnitude = (magnitude - Constants.CIRCLE_DEADZONE) / (1 - Constants.CIRCLE_DEADZONE);
		x = x * scaledMagnitude;
		y = y * scaledMagnitude;
		magnitude = magnitude * scaledMagnitude;
    }
    
	public double findLeftPower() {
		findMagnitude();
		return (y - x);
    }
    
	public double findRightPower() {
		findMagnitude();
		return (y + x);
	}

	public boolean isStraight() {
		return Math.abs(getX()) <= Constants.ERROR_RANGE_JOYSTICK && Math.abs(getY()) > Constants.CIRCLE_DEADZONE;
	}
}