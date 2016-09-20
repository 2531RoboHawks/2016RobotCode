package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID2;

/**
 *
 */
public class CrossDefence extends Command {

	PID2 head = new PID2(0, 0, 0, Robot.heading);

	boolean crossed = false;

	public CrossDefence() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> CrossDefence");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return crossed;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! CrossDefence");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
