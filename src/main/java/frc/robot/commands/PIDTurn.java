package frc.robot.commands;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Accurately turn to a given degree. WIP	
 * P - Proportional.	
 * I - Integral.	
 * D - Derivative.	
 * Angle - desired angle.	
 * @author ingihelg
 * @version 1.0
 * 
 */
public class PIDTurn extends PIDCommand {

    public double PIDTurnSolution;
	
	public PIDTurn(double p, double i, double d, double angle) {
		super(p, i, d);
		this.setSetpoint(angle);
        this.getPIDController().setOutputRange(-0.7, 0.7);
    }
	
	protected void execute() {
        Robot.hardware.rightMotor.set(ControlMode.PercentOutput, 10 * PIDTurnSolution);
        Robot.hardware.leftMotor.set(ControlMode.PercentOutput, 10 * PIDTurnSolution);
    }
	
	@Override
	protected double returnPIDInput() {
        return 0.0;
    }

	@Override
	protected void usePIDOutput(double output) {}
	
	protected void interrupted() {
		end();
	}
		
	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {}
	
}