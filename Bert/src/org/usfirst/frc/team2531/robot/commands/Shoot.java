package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import frclib.time.Time;

/**
 *
 */
public class Shoot extends Command {

	boolean done = false;
	double p = 0;

	public Shoot(double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
		p = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.shooter.setSpeed(p);
		Time.delaySeconds(1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Command up = new MoveElevator(true);
		Command down = new MoveElevator(false);
		up.start();
		down.start();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
