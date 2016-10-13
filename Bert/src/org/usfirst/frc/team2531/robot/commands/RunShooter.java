package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunShooter extends Command {

	boolean set = false;
	double pow;
	boolean done = false;

	public RunShooter(double power, boolean run) {
		requires(Robot.shooter);
		pow = power;
		set = run;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Shooter");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.setSpeed(-pow);
		done = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (set) {
			return done;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		if (!set) {
			Robot.shooter.setSpeed(0.0);
		}
		System.out.println("-! Shooter");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
